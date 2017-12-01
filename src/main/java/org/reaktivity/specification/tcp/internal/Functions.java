/**
 * Copyright 2016-2017 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.reaktivity.specification.tcp.internal;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.kaazing.k3po.lang.el.Function;
import org.kaazing.k3po.lang.el.spi.FunctionMapperSpi;
import org.reaktivity.specification.tcp.internal.types.TcpAddressFW.Builder;
import org.reaktivity.specification.tcp.internal.types.stream.TcpBeginExFW;

public final class Functions
{

    private static final byte[] ANY_IP_ADDR = new byte[4];
    private static final ThreadLocal<MutableDirectBuffer> WRITE_BUFFER = new ThreadLocal<MutableDirectBuffer>()
    {
        @Override
        protected MutableDirectBuffer initialValue()
        {
            return new UnsafeBuffer(new byte[1024]);
        }
    };


    @Function
    public static byte[] beginExtRemoteAddress(
        String ipString,
        int port) throws UnknownHostException
    {
        final InetAddress inet = InetAddress.getByName(ipString);
        final byte[] ip = inet.getAddress();
        final Consumer<Builder> addressBuilder = inet instanceof Inet4Address?
                b -> b.ipv4Address(s -> s.put(ip)):
                b -> b.ipv6Address(s -> s.put(ip));

        final MutableDirectBuffer writeBuffer = WRITE_BUFFER.get();
        TcpBeginExFW begin = new TcpBeginExFW.Builder()
                .wrap(writeBuffer, 0, writeBuffer.capacity())
                .localAddress(b -> b.ipv4Address(o -> o.set(ANY_IP_ADDR)))
                .localPort(0)
                .remoteAddress(addressBuilder)
                .remotePort(port)
                .build();
        byte[] result = new byte[begin.sizeof()];
        begin.buffer().getBytes(0, result);
        return result;
    }

    @Function
    public static byte[] beginExtRemoteHost(
        String host,
        int port)
    {
        final MutableDirectBuffer writeBuffer = WRITE_BUFFER.get();
        TcpBeginExFW begin = new TcpBeginExFW.Builder()
                .wrap(writeBuffer, 0, writeBuffer.capacity())
                .localAddress(b -> b.ipv4Address(o -> o.set(ANY_IP_ADDR)))
                .localPort(0)
                .remoteAddress(b -> b.host(host))
                .remotePort(port)
                .build();
        byte[] result = new byte[begin.sizeof()];
        begin.buffer().getBytes(0, result);
        return result;
    }


    public static class Mapper extends FunctionMapperSpi.Reflective
    {

        public Mapper()
        {
            super(Functions.class);
        }

        @Override
        public String getPrefixName()
        {
            return "tcp";
        }
    }


    private Functions()
    {
        // utility
    }
}

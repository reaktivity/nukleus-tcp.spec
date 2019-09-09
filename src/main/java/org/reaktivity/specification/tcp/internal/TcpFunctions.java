/**
 * Copyright 2016-2019 The Reaktivity Project
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

public final class TcpFunctions
{
    @Function
    public static TcpBeginExBuilder beginEx()
    {
        return new TcpBeginExBuilder();
    }

    public static final class TcpBeginExBuilder
    {
        private final TcpBeginExFW.Builder beginExRW;

        private TcpBeginExBuilder()
        {
            final MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024]);
            this.beginExRW = new TcpBeginExFW.Builder()
                                             .wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public TcpBeginExBuilder typeId(
            int type)
        {
            beginExRW.typeId(type);
            return this;
        }

        public TcpBeginExBuilder localAddress(
            String localAddress) throws UnknownHostException
        {
            final InetAddress inet = InetAddress.getByName(localAddress);
            final byte[] ip = inet.getAddress();
            final Consumer<Builder> addressBuilder = inet instanceof Inet4Address?
                b -> b.ipv4Address(s -> s.put(ip)):
                b -> b.ipv6Address(s -> s.put(ip));

            beginExRW.localAddress(addressBuilder);
            return this;
        }

        public TcpBeginExBuilder localPort(
            int localPort)
        {
            beginExRW.localPort(localPort);
            return this;
        }

        public TcpBeginExBuilder remoteAddress(
            String remoteAddress) throws UnknownHostException
        {
            final InetAddress inet = InetAddress.getByName(remoteAddress);
            final byte[] ip = inet.getAddress();
            final Consumer<Builder> addressBuilder = inet instanceof Inet4Address?
                b -> b.ipv4Address(s -> s.put(ip)):
                b -> b.ipv6Address(s -> s.put(ip));

            beginExRW.remoteAddress(addressBuilder);
            return this;
        }

        public TcpBeginExBuilder remoteHost(
            String remoteHost)
        {
            beginExRW.remoteAddress(b -> b.host(remoteHost));
            return this;
        }

        public TcpBeginExBuilder remotePort(
            int remotePort)
        {
            beginExRW.remotePort(remotePort);
            return this;
        }

        public byte[] build()
        {
            final TcpBeginExFW beginEx = beginExRW.build();
            final byte[] array = new byte[beginEx.sizeof()];
            beginEx.buffer().getBytes(0, array);
            return array;
        }
    }

    public static class Mapper extends FunctionMapperSpi.Reflective
    {

        public Mapper()
        {
            super(TcpFunctions.class);
        }

        @Override
        public String getPrefixName()
        {
            return "tcp";
        }
    }


    private TcpFunctions()
    {
        // utility
    }
}

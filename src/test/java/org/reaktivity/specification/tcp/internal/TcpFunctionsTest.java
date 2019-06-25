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

import static java.util.Arrays.copyOfRange;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.reaktivity.specification.tcp.internal.types.TcpAddressFW.KIND_HOST;
import static org.reaktivity.specification.tcp.internal.types.TcpAddressFW.KIND_IPV4_ADDRESS;
import static org.reaktivity.specification.tcp.internal.types.TcpAddressFW.KIND_IPV6_ADDRESS;

import java.net.UnknownHostException;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.junit.Test;
import org.reaktivity.specification.tcp.internal.types.stream.TcpBeginExFW;

public class TcpFunctionsTest
{
    @Test
    public void shouldGenerateTcpBeginExtensionWithIpv4Address() throws UnknownHostException
    {
        byte[] build = TcpFunctions.beginEx()
                                   .typeId(0x01)
                                   .localAddress("0.0.0.0")
                                   .localPort(0)
                                   .remoteAddress("127.0.0.1")
                                   .remotePort(8080)
                                   .build();

        DirectBuffer buffer = new UnsafeBuffer(build);
        TcpBeginExFW beginEx = new TcpBeginExFW().wrap(buffer, 0, buffer.capacity());

        assertEquals(0x01, beginEx.typeId());
        assertEquals(KIND_IPV4_ADDRESS, beginEx.localAddress().kind());
        assertArrayEquals(new byte[4],
                copyOfRange(beginEx.buffer().byteArray(), beginEx.localAddress().offset() + 1, beginEx.localAddress().limit()));
        assertEquals(0, beginEx.localPort());
        assertEquals(KIND_IPV4_ADDRESS, beginEx.remoteAddress().kind());
        assertArrayEquals(new byte[] { 127, 0, 0, 1 },
            copyOfRange(beginEx.buffer().byteArray(), beginEx.remoteAddress().offset() + 1, beginEx.remoteAddress().limit()));
        assertEquals(8080, beginEx.remotePort());
    }

    @Test
    public void shouldGenerateTcpBeginExtensionWithIpv6Address() throws UnknownHostException
    {
        byte[] build = TcpFunctions.beginEx()
                                   .typeId(0x01)
                                   .localAddress("::")
                                   .localPort(0)
                                   .remoteAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334")
                                   .remotePort(8080)
                                   .build();

        DirectBuffer buffer = new UnsafeBuffer(build);
        TcpBeginExFW beginEx = new TcpBeginExFW().wrap(buffer, 0, buffer.capacity());

        assertEquals(0x01, beginEx.typeId());
        assertEquals(KIND_IPV6_ADDRESS, beginEx.localAddress().kind());
        assertArrayEquals(new byte[16],
                copyOfRange(beginEx.buffer().byteArray(), beginEx.localAddress().offset() + 1, beginEx.localAddress().limit()));
        assertEquals(0, beginEx.localPort());
        assertEquals(KIND_IPV6_ADDRESS, beginEx.remoteAddress().kind());
        assertArrayEquals(new byte[] { 32, 1, 13, -72, -123, -93, 0, 0, 0, 0, -118, 46, 3, 112, 115, 52 },
                copyOfRange(beginEx.buffer().byteArray(), beginEx.remoteAddress().offset() + 1, beginEx.remoteAddress().limit()));
        assertEquals(8080, beginEx.remotePort());
    }

    @Test
    public void shouldGenerateTcpBeginExtensionWithHost() throws UnknownHostException
    {
        byte[] build = TcpFunctions.beginEx()
                                   .typeId(0x01)
                                   .localAddress("0.0.0.0")
                                   .localPort(0)
                                   .remoteHost("localhost")
                                   .remotePort(8080)
                                   .build();

        DirectBuffer buffer = new UnsafeBuffer(build);
        TcpBeginExFW beginEx = new TcpBeginExFW().wrap(buffer, 0, buffer.capacity());

        assertEquals(0x01, beginEx.typeId());
        assertEquals(KIND_IPV4_ADDRESS, beginEx.localAddress().kind());
        assertArrayEquals(new byte[4],
        copyOfRange(beginEx.buffer().byteArray(), beginEx.localAddress().offset() + 1, beginEx.localAddress().limit()));
        assertEquals(0, beginEx.localPort());
        assertEquals(KIND_HOST, beginEx.remoteAddress().kind());
        assertEquals("localhost", beginEx.remoteAddress().host().asString());
        assertEquals(8080, beginEx.remotePort());
    }
}

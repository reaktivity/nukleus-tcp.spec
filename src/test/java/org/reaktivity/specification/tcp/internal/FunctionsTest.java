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

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class FunctionsTest
{
    @Test
    public void shouldGenerateClientIpv4Ext() throws UnknownHostException
    {
        byte[] actual = Functions.clientBeginExtIp("127.0.0.1", 8080);
        byte[] expected = new byte[] {
                1, 0, 0, 0, 0, 0, 0,
                1, 0x7F, 0, 0, 1, 0x1F, (byte) 0x90
            };
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateClientIpv6Ext() throws UnknownHostException
    {
        byte[] actual = Functions.clientBeginExtIp("2001:0db8:85a3:0000:0000:8a2e:0370:7334", 8080);
        byte[] expected = new byte[]
        {
            1, 0, 0, 0, 0,
            0, 0,
            2, 32, 1, 13, (byte) 184, (byte) 133, (byte) 163, 0, 0, 0, 0,
            (byte) 138, 46, 3, 112, 115, 52,
            31, (byte) 144
        };
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    @Ignore("https://github.com/reaktivity/nukleus-maven-plugin/issues/49")
    public void shouldGenerateClientHostExt()
    {
        Functions.clientBeginExtHost("localhost", 8080);
        // TODO assert, current throws IndexOutOfBoundsException
    }

}

/**
 * Copyright 2016-2018 The Reaktivity Project
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
package org.reaktivity.specification.tcp.rfc793;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

/**
 * RFC-793
 */
public class TcpIT
{

    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("streams", "org/reaktivity/specification//tcp/rfc793");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "client.and.server.sent.data.multiple.frames/client",
        "client.and.server.sent.data.multiple.frames/server" })
    public void shouldSendAndReceiveData() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
            "client.and.server.sent.data.with.padding/client",
            "client.and.server.sent.data.with.padding/server" })
    public void shouldSendAndReceiveDataWithPadding() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "client.close/client",
        "client.close/server" })
    public void shouldInitiateClientClose() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data/client",
        "client.sent.data/server" })
    public void shouldReceiveClientSentData() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.multiple.frames/client",
        "client.sent.data.multiple.frames/server" })
    public void shouldReceiveClientSentDataInMultipleFrames() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.multiple.streams/client",
        "client.sent.data.multiple.streams/server" })
    public void shouldReceiveClientSentDataOnMultipleStreams() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "concurrent.connections/client",
        "concurrent.connections/server" })
    public void shouldEstablishConcurrentFullDuplexConnections() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "connection.established/client",
        "connection.established/server" })
    public void shouldEstablishConnection() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "server.close/client",
        "server.close/server" })
    public void shouldInitiateServerClose() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data/client",
        "server.sent.data/server" })
    public void shouldReceiveServerSentData() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.multiple.frames/client",
        "server.sent.data.multiple.frames/server" })
    public void shouldReceiveServerSentDataMultipleFrames() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.multiple.streams/client",
        "server.sent.data.multiple.streams/server" })
    public void shouldReceiveServerSentDataMultipleStreams() throws Exception
    {
        k3po.finish();
    }

}

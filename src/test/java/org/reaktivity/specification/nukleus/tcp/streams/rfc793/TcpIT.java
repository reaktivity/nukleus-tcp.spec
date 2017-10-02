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
package org.reaktivity.specification.nukleus.tcp.streams.rfc793;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.ScriptProperty;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

/**
 * RFC-793
 */
public class TcpIT
{

    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("streams", "org/reaktivity/specification/nukleus/tcp/streams/rfc793");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "client.and.server.sent.data.multiple.frames/client",
        "client.and.server.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldSendAndReceiveData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.close/client",
        "client.close/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldInitiateClientClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.received.abort.sent.end/client",
        "client.received.abort.sent.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldReceiveResetAndAbortAndNoAdditionalResetWhensendEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Ignore("BEGIN vs RESET read order not yet guaranteed to match write order")
    @Test
    @Specification({
        "client.received.reset.and.abort/client",
        "client.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldReceiveResetAndAbortAfterIOExceptionFromRead() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.abort/client",
        "client.sent.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldProcessAbortFromClient() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.abort.and.reset/client",
        "client.sent.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldProcessAbortAndResetFromClient() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data/client",
        "client.sent.data/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveClientSentData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.multiple.frames/client",
        "client.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveClientSentDataInMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.multiple.streams/client",
        "client.sent.data.multiple.streams/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveClientSentDataOnMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.multiple.streams.second.was.reset/client",
        "client.sent.data.multiple.streams.second.was.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveClientSentDataWithMultipleStreamsSecondWasReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.received.reset.and.abort/client",
        "client.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldSendResetAndAbortToClientAfterIOExceptionFromWrite() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.received.reset/client",
        "client.sent.data.received.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldSendResetToClientAppWhenItExceedsWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.data.then.end/client",
        "client.sent.data.then.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveClientSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.end.then.received.data/client",
        "client.sent.end.then.received.data/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldReceiveDataAfterEndingOutput() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.reset/client",
        "client.sent.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "client.sent.reset.and.end/client",
        "client.sent.reset.and.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldResetConnectionThenEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "concurrent.connections/client",
        "concurrent.connections/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldConveyBidirectionalDataOnConcurrentConnections() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "connection.established/client",
        "connection.established/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldEstablishConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "connection.failed/client",
        "connection.failed/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldFailConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.close/client",
        "server.close/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldInitiateServerClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.received.abort.sent.end/client",
        "server.received.abort.sent.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldReceiveResetAndAbortAndNoAdditionalResetWhensendEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.received.reset.and.abort/client",
        "server.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldReceiveResetAndAbortAfterIOExceptionFromRead() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.abort/client",
        "server.sent.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldAbortConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Ignore("BEGIN vs RESET read order not yet guaranteed to match write order")
    @Test
    @Specification({
        "server.sent.abort.and.reset/client",
        "server.sent.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldAbortAndResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data/client",
        "server.sent.data/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveServerSentData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.multiple.frames/client",
        "server.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveServerSentDataMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.multiple.streams/client",
        "server.sent.data.multiple.streams/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveServerSentDataOnMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.multiple.streams.second.was.reset/client",
        "server.sent.data.multiple.streams.second.was.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveServerSentDataWithMultipleStreamsSecondWasReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.received.reset.and.abort/client",
        "server.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldSendResetToServerAppWhenItExceedsWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.received.reset.and.abort/client",
        "server.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldSendResetAndAbortToServerAfterIOExceptionFromWrite() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.data.then.end/client",
        "server.sent.data.then.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void shouldReceiveServerSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.end.then.received.data/client",
        "server.sent.end.then.received.data/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldReceiveDataAfterEndingOutput() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.reset/client",
        "server.sent.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "server.sent.reset.then.end/client",
        "server.sent.reset.then.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldResetConnectionThenEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }
}

/**
 * Copyright 2016-2020 The Reaktivity Project
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
package org.reaktivity.specification.nukleus.tcp.streams;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

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
public class ApplicationIT
{

    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("app", "org/reaktivity/specification/nukleus/tcp/streams/application/rfc793");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${app}/client.and.server.sent.data.multiple.frames/client",
        "${app}/client.and.server.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendAndReceiveData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.and.server.sent.data.with.padding/client",
        "${app}/client.and.server.sent.data.with.padding/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendAndReceiveDataWithPadding() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.close/client",
        "${app}/client.close/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldInitiateClientClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.received.abort.sent.end/client",
        "${app}/client.received.abort.sent.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void clientShouldReceiveResetAndAbortAndNoAdditionalResetWhensendEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.received.reset.and.abort/client",
        "${app}/client.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void clientShouldReceiveResetAndAbortAfterIOExceptionFromRead() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.abort/client",
        "${app}/client.sent.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldProcessAbortFromClient() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.abort.and.reset/client",
        "${app}/client.sent.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldProcessAbortAndResetFromClient() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data/client",
        "${app}/client.sent.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveClientSentData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.multiple.frames/client",
        "${app}/client.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveClientSentDataInMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.multiple.streams/client",
        "${app}/client.sent.data.multiple.streams/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveClientSentDataOnMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.multiple.streams.second.was.reset/client",
        "${app}/client.sent.data.multiple.streams.second.was.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveClientSentDataWithMultipleStreamsSecondWasReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.received.abort.and.reset/client",
        "${app}/client.sent.data.received.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendAbortAndResetToClientAfterIOExceptionFromWrite() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.received.reset/client",
        "${app}/client.sent.data.received.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendResetToClientAppWhenItExceedsWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.data.then.end/client",
        "${app}/client.sent.data.then.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveClientSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.end.then.received.data/client",
        "${app}/client.sent.end.then.received.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void clientShouldReceiveDataAfterEndingOutput() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.reset/client",
        "${app}/client.sent.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void clientShouldResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/client.sent.reset.and.end/client",
        "${app}/client.sent.reset.and.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void clientShouldResetConnectionThenEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/concurrent.connections/client",
        "${app}/concurrent.connections/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldConveyBidirectionalDataOnConcurrentConnections() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/max.connections/client",
        "${app}/max.connections/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void maxConnections() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/connection.established/client",
        "${app}/connection.established/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldEstablishConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/connection.failed/client",
        "${app}/connection.failed/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldFailConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.close/client",
        "${app}/server.close/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldInitiateServerClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.received.abort.sent.end/client",
        "${app}/server.received.abort.sent.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldReceiveResetAndAbortAndNoAdditionalResetWhensendEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.received.reset.and.abort/client",
        "${app}/server.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldReceiveResetAndAbortAfterIOExceptionFromRead() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.abort/client",
        "${app}/server.sent.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldAbortConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.abort.and.reset/client",
        "${app}/server.sent.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldAbortAndResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data/client",
        "${app}/server.sent.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveServerSentData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.multiple.frames/client",
        "${app}/server.sent.data.multiple.frames/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveServerSentDataMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.multiple.streams/client",
        "${app}/server.sent.data.multiple.streams/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveServerSentDataOnMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.multiple.streams.second.was.reset/client",
        "${app}/server.sent.data.multiple.streams.second.was.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveServerSentDataWithMultipleStreamsSecondWasReset() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.received.reset.and.abort/client",
        "${app}/server.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendResetToServerAppWhenItExceedsWindow() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.received.reset.and.abort/client",
        "${app}/server.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldSendResetAndAbortToServerAfterIOExceptionFromWrite() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.data.then.end/client",
        "${app}/server.sent.data.then.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void shouldReceiveServerSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.end.then.received.data/client",
        "${app}/server.sent.end.then.received.data/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldReceiveDataAfterEndingOutput() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.reset/client",
        "${app}/server.sent.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${app}/server.sent.reset.and.end/client",
        "${app}/server.sent.reset.and.end/server" })
    @ScriptProperty("serverConnect \"nukleus://streams/tcp#0\"")
    public void serverShouldResetConnectionThenEnd() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }
}

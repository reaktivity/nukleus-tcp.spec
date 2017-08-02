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
        "client.received.abort.sent.end/client",
        "client.received.abort.sent.end/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldReceiveResetAndAbortAndNoAdditionalResetWhensendEnd() throws Exception
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
        "client.sent.data.received.reset.and.abort/client",
        "client.sent.data.received.reset.and.abort/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldReceiveResetAndAbortAfterIOExceptionFromWrite() throws Exception
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
    public void serverShouldReceiveResetAndAbortAfterIOExceptionFromWrite() throws Exception
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
    public void clientShouldAbortConnection() throws Exception
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

    @Test
    @Specification({
        "client.sent.abort.and.reset/client",
        "client.sent.abort.and.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void clientShouldAbortAndResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

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
        "server.sent.reset/client",
        "server.sent.reset/server" })
    @ScriptProperty("serverConnect \"nukleus://tcp/streams/source\"")
    public void serverShouldResetConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_CLIENT");
        k3po.finish();
    }

}

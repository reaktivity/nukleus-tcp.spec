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
package org.reaktivity.specification.nukleus.tcp.streams;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.specification.nukleus.NukleusRule;

public class ClientIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("route", "org/reaktivity/specification/nukleus/tcp/control/route")
        .addScriptRoot("streams", "org/reaktivity/specification/nukleus/tcp/streams");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule()
        .directory("target/nukleus-itests")
        .streams("tcp", "source#partition")
        .streams("source", "tcp#any");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/connection.established/client/nukleus",
        "${streams}/connection.established/client/source"
    })
    public void shouldEstablishConnection() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/server.sent.data/client/nukleus",
        "${streams}/server.sent.data/client/source"
    })
    public void shouldReceiveServerSentData() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/server.sent.data.multiple.frames/client/nukleus",
        "${streams}/server.sent.data.multiple.frames/client/source"
    })
    public void shouldReceiveServerSentDataWithMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/server.sent.data.multiple.streams/client/nukleus",
        "${streams}/server.sent.data.multiple.streams/client/source"
    })
    public void shouldReceiveServerSentDataWithMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/server.sent.data.then.end/client/nukleus",
        "${streams}/server.sent.data.then.end/client/source"
    })
    public void shouldReceiveServerSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data/client/nukleus",
        "${streams}/client.sent.data/client/source"
    })
    public void shouldReceiveClientSentData() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.multiple.frames/client/nukleus",
        "${streams}/client.sent.data.multiple.frames/client/source"
    })
    public void shouldReceiveClientSentDataWithMultipleFrames() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.multiple.frames.partial.writes/client/nukleus",
        "${streams}/client.sent.data.multiple.frames.partial.writes/client/source"
    })
    public void shouldReceiveClientSentDataWithMultipleFramesWithPartialWrites() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.multiple.streams/client/nukleus",
        "${streams}/client.sent.data.multiple.streams/client/source"
    })
    public void shouldReceiveClientSentDataWithMultipleStreams() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.multiple.streams.second.was.reset/client/nukleus",
        "${streams}/client.sent.data.multiple.streams.second.was.reset/client/source"
    })
    public void shouldReceiveClientSentDataWithMultipleStreamsSecondWasReset() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.then.end/client/nukleus",
        "${streams}/client.sent.data.then.end/client/source"
    })
    public void shouldReceiveClientSentDataAndEnd() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/echo.data/client/nukleus",
        "${streams}/echo.data/client/source"
    })
    public void shouldEchoData() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/server.close/client/nukleus",
        "${streams}/server.close/client/source"
    })
    public void shouldInitiateServerClose() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.close/client/nukleus",
        "${streams}/client.close/client/source"
    })
    public void shouldInitiateClientClose() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.after.end/client/nukleus",
        "${streams}/client.sent.data.after.end/client/source"
    })
    public void shouldResetClientSentDataAfterClose() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/output/new/nukleus",
        "${route}/output/new/controller",
        "${streams}/client.sent.data.received.reset/client/nukleus",
        "${streams}/client.sent.data.received.reset/client/source"
    })
    public void shouldResetClientSentDataExceedingWindow() throws Exception
    {
        k3po.start();
        k3po.awaitBarrier("ROUTED_OUTPUT");
        k3po.notifyBarrier("ROUTED_INPUT");
        k3po.finish();
    }
}

package com.example.wtf_workshop.api;

import com.example.wtf_workshop.api.requests.ManageAgentRequest;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;


public class SetupAgentTest extends BaseApiTest{
    @Test(groups = {"Setup"})
    public void authTeamCityAgentTest() {
        var agentRequester = new ManageAgentRequest(Specifications.superUserSpec());

        await().atMost(30, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() -> {
            var agents = agentRequester.get_agents("?locator=connected:true,authorized:false");
            if (agents.getCount() == 0) {
                return false;
            }
            return true;
        });

        agentRequester.auth_agent("true");
    }
}

package com.example.wtf_workshop.api;

import com.example.wtf_workshop.api.requests.ManageAgentRequest;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.Test;


public class SetupAgentTest extends BaseApiTest{
    @Test(groups = {"Setup"})
    public void authTeamCityAgentTest() {
        new ManageAgentRequest(Specifications.superUserSpec()).auth_agent("true");
    }
}

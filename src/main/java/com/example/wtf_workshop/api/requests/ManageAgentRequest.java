package com.example.wtf_workshop.api.requests;

import com.example.wtf_workshop.api.config.Config;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class ManageAgentRequest {
    private static final String AGENT_AUTH_URL_TEMPLATE = "/app/rest/agents/%s/authorized";
    private RequestSpecification spec;

    public ManageAgentRequest(RequestSpecification spec) {
        this.spec = spec;
    }

    public void auth_agent() {
        String agentAuthUrl = String.format(AGENT_AUTH_URL_TEMPLATE, "ip_" + Config.getProperty("agent_ip"));
        RestAssured.given()
                .spec(spec)
                .put(agentAuthUrl)
                .then().assertThat().statusCode(HttpStatus.SC_OK);
    }
}
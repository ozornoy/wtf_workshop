package com.example.wtf_workshop.api;


import com.example.wtf_workshop.api.models.*;
import com.example.wtf_workshop.api.requests.CheckedRequests;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.Test;

import static com.example.wtf_workshop.api.enums.Endpoint.*;
import static org.testng.AssertJUnit.assertEquals;

@Test(groups = {"Regression"})
public class ProjectTest extends BaseApiTest {
    @Test(description = "User should be able to search for a project by its name", groups = {"Positive", "Search"})
    public void userSearchProjectByNameTest() {
        var userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        var project = userCheckedRequests.<Project>getRequest(PROJECTS).create(testData.getProject());

        var projects = userCheckedRequests.<Projects>getRequest(PROJECTS_SEARCH).search("name:" + testData.getProject().getName());
        assertEquals("Project not found", project, projects.getProject().get(0));
    }
}

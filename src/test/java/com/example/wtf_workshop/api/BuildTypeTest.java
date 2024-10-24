package com.example.wtf_workshop.api;


import com.example.wtf_workshop.api.models.*;
import com.example.wtf_workshop.api.requests.CheckedRequests;
import com.example.wtf_workshop.api.requests.UncheckedRequests;
import com.example.wtf_workshop.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.example.wtf_workshop.api.enums.Endpoint.*;
import static com.example.wtf_workshop.api.generators.TestDataGenerator.generate;

@Test(groups = {"Regression"})
public class BuildTypeTest extends BaseApiTest {
    @Test(description = "User should be able to create build type", groups = {"Positive", "CRUD"})
    public void userCreatesBuildTypeTest() {
        var userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.getRequest(PROJECTS).create(testData.getProject());

        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var createdBuildType = userCheckedRequests.<BuildType>getRequest(BUILD_TYPES).read(testData.getBuildType().getId());
        softy.assertEquals(testData.getBuildType().getName(), createdBuildType.getName(), "Build type name is not correct");
    }

    @Test(description = "User should not be able to create two build types with the same id", groups = {"Negative", "CRUD"})
    public void userCreatesTwoBuildTypesWithTheSameIdTest() {
        var userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        var buildTypeWithSameId = generate(Arrays.asList(testData.getProject()), BuildType.class, testData.getBuildType().getId());
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.getRequest(PROJECTS).create(testData.getProject());

        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        new UncheckedRequests(Specifications.authSpec(testData.getUser())).getRequest(BUILD_TYPES)
                .create(buildTypeWithSameId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("The build configuration / template ID \"%s\" is already used by another configuration or template".formatted(testData.getBuildType().getId())));
    }

    @Test(description = "Project admin should be able to create build type for their project", groups = {"Positive", "Roles"})
    public void projectAdminCreatesBuildTypeTest() {
        var checkedUserRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        testData.getUser().setRoles(generate(Roles.class, "PROJECT_ADMIN", "p:" + testData.getProject().getId()));

        superUserCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        checkedUserRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var createdBuildType = checkedUserRequests.<BuildType>getRequest(BUILD_TYPES).read(testData.getBuildType().getId());
        softy.assertEquals(testData.getBuildType().getName(), createdBuildType.getName(), "Build type name is correct");
    }

    @Test(description = "Project admin should not be able to create build type for not their project", groups = {"Negative", "Roles"})
    public void projectAdminCreateBuildTypeForAnotherUserProjectTest() {
        var project1 = testData.getProject();
        var project2 = generate(Project.class);
        var buildTypeOfProject2 = generate(Arrays.asList(project2), BuildType.class);
        var userWithAccessToProject1 = testData.getUser();
        userWithAccessToProject1.setRoles(generate(Roles.class, "PROJECT_ADMIN", "p:" + project1.getId()));

        superUserCheckedRequests.getRequest(PROJECTS).create(project1);
        superUserCheckedRequests.getRequest(PROJECTS).create(project2);
        superUserCheckedRequests.getRequest(USERS).create(userWithAccessToProject1);

        new UncheckedRequests(Specifications.authSpec(userWithAccessToProject1))
                .getRequest(BUILD_TYPES).create(buildTypeOfProject2)
                .then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .body(Matchers.containsString("You do not have enough permissions to edit project with id: " + project2.getId()));
    }
}

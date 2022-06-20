package tests.api;

import configuration.ReadProperties;
import helpers.CaseHelper;
import helpers.MilestoneHelper;
import helpers.ProjectHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Case;
import models.Milestone;
import models.Project;
import models.ProjectType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    public CaseHelper caseHelper;
    public MilestoneHelper milestoneHelper;
    public ProjectHelper projectHelper;

    public Case expectedCase;
    public Milestone expectedMilestone;
    public Project expectedProject;

    @BeforeClass
    public void setupEnv() {
        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());

        expectedProject = Project.builder()
                .name("WP_Test_03")
                .suiteMode(ProjectType.MULTIPLE_SUITE_MODE)
                .build();

        caseHelper = new CaseHelper();
        milestoneHelper = new MilestoneHelper();
        projectHelper = new ProjectHelper();
    }

    @AfterClass
    public void teardownEnv() {
        RestAssured.reset();
    }
}

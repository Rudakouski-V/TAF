package tests.api;

import configuration.ReadProperties;
import helpers.ProjectHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Project;
import models.ProjectType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestRailBaseApiTest {

    public Project baseProject;
    public ProjectHelper projectHelper = new ProjectHelper();

    @BeforeTest
    public void setupEnv() {
        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    @BeforeClass
    public void setupBaseTestProject() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base test project");
        jsonAsMap.put("announcement", "[AUTO TEST] Base test project announcement");
        jsonAsMap.put("show_announcement", true);
        jsonAsMap.put("suite_mode", ProjectType.SINGLE_SUITE_MODE);

        baseProject = projectHelper.addProject(jsonAsMap);
    }

    protected void loginAsNoAccessUser() {
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());
    }

    protected void loginAsAccessUser() {
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    @AfterClass
    public void deleteBaseTestProject() {
        projectHelper.deleteProject(baseProject.getProjectId());
    }

    @AfterTest
    public void teardownEnv() {
        RestAssured.reset();
    }
}
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
    //todo remove it
    public static final int BASE_TEST_PROJECT_ID = 76;

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
        //todo test with it in final
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("name", "[AUTO TEST] Base test project");
//        jsonAsMap.put("announcement", "[AUTO TEST] Base test project announcement");
//        jsonAsMap.put("show_announcement", true);
//        jsonAsMap.put("suite_mode", ProjectType.SINGLE_SUITE_MODE);
//
//        baseProject = projectHelper.addProject(jsonAsMap);
    }

    protected void setupNoAccessUser(){
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());
    }

    protected void resetNoAccessUser(){
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    @AfterClass
    public void deleteBaseTestProject() {
        //todo test with it in final
//        projectHelper.deleteProject(baseProject.getProjectId());
    }

    @AfterTest
    public void teardownEnv() {
        RestAssured.reset();
    }
}
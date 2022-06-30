package tests.api;

import configuration.ReadProperties;
import helpers.ProjectHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Project;
import models.ProjectType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestRailBaseApiTest {

    public static Project baseProject;
    public ProjectHelper projectHelper = new ProjectHelper();

    @BeforeTest
    public void createTempProject() {
        setupEnv();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base test project");
        jsonAsMap.put("announcement", "[AUTO TEST] Base test project announcement");
        jsonAsMap.put("show_announcement", true);
        jsonAsMap.put("suite_mode", ProjectType.SINGLE_SUITE_MODE);

        baseProject = projectHelper.addProject(jsonAsMap);
    }

    @BeforeMethod
    public void setupEnv() {
        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    @AfterMethod
    public void teardownEnv() {
        RestAssured.reset();
    }

    @AfterTest
    public void deleteTempProject() {
        setupEnv();

        projectHelper.deleteProject(baseProject.getProjectId());
    }


    protected void loginAsNoAccessUser() {
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());
    }
}

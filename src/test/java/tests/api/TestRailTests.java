package tests.api;

import configuration.ReadProperties;
import configuration.TestRailEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.Setter;
import models.Milestone;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestRailTests {

    @Setter
    private static int MILESTONE_ID;

    private static final String MILESTONE_NAME = "[AUTO TEST] Add milestone test";
    private static final String MILESTONE_DESCRIPTION = "Milestone description";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    @Test(groups = "Milestones tests")
    public void addMilestoneTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", MILESTONE_NAME);
        jsonAsMap.put("description", MILESTONE_DESCRIPTION);

        Milestone requestedMilestone = given()
                .body(jsonAsMap)
                .when()
                .post(TestRailEndpoints.POST_ADD_MILESTONE
                        + "/"
                        + TestRailEndpoints.RUDAKOUSKI_API_TESTS_PROJECT_ID)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .extract().as(Milestone.class);

        Assert.assertEquals(requestedMilestone.getName(), MILESTONE_NAME);
        Assert.assertEquals(requestedMilestone.getDescription(), MILESTONE_DESCRIPTION);
        Assert.assertEquals(requestedMilestone.getProjectId(), TestRailEndpoints.RUDAKOUSKI_API_TESTS_PROJECT_ID);

        setMILESTONE_ID(requestedMilestone.getMilestoneId());
    }

    @Test(groups = "Milestones tests", dependsOnMethods = "addMilestoneTest")
    public void getMilestoneTest() {
        Milestone requestedMilestone = given()
                .when()
                .get(TestRailEndpoints.GET_GET_MILESTONE
                        + "/"
                        + MILESTONE_ID)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .extract().as(Milestone.class);

        Assert.assertEquals(requestedMilestone.getName(), MILESTONE_NAME);
        Assert.assertEquals(requestedMilestone.getDescription(), MILESTONE_DESCRIPTION);
        Assert.assertEquals(requestedMilestone.getProjectId(), TestRailEndpoints.RUDAKOUSKI_API_TESTS_PROJECT_ID);
    }

    @Test(groups = "Milestones tests")
    public void getMilestonesTest() {
        given()
                .when()
                .get(TestRailEndpoints.GET_GET_MILESTONES
                        + "/"
                        + TestRailEndpoints.RUDAKOUSKI_API_TESTS_PROJECT_ID)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("offset", equalTo(0))
                .body("limit", equalTo(250))
                .body("size", equalTo(2))
                .body("milestones", arrayContaining(Milestone.class));

        //.log().body()
        //.extract().as(Milestone.class);
    }

    @Test
    public void updateMilestoneTest() {

    }

    @Test
    public void deleteMilestoneTest() {

    }
    //todo group packages (cases and milestones)
    //todo awesome-viewable strings here
}

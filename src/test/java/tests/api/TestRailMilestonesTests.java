package tests.api;

import com.google.gson.Gson;
import configuration.ReadProperties;
import helpers.MilestoneHelper;
import io.restassured.RestAssured;
import models.Milestone;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestRailMilestonesTests extends TestRailBaseApiTest {
    //todo remove it
    private static final int MILESTONE_ID = 60;
    public Gson gson = new Gson();

    public Milestone baseMilestone;
    public MilestoneHelper milestoneHelper = new MilestoneHelper();


    @Test(priority = 0)
    public void addMilestoneSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base milestone name");
        jsonAsMap.put("description", "[AUTO TEST] Base milestone announcement");

        //todo
        baseMilestone = milestoneHelper.addMilestone(BASE_TEST_PROJECT_ID, jsonAsMap);

        Assert.assertEquals(baseMilestone.getName(), jsonAsMap.get("name"));
        Assert.assertEquals(baseMilestone.getDescription(), jsonAsMap.get("description"));

//        expectedMilestone = Milestone.builder()
//                .name("[AUTO TEST] Base milestone name")
//                .description("[AUTO TEST] Base milestone announcement")
//                .build();
//
//        Assert.assertEquals(
//                milestoneHelper.addMilestone(BASE_TEST_PROJECT_ID, gson.toJson(expectedMilestone)),
//                expectedMilestone);
    }

    @Test(priority = 1)
    public void addMilestoneUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base milestone name");

        Assert.assertEquals(milestoneHelper.addMilestoneResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 1)
    public void addMilestoneNoAccessTest() {
        setupNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base milestone name");

        //todo
        Assert.assertEquals(milestoneHelper.addMilestoneResponse(BASE_TEST_PROJECT_ID, jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        resetNoAccessUser();
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 2)
    public void getMilestoneSuccessfulTest() {
        Assert.assertEquals(milestoneHelper.getMilestone(baseMilestone.getMilestoneId()), baseMilestone);
    }

    @Test(priority = 2)
    public void getMilestoneUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.getMilestoneResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 2)
    public void getMilestoneNoAccessTest() {
        setupNoAccessUser();

        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());

        Assert.assertEquals(milestoneHelper.getMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        resetNoAccessUser();
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 3)
    public void getMilestonesSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Sub base milestone name");
        jsonAsMap.put("description", "[AUTO TEST] Sub base milestone announcement");
        jsonAsMap.put("parent_id", baseMilestone.getMilestoneId());

        //todo
        Milestone subBaseMilestone = milestoneHelper.addMilestone(BASE_TEST_PROJECT_ID, jsonAsMap);

        //todo
        //Assert.assertEquals(milestoneHelper.getMilestonesResponse(BASE_TEST_PROJECT_ID).getBody().jsonPath().getInt("size"), 2);
        Assert.assertEquals(milestoneHelper.getMilestones(BASE_TEST_PROJECT_ID).get(1), baseMilestone);
        Assert.assertEquals(milestoneHelper.getMilestones(BASE_TEST_PROJECT_ID).get(0), subBaseMilestone);
    }

    @Test
    public void one(){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Sub base milestone name");
        jsonAsMap.put("description", "[AUTO TEST] Sub base milestone announcement");
        jsonAsMap.put("parent_id", baseMilestone.getMilestoneId());

        //todo
        Milestone subBaseMilestone = milestoneHelper.addMilestone(BASE_TEST_PROJECT_ID, jsonAsMap);

        //todo
        //Assert.assertEquals(milestoneHelper.getMilestonesResponse(BASE_TEST_PROJECT_ID).getBody().jsonPath().getInt("size"), 2);
        Assert.assertEquals(milestoneHelper.getMilestones(BASE_TEST_PROJECT_ID).get(1), baseMilestone);
        Assert.assertEquals(milestoneHelper.getMilestones(BASE_TEST_PROJECT_ID).get(0), subBaseMilestone);
    }

    @Test(priority = 3)
    public void getMilestonesUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.getMilestonesResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 3)
    public void getMilestonesNoAccessTest() {
        setupNoAccessUser();

        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());

        //todo
        Assert.assertEquals(milestoneHelper.getMilestonesResponse(BASE_TEST_PROJECT_ID).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        resetNoAccessUser();
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 4)
    public void updateMilestoneSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("is_completed", !baseMilestone.isCompleted());
        jsonAsMap.put("is_started", !baseMilestone.isStarted());
        jsonAsMap.put("start_on", System.currentTimeMillis());

        Milestone updatedMilestone = milestoneHelper.updateMilestone(baseMilestone.getMilestoneId(), jsonAsMap);

        Assert.assertEquals(updatedMilestone.isCompleted(), jsonAsMap.get("is_completed"));
        Assert.assertEquals(updatedMilestone.isStarted(), jsonAsMap.get("is_started"));
        Assert.assertEquals(updatedMilestone.getStartOn(), jsonAsMap.get("start_on"));
    }

    @Test(priority = 4)
    public void updateMilestoneUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.updateMilestoneResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 4)
    public void updateMilestoneNoAccessTest() {
        setupNoAccessUser();

        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());

        Assert.assertEquals(milestoneHelper.updateMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        resetNoAccessUser();
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 6)
    public void deleteMilestoneSuccessfulTest() {
        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 5)
    public void deleteMilestoneUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 5)
    public void deleteMilestoneNoAccessTest() {
        setupNoAccessUser();

        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.noAccessUsername(), ReadProperties.noAccessPassword());

        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        resetNoAccessUser();
    }
}

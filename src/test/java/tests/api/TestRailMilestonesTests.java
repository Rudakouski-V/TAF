package tests.api;

import helpers.MilestoneHelper;
import helpers.ProjectHelper;
import models.Milestone;
import models.Project;
import models.ProjectType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class TestRailMilestonesTests extends TestRailBaseApiTest {

    public Milestone baseMilestone;
    public MilestoneHelper milestoneHelper = new MilestoneHelper();

    @Test(priority = 1)
    public void addMilestoneSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base first milestone name");
        jsonAsMap.put("description", "[AUTO TEST] Base milestone description");

        baseMilestone = milestoneHelper.addMilestone(baseProject.getProjectId(), jsonAsMap);

        Assert.assertEquals(baseMilestone.getName(), jsonAsMap.get("name"));
        Assert.assertEquals(baseMilestone.getDescription(), jsonAsMap.get("description"));
    }

    @Test(priority = 1)
    public void addMilestoneUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base milestone name");

        Assert.assertEquals(milestoneHelper.addMilestoneResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 1)
    public void addMilestoneNoAccessTest() {
        loginAsNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base milestone name");

        Assert.assertEquals(milestoneHelper.addMilestoneResponse(baseProject.getProjectId(), jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);
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
        loginAsNoAccessUser();

        Assert.assertEquals(milestoneHelper.getMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 3)
    public void getMilestonesSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base second milestone name");
        jsonAsMap.put("description", "[AUTO TEST] Base milestone announcement");

        Milestone secondBaseMilestone = milestoneHelper.addMilestone(baseProject.getProjectId(), jsonAsMap);

        milestoneHelper.getMilestonesResponse(baseProject.getProjectId()).then().body("size", is(2));
        Assert.assertEquals(milestoneHelper.getMilestones(baseProject.getProjectId()).size(), 2);
        Assert.assertEquals(milestoneHelper.getMilestones(baseProject.getProjectId()).get(0), baseMilestone);
        Assert.assertEquals(milestoneHelper.getMilestones(baseProject.getProjectId()).get(1), secondBaseMilestone);
    }

    @Test(priority = 3)
    public void getMilestonesUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.getMilestonesResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 3)
    public void getMilestonesNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(milestoneHelper.getMilestonesResponse(baseProject.getProjectId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 4)
    public void updateMilestoneSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        // Date&time - as UNIX timestamp (long), converted to int
        jsonAsMap.put("start_on", (int) Instant.now().getEpochSecond());
        jsonAsMap.put("is_started", !(baseMilestone.isStarted()));
        jsonAsMap.put("is_completed", !(baseMilestone.isCompleted()));

        baseMilestone = milestoneHelper.updateMilestone(baseMilestone.getMilestoneId(), jsonAsMap);

        Assert.assertEquals(baseMilestone.getStartOn(), jsonAsMap.get("start_on"));
        Assert.assertEquals(baseMilestone.isStarted(), jsonAsMap.get("is_started"));
        Assert.assertEquals(baseMilestone.isCompleted(), jsonAsMap.get("is_completed"));
    }

    @Test(priority = 4)
    public void updateMilestoneUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.updateMilestoneResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 4)
    public void updateMilestoneNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(milestoneHelper.updateMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }


    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 5)
    public void deleteMilestoneSuccessfulTest() {
        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(priority = 5)
    public void deleteMilestoneUnsuccessfulTest() {
        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addMilestoneSuccessfulTest", priority = 5)
    public void deleteMilestoneNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(milestoneHelper.deleteMilestoneResponse(baseMilestone.getMilestoneId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }
}

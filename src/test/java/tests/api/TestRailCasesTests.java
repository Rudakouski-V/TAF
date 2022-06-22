package tests.api;

import configuration.TestRailEndpoints;
import helpers.CaseHelper;
import models.Case;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestRailCasesTests extends TestRailBaseApiTest {

    public int SECTION_ID;
    public Case baseCase;
    public Case secondBaseCase;
    public CaseHelper caseHelper = new CaseHelper();

    @Test(priority = 0)
    public void setupBaseSectionId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "[AUTO TEST] Base section");

        SECTION_ID = given()
                .pathParam("project_id", baseProject.getProjectId())
                .body(jsonAsMap)
                .when()
                .post(TestRailEndpoints.ADD_SECTION)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getInt("id");
    }


    @Test(dependsOnMethods = "setupBaseSectionId", priority = 1)
    public void addCaseSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] Base first case title");

        baseCase = caseHelper.addCase(SECTION_ID, jsonAsMap);

        Assert.assertEquals(baseCase.getTitle(), jsonAsMap.get("title"));
    }

    @Test(priority = 1)
    public void addCaseUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] Base first case title");

        Assert.assertEquals(caseHelper.addCaseResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "setupBaseSectionId", priority = 1)
    public void addCaseNoAccessTest() {
        loginAsNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] Base first case title");

        Assert.assertEquals(caseHelper.addCaseResponse(SECTION_ID, jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 2)
    public void getCaseSuccessfulTest() {
        Assert.assertEquals(caseHelper.getCase(baseCase.getCaseId()), baseCase);
    }

    @Test(priority = 2)
    public void getCaseUnsuccessfulTest() {
        Assert.assertEquals(caseHelper.getCaseResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 2)
    public void getCaseNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(caseHelper.getCaseResponse(baseCase.getCaseId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 3)
    public void getCasesSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] Base second case title");

        secondBaseCase = caseHelper.addCase(SECTION_ID, jsonAsMap);

        caseHelper.getCasesResponse(baseProject.getProjectId(), baseCase.getSuiteId()).then().body("size", is(2));
        Assert.assertEquals(caseHelper.getCases(baseProject.getProjectId(), baseCase.getSuiteId()).size(), 2);
        Assert.assertEquals(caseHelper.getCases(baseProject.getProjectId(), baseCase.getSuiteId()).get(0), baseCase);
        Assert.assertEquals(caseHelper.getCases(baseProject.getProjectId(), baseCase.getSuiteId()).get(1), secondBaseCase);
    }

    @Test(priority = 3)
    public void getCasesUnsuccessfulTest() {
        Assert.assertEquals(caseHelper.getCasesResponse(Integer.MAX_VALUE, Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 3)
    public void getCasesNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(caseHelper.getCasesResponse(baseProject.getProjectId(), baseCase.getSuiteId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 4)
    public void updateCaseSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] UPDATED Base first case title");
        jsonAsMap.put("priority_id", 1);
        jsonAsMap.put("estimate", "55m");

        baseCase = caseHelper.updateCase(baseCase.getCaseId(), jsonAsMap);

        Assert.assertEquals(baseCase.getTitle(), jsonAsMap.get("title"));
        Assert.assertEquals(baseCase.getPriorityId(), jsonAsMap.get("priority_id"));
        Assert.assertEquals(baseCase.getEstimate(), jsonAsMap.get("estimate"));
    }

    @Test(priority = 4)
    public void updateCaseUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] UPDATED Base first case title");

        Assert.assertEquals(caseHelper.updateCaseResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 4)
    public void updateCaseNoAccessTest() {
        loginAsNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] UPDATED Base first case title");

        Assert.assertEquals(caseHelper.updateCaseResponse(baseCase.getCaseId(), jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(dependsOnMethods = {"addCaseSuccessfulTest", "getCasesSuccessfulTest"}, priority = 5)
    public void updateCasesSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("priority_id", 3);
        jsonAsMap.put("estimate", "20m");

        List<Case> cases = caseHelper.updateCases(baseCase.getSuiteId(), jsonAsMap);
        baseCase = cases.get(0);
        secondBaseCase = cases.get(1);

        Assert.assertEquals(cases.size(), 2);
        Assert.assertEquals(baseCase.getPriorityId(), jsonAsMap.get("priority_id"));
        Assert.assertEquals(baseCase.getEstimate(), jsonAsMap.get("estimate"));
        Assert.assertEquals(secondBaseCase.getPriorityId(), jsonAsMap.get("priority_id"));
        Assert.assertEquals(secondBaseCase.getEstimate(), jsonAsMap.get("estimate"));
    }

    @Test(priority = 5)
    public void updateCasesUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("priority_id", 3);

        Assert.assertEquals(caseHelper.updateCasesResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = {"addCaseSuccessfulTest", "getCasesSuccessfulTest"}, priority = 5)
    public void updateCasesNoAccessTest() {
        loginAsNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("priority_id", 3);

        Assert.assertEquals(caseHelper.updateCasesResponse(baseCase.getSuiteId(), jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(priority = 6)
    public void deleteCaseSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("title", "[AUTO TEST] Create-and-delete case title");

        Assert.assertEquals(
                caseHelper.deleteCaseResponse(caseHelper.addCase(SECTION_ID, jsonAsMap).getCaseId()).getStatusCode(),
                HttpStatus.SC_OK);
    }

    @Test(priority = 6)
    public void deleteCaseUnsuccessfulTest() {
        Assert.assertEquals(caseHelper.deleteCaseResponse(Integer.MAX_VALUE).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addCaseSuccessfulTest", priority = 6)
    public void deleteCaseNoAccessTest() {
        loginAsNoAccessUser();

        Assert.assertEquals(caseHelper.deleteCaseResponse(baseCase.getCaseId()).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }


    @Test(dependsOnMethods = {"addCaseSuccessfulTest", "getCasesSuccessfulTest"}, priority = 8)
    public void deleteCasesSuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("project_id", baseProject.getProjectId());

        Assert.assertEquals(caseHelper.deleteCasesResponse(baseCase.getSuiteId(), jsonAsMap).getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = {"addCaseSuccessfulTest", "getCasesSuccessfulTest"}, priority = 7)
    public void deleteCasesUnsuccessfulTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("project_id", baseProject.getProjectId());

        Assert.assertEquals(caseHelper.deleteCasesResponse(Integer.MAX_VALUE, jsonAsMap).getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = {"addCaseSuccessfulTest", "getCasesSuccessfulTest"}, priority = 7)
    public void deleteCasesNoAccessTest() {
        loginAsNoAccessUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("case_ids", new int[]{baseCase.getCaseId(), secondBaseCase.getCaseId()});
        jsonAsMap.put("project_id", baseProject.getProjectId());

        Assert.assertEquals(caseHelper.deleteCasesResponse(baseCase.getSuiteId(), jsonAsMap).getStatusCode(), HttpStatus.SC_FORBIDDEN);

        loginAsAccessUser();
    }
}

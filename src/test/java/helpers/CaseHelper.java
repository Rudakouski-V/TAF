package helpers;

import configuration.TestRailEndpoints;
import io.restassured.response.Response;
import models.Case;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CaseHelper {

    public Case getCase(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .get(TestRailEndpoints.GET_CASE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response getCaseResponse(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .get(TestRailEndpoints.GET_CASE);

    }

    public List<Case> getCases(int project_id, int suite_id) {
        return given()
                .pathParams("project_id", project_id, "suite_id", suite_id)
                .get(TestRailEndpoints.GET_CASES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("cases", Case.class);
    }

    public Response getCasesResponse(int project_id, int suite_id) {
        return given()
                .pathParams("project_id", project_id, "suite_id", suite_id)
                .get(TestRailEndpoints.GET_CASES);
    }

    //todo get_history_for_case x2

    public Case addCase(int section_id) {
        return given()
                .pathParam("section_id", section_id)
                .post(TestRailEndpoints.ADD_CASE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response addCaseResponse(int section_id) {
        return given()
                .pathParam("section_id", section_id)
                .post(TestRailEndpoints.ADD_CASE);
    }

    public Response copyCasesToSectionResponse(int section_id) {
        return given()
                .pathParam("section_id", section_id)
                .post(TestRailEndpoints.COPY_CASES_TO_SECTION);
    }

    public Case updateCase(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .post(TestRailEndpoints.UPDATE_CASE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response updateCaseResponse(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .post(TestRailEndpoints.UPDATE_CASE);
    }

    public Case updateCases(int suite_id) {
        return given()
                .pathParam("suite_id", suite_id)
                .post(TestRailEndpoints.UPDATE_CASES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response updateCasesResponse(int suite_id) {
        return given()
                .pathParam("suite_id", suite_id)
                .post(TestRailEndpoints.UPDATE_CASES);
    }

    public Response moveCasesToSectionResponse(int section_id) {
        return given()
                .pathParam("section_id", section_id)
                .post(TestRailEndpoints.MOVE_CASES_TO_SECTION);
    }

    public Response deleteCaseResponse(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .post(TestRailEndpoints.DELETE_CASE);
    }

    public Response deleteCasesResponse(int suite_id) {
        return given()
                .pathParam("suite_id", suite_id)
                .post(TestRailEndpoints.DELETE_CASES);
    }
}
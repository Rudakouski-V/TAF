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
                .when()
                .get(TestRailEndpoints.GET_CASES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("cases", Case.class);
    }

    public Response getCasesResponse(int project_id, int suite_id) {
        return given()
                .pathParams("project_id", project_id, "suite_id", suite_id)
                .when()
                .get(TestRailEndpoints.GET_CASES);
    }

    public Case addCase(int section_id, Object request) {
        return given()
                .pathParam("section_id", section_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.ADD_CASE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response addCaseResponse(int section_id, Object request) {
        return given()
                .pathParam("section_id", section_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.ADD_CASE);
    }

    public Case updateCase(int case_id, Object request) {
        return given()
                .pathParam("case_id", case_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.UPDATE_CASE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Case.class);
    }

    public Response updateCaseResponse(int case_id, Object request) {
        return given()
                .pathParam("case_id", case_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.UPDATE_CASE);
    }

    public List<Case> updateCases(int suite_id, Object request) {
        return given()
                .pathParam("suite_id", suite_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.UPDATE_CASES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("updated_cases", Case.class);
    }

    public Response updateCasesResponse(int suite_id, Object request) {
        return given()
                .pathParam("suite_id", suite_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.UPDATE_CASES);
    }

    public Response deleteCaseResponse(int case_id) {
        return given()
                .pathParam("case_id", case_id)
                .post(TestRailEndpoints.DELETE_CASE);
    }

    public Response deleteCasesResponse(int suite_id, Object request) {
        return given()
                .pathParam("suite_id", suite_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.DELETE_CASES);
    }
}
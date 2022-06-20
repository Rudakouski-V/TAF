package helpers;

import configuration.TestRailEndpoints;
import io.restassured.response.Response;
import models.Milestone;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class MilestoneHelper {

    public Milestone getMilestone(int milestone_id) {
        return given()
                .pathParam("milestone_id", milestone_id)
                .get(TestRailEndpoints.GET_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Milestone.class);
    }

    public Response getMilestoneResponse(int milestone_id) {
        return given()
                .pathParam("milestone_id", milestone_id)
                .get(TestRailEndpoints.GET_MILESTONE);
    }

    public List<Milestone> getMilestones(int project_id) {
        return given()
                .pathParam("project_id", project_id)
                .get(TestRailEndpoints.GET_MILESTONES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("milestones", Milestone.class);
    }

    public Response getMilestonesResponse(int project_id) {
        return given()
                .pathParam("project_id", project_id)
                .get(TestRailEndpoints.GET_MILESTONES);
    }

    public Milestone addMilestone(int project_id, Object request) {
        return given()
                .pathParam("project_id", project_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.ADD_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Milestone.class);
    }

    public Response addMilestoneResponse(int project_id, Object request) {
        return given()
                .pathParam("project_id", project_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.ADD_MILESTONE);
    }

    public Milestone updateMilestone(int milestone_id, Object request) {
        return given()
                .pathParam("milestone_id", milestone_id)
                .body(request)
                .when()
                .post(TestRailEndpoints.UPDATE_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Milestone.class);
    }

    public Response updateMilestoneResponse(int milestone_id) {
        return given()
                .pathParam("milestone_id", milestone_id)
                .post(TestRailEndpoints.UPDATE_MILESTONE);
    }

    public Response deleteMilestoneResponse(int milestone_id) {
        return given()
                .pathParam("milestone_id", milestone_id)
                .post(TestRailEndpoints.DELETE_MILESTONE);
    }
}

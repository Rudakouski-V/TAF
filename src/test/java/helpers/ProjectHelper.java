package helpers;

import configuration.Endpoints;
import configuration.TestRailEndpoints;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectHelper {

    public void addProject(String request) {
        given()
                .body(request)
                .post(TestRailEndpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public Project getProject(int project_id) {
        return given()
                .pathParam("project_id", project_id)
                .get(TestRailEndpoints.GET_PROJECT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    public List<Project> getAllProjects() {
        return given()
                .get(TestRailEndpoints.GET_PROJECTS)
                .getBody()
                .jsonPath().getList("projects", Project.class);
    }

    public void deleteProject(int projectId) {
        given()
                .pathParam("project_id", projectId)
                .post(TestRailEndpoints.DELETE_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}

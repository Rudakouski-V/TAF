package helpers;

import configuration.Endpoints;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectHelper {

    public Project getProject(int project_id) {
        return given()
                .pathParam("project_id", project_id)
                .get(Endpoints.GET_PROJECT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Project.class);
    }

    public Response getProjectResponse(int project_id) {
        return given()
                .pathParam("project_id", project_id)
                .get(Endpoints.GET_PROJECT);
    }

    public List<Project> getAllProjects() {
        return given()
                .get(Endpoints.GET_PROJECTS)
                .getBody()
                .jsonPath().getList("projects", Project.class);
    }

    public Response getAllProjectsResponse() {
        return null;
    }
}

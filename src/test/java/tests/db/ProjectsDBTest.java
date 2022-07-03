package tests.db;

import com.google.gson.Gson;
import configuration.ReadProperties;
import helpers.ProjectHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Project;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectsDBTest extends BaseProjectsDBTest {

    Logger logger = LoggerFactory.getLogger(ProjectsDBTest.class);

    public ProjectHelper projectHelper = new ProjectHelper();

    Gson gson = new Gson();

    @BeforeClass
    public void setupConnectionToTestRail() {
        logger.info("...Configuring connection to TestRail...");

        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());

        logger.info("Connection to TestRail configured successful!");
    }

    @Test
    public void testRailProjectsCreationUsingApiAndDbSuccessful() {
        logger.info("...Test is started...");

        ArrayList<Project> projectsFromDB = new ArrayList<>();

        ResultSet rs = projectsTable.getProjects();

        logger.info("...Getting instances from DB...");
        try {
            while (rs.next()) {
                rs.getInt("id");

                String name = rs.getString("name");
                String announcement = rs.getString("announcement");
                boolean showAnnouncement = rs.getBoolean("show_announcement");
                int suiteMode = rs.getInt("suite_mode");

                logger.info(
                        "Instance from DB {" +
                        "name: " + name +
                        ", announcement: " + announcement +
                        ", showAnnouncement: " + showAnnouncement +
                        ", suiteMode: " + suiteMode + "}");

                Project tmpProject = Project.builder()
                        .name(name)
                        .announcement(announcement)
                        .showAnnouncement(showAnnouncement)
                        .suiteMode(suiteMode)
                        .build();

                projectsFromDB.add(tmpProject);

                logger.info("...Creating the project into TestRail from this instance...");
                projectHelper.addProject(gson.toJson(tmpProject));
                logger.info("The project in TestRail from this instance created successful!");
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        }
        logger.info("All instances from DB have been given successful!");

        logger.info("...Getting instances from TestRail API...");
        List<Project> projectsFromTestRail = projectHelper.getAllProjects();
        logger.info("All instances from TestRail API have been given successful!");

        logger.info("...Deleting our temp projects from TestRail...");
        for (int i = 0; i < projectsFromDB.size(); i++) {
            projectHelper.deleteProject(projectsFromTestRail.get(projectsFromTestRail.size() - i - 1).projectId);
        }
        logger.info("All our temp projects from TestRail have been deleted successful!");

        logger.info("...Asserting results...");
        for (int i = 0; i < projectsFromDB.size(); i++) {
            Assert.assertEquals(
                    projectsFromTestRail.get(projectsFromTestRail.size() - i - 1),
                    projectsFromDB.get(projectsFromDB.size() - i - 1));
        }
        logger.info("Results have been asserted successful!");

        logger.info("Test is fully completed!");
    }

    @AfterClass
    public void teardownConnectionToTestRail() {
        RestAssured.reset();
    }
}

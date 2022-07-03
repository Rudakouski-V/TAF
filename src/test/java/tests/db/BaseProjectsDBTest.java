package tests.db;

import dbEntities.ProjectsTable;
import models.ProjectType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import services.DataBaseService;

public class BaseProjectsDBTest {

    public DataBaseService dataBaseService;

    ProjectsTable projectsTable;

    @BeforeTest
    public void setupConnection() {
        dataBaseService = new DataBaseService();

        projectsTable = new ProjectsTable(dataBaseService);

        projectsTable.dropTable();
        projectsTable.createProjectsTable();

        projectsTable.addProject(
                "[AUTO TEST] DB usage test 1",
                "DB usage test 1 announcement",
                true,
                ProjectType.SINGLE_SUITE_MODE);
        projectsTable.addProject(
                "[AUTO TEST] DB usage test 2",
                "DB usage test 2 announcement",
                false,
                ProjectType.SINGLE_SUITE_BASELINES);
    }

    @AfterTest
    public void closeConnection() {
        dataBaseService.closeConnection();
    }
}

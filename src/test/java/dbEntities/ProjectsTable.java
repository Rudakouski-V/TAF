package dbEntities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DataBaseService;

import java.sql.ResultSet;

public class ProjectsTable {
    Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    DataBaseService dataBaseService;

    public ProjectsTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void dropTable() {
        logger.info("...Deleting the projects`s table...");

        dataBaseService.executeSQL("DROP TABLE projects;");
    }

    public void createProjectsTable() {
        logger.info("...Creating the projects`s table...");

        String createTableProjectsSQL = "CREATE TABLE projects (" +
                "id SERIAL PRIMARY KEY, " +
                "name CHARACTER VARYING(50), " +
                "announcement CHARACTER VARYING(100), " +
                "show_announcement BOOLEAN, " +
                "suite_mode INTEGER" +
                ");";

        dataBaseService.executeSQL(createTableProjectsSQL);
    }

    public void addProject(String name, String announcement, boolean showAnnouncement, int suiteMode) {
        logger.info("...Adding a record to the projects`s table...");

        String insertTableProjectsSQL = "INSERT INTO public.projects(" +
                "name, announcement, show_announcement, suite_mode)" +
                "VALUES ('" + name + "', '" + announcement + "', '" + showAnnouncement + "', " + suiteMode + ");";

        dataBaseService.executeSQL(insertTableProjectsSQL);
    }

    public ResultSet getProjects() {
        return dataBaseService.executeQuery("SELECT * FROM public.projects ORDER BY id ASC;");
    }
}

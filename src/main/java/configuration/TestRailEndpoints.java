package configuration;

public final class TestRailEndpoints {
    public static final String GET_PROJECT = "/index.php?/api/v2/get_project/{project_id}";
    public static final String GET_PROJECTS = "/index.php?/api/v2/get_projects";
    public static final String ADD_PROJECT = "/index.php?/api/v2/add_project";
    public static final String DELETE_PROJECT = "/index.php?/api/v2/delete_project/{project_id}";

    public static final String GET_MILESTONE = "/index.php?/api/v2/get_milestone/{milestone_id}";
    public static final String GET_MILESTONES = "/index.php?/api/v2/get_milestones/{project_id}";
    public static final String ADD_MILESTONE = "/index.php?/api/v2/add_milestone/{project_id}";
    public static final String UPDATE_MILESTONE = "/index.php?/api/v2/update_milestone/{milestone_id}";
    public static final String DELETE_MILESTONE = "/index.php?/api/v2/delete_milestone/{milestone_id}";

    public static final String ADD_SECTION = "/index.php?/api/v2/add_section/{project_id}";

    public static final String GET_CASE = "/index.php?/api/v2/get_case/{case_id}";
    public static final String GET_CASES = "/index.php?/api/v2/get_cases/{project_id}&suite_id={suite_id}";
    public static final String ADD_CASE = "/index.php?/api/v2/add_case/{section_id}";
    public static final String UPDATE_CASE = "/index.php?/api/v2/update_case/{case_id}";
    public static final String UPDATE_CASES = "/index.php?/api/v2/update_cases/{suite_id}";
    public static final String DELETE_CASE = "/index.php?/api/v2/delete_case/{case_id}";
    public static final String DELETE_CASES = "/index.php?/api/v2/delete_cases/{suite_id}";
}

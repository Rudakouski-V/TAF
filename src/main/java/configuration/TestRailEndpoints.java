package configuration;

public class TestRailEndpoints {
    private static final String BASE_TESTRAIL_API_ENDPOINT = "/index.php?/api/v2";

    public static final int RUDAKOUSKI_API_TESTS_PROJECT_ID = 86;

    public static final String GET_GET_MILESTONE = BASE_TESTRAIL_API_ENDPOINT + "/get_milestone";
    public static final String GET_GET_MILESTONES = BASE_TESTRAIL_API_ENDPOINT + "/get_milestones";
    public static final String POST_ADD_MILESTONE = BASE_TESTRAIL_API_ENDPOINT + "/add_milestone";
    public static final String POST_UPDATE_MILESTONE = BASE_TESTRAIL_API_ENDPOINT + "/update_milestone";
    public static final String POST_DELETE_MILESTONE = BASE_TESTRAIL_API_ENDPOINT + "/delete_milestone";
}

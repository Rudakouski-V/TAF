package configuration;

public class ReqresInEndpoints {
    public static final String GET_LIST_USERS = "/api/users?page=2";
    public static final String GET_SINGLE_USER = "/api/users/2";
    public static final String GET_SINGLE_USER_NOT_FOUND = "/api/users/23";
    public static final String GET_LIST_RESOURCE = "/api/unknown";
    public static final String GET_SINGLE_RESOURCE = "/api/unknown/2";
    public static final String GET_SINGLE_RESOURCE_NOT_FOUND = "/api/unknown/23";
    public static final String POST_CREATE = "/api/users";
    public static final String PUT_UPDATE = "/api/users/2";
    public static final String PATCH_UPDATE = "/api/users/2";
    public static final String DELETE_DELETE = "/api/users/2";
    public static final String POST_REGISTER = "/api/register";
    public static final String POST_LOGIN = "/api/login";
    public static final String GET_DELAYED_RESPONSE = "/api/users?delay=3";
}

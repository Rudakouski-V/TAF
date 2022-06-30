package configuration;

public final class ReqresInEndpoints {
    public static final String LIST_USERS = "/api/users?page=2";
    public static final String SINGLE_USER = "/api/users/2";
    public static final String SINGLE_USER_NOT_FOUND = "/api/users/23";
    public static final String LIST_RESOURCE = "/api/unknown";
    public static final String SINGLE_RESOURCE = "/api/unknown/2";
    public static final String SINGLE_RESOURCE_NOT_FOUND = "/api/unknown/23";
    public static final String CREATE = "/api/users";
    public static final String UPDATE = "/api/users/2";
    public static final String DELETE = "/api/users/2";
    public static final String REGISTER = "/api/register";
    public static final String LOGIN = "/api/login";
    public static final String DELAYED_RESPONSE = "/api/users?delay=3";
}

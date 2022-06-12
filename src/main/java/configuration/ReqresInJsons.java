package configuration;

import io.restassured.path.json.JsonPath;

import java.io.File;

public class ReqresInJsons {
    public static final JsonPath GET_DELAYED_RESPONSE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getDelayedResponseExpectedResponse.json"));
    public static final JsonPath GET_LIST_RESOURCE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getListResourceExpectedResponse.json"));
    public static final JsonPath GET_LIST_USERS_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getListUsersExpectedResponse.json"));
    public static final JsonPath GET_SINGLE_RESOURCE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getSingleResourceExpectedResponse.json"));
    public static final JsonPath GET_SINGLE_RESOURCE_NOT_FOUND_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getSingleResourceNotFoundExpectedResponse.json"));
    public static final JsonPath GET_SINGLE_USER_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getSingleUserExpectedResponse.json"));
    public static final JsonPath GET_SINGLE_USER_NOT_FOUND_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/getSingleUserNotFoundExpectedResponse.json"));
    public static final JsonPath PATCH_UPDATE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/patchUpdateExpectedResponse.json"));
    public static final JsonPath PATCH_UPDATE_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/patchUpdateRequest.json"));
    public static final JsonPath POST_CREATE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postCreateExpectedResponse.json"));
    public static final JsonPath POST_CREATE_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postCreateRequest.json"));
    public static final JsonPath POST_LOGIN_SUCCESSFUL_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postLoginSuccessfulExpectedResponse.json"));
    public static final JsonPath POST_LOGIN_SUCCESSFUL_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postLoginSuccessfulRequest.json"));
    public static final JsonPath POST_LOGIN_UNSUCCESSFUL_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postLoginUnsuccessfulExpectedResponse.json"));
    public static final JsonPath POST_LOGIN_UNSUCCESSFUL_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postLoginUnsuccessfulRequest.json"));
    public static final JsonPath POST_REGISTER_SUCCESSFUL_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postRegisterSuccessfulExpectedResponse.json"));
    public static final JsonPath POST_REGISTER_SUCCESSFUL_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postRegisterSuccessfulRequest.json"));
    public static final JsonPath POST_REGISTER_UNSUCCESSFUL_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postRegisterUnsuccessfulExpectedResponse.json"));
    public static final JsonPath POST_REGISTER_UNSUCCESSFUL_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/postRegisterUnsuccessfulRequest.json"));
    public static final JsonPath PUT_UPDATE_EXPECTED_RESPONSE = new JsonPath(new File("src/test/resources/jsons/toReqresIn/putUpdateExpectedResponse.json"));
    public static final JsonPath PUT_UPDATE_REQUEST = new JsonPath(new File("src/test/resources/jsons/toReqresIn/putUpdateRequest.json"));
}

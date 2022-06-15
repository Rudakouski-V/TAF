package tests.api;

import configuration.ReqresInEndpoints;
import configuration.ReqresInJsons;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresInApiTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getListUsersTest() {
        given()
                .when()
                .get(ReqresInEndpoints.LIST_USERS)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_LIST_USERS_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getSingleUserTest() {
        given()
                .when()
                .get(ReqresInEndpoints.SINGLE_USER)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_SINGLE_USER_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getSingleUserNotFoundTest() {
        given()
                .when()
                .get(ReqresInEndpoints.SINGLE_USER_NOT_FOUND)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_SINGLE_USER_NOT_FOUND_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getListResourceTest() {
        given()
                .when()
                .get(ReqresInEndpoints.LIST_RESOURCE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_LIST_RESOURCE_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getSingleResourceTest() {
        given()
                .when()
                .get(ReqresInEndpoints.SINGLE_RESOURCE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_SINGLE_RESOURCE_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        given()
                .when()
                .get(ReqresInEndpoints.SINGLE_RESOURCE_NOT_FOUND)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_SINGLE_RESOURCE_NOT_FOUND_EXPECTED_RESPONSE.getMap("" + "")));
    }

    @Test
    public void postCreateTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.POST_CREATE_REQUEST.getMap(""))
                .when()
                .post(ReqresInEndpoints.CREATE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .log().body()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", instanceOf(String.class))
                .body("createdAt", instanceOf(String.class))
                .time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void putUpdateTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.PUT_UPDATE_REQUEST.getMap(""))
                .when()
                .put(ReqresInEndpoints.UPDATE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", instanceOf(String.class))
                .time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void patchUpdateTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.PATCH_UPDATE_REQUEST.getMap(""))
                .when()
                .patch(ReqresInEndpoints.UPDATE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", instanceOf(String.class))
                .time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void deleteDeleteTest() {
        given()
                .when()
                .delete(ReqresInEndpoints.DELETE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.POST_REGISTER_SUCCESSFUL_REQUEST.getMap(""))
                .when()
                .post(ReqresInEndpoints.REGISTER)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.POST_REGISTER_SUCCESSFUL_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void postRegisterUnsuccessfulTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.POST_REGISTER_UNSUCCESSFUL_REQUEST.getMap(""))
                .when()
                .post(ReqresInEndpoints.REGISTER)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.POST_REGISTER_UNSUCCESSFUL_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void postLoginSuccessfulTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.POST_LOGIN_SUCCESSFUL_REQUEST.getMap(""))
                .when()
                .post(ReqresInEndpoints.LOGIN)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.POST_LOGIN_SUCCESSFUL_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void postLoginUnsuccessfulTest() {
        given()
                .header("Content-type", "application/json")
                .body(ReqresInJsons.POST_LOGIN_UNSUCCESSFUL_REQUEST.getMap(""))
                .when()
                .post(ReqresInEndpoints.LOGIN)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.POST_LOGIN_UNSUCCESSFUL_EXPECTED_RESPONSE.getMap("")));
    }

    @Test
    public void getDelayedResponseTest() {
        given()
                .when()
                .get(ReqresInEndpoints.DELAYED_RESPONSE)
                .then()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log().body()
                .body("", equalTo(ReqresInJsons.GET_DELAYED_RESPONSE_EXPECTED_RESPONSE.getMap("")));
    }
}

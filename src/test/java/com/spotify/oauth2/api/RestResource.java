package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String access_token, Object objectPayload) {
        return given(getRequestSpec()).auth().oauth2(access_token)/*.header("Authorization", "Bearer " + access_token)*/.
                body(objectPayload).when().
                post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response postAccount(HashMap<String, String> formData) {
        return given(getAccountRequestSpec())
                .formParams(formData).when().post(API+TOKEN).
                then().log().all().spec(getResponseSpec()).extract().response();
    }

    public static Response get(String access_token, String path) {
        return given(getRequestSpec()).auth().oauth2(access_token)./*header("Authorization", "Bearer " + access_token).*/
                get(path).then().spec(getResponseSpec()).assertThat().statusCode(200).extract().response();
    }

    public static Response put(String access_token, Object objectPayload, String path) {
        return given(getRequestSpec()).auth().oauth2(access_token)./*header("Authorization", "Bearer " + access_token).*/
                body(objectPayload).put(path).
                then().extract().response();
    }
}
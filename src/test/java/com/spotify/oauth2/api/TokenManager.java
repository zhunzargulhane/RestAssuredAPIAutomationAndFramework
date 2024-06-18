package com.spotify.oauth2.api;

import com.spotify.oauth2.api.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Properties;

import static com.spotify.oauth2.api.RestResource.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public static synchronized String getAccessToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing the token");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryTimeInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryTimeInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }

        } catch (Exception e) {
            throw new RuntimeException("ABORT !! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken() throws IOException {
        HashMap<String, String> formData = new HashMap();
        formData.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formData.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formData.put("redirect_uri", ConfigLoader.getInstance().getRedirectURI());
        formData.put("client_id", ConfigLoader.getInstance().getClientId());
        formData.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        Response response = postAccount(formData);
        if (response.statusCode() != 200)
            throw new RuntimeException("ABORT !!! Renew token failed");
        else return response;
    }
}


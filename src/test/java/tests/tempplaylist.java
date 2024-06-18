package tests;


import com.jayway.jsonpath.JsonPath;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

public class tempplaylist {
    String access_token = "BQB7HS1kQQ3ZtQVMyfM7O-026vBPDyWRZp46-Yx0fIRzG5dj8lG8iSCPJVrU583kNBhKgdfxYLbT0h8gH-1TWpgoA4IdJ-0aeKxaGu38fvLXVOrSYQvcb5_NdlG85-Bp2wyYgtOcpwax1JOdSvjdet0bnE58L0yWKh-BY4N2zAlemfT0xa9iOtHK0YLOljmDA9-ya8GfSvY_d0NcD6Mb2kSpzWjHk4GRj38nfiWlpuHPX9UlFP0BQo4opMTw2QYs6euPWiCgHle7HSLh";

    @BeforeClass
    public void initialize() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecification = requestSpecBuilder.setBaseUri("https://api.spotify.com").setBasePath("/v1").
                setContentType("application/json").
                addHeader("Authorization", "Bearer " + access_token).log(LogDetail.ALL).build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.
                expectContentType("application/json").log(LogDetail.ALL).build();

    }

    @Test
    public void create_playlist() {
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\payloads\\createPlaylist.json");
        with().body(file).when().post("/users/31staj7ol57mdzzpj2mb2cdf7klq/playlists").then().assertThat().statusCode(201).
                body("name",equalTo("Myplaylist"),
                        "description",equalTo("EnglishSongs"),
                        "public",equalTo(false));
    }

    @Test
    public void get_playlist() {
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\payloads\\createPlaylist.json");
        String playlistId = with().body(file).when().post("/users/31staj7ol57mdzzpj2mb2cdf7klq/playlists").then().assertThat().statusCode(201).
                body("name",equalTo("Myplaylist"),
                        "description",equalTo("EnglishSongs"),
                        "public",equalTo(false)).extract().response().path("id");
        with().pathParam("id",playlistId).get("/playlists/{id}").then().assertThat().statusCode(200)
                .body("id",equalTo(playlistId));
    }

    @Test
    public void update_playlist() {
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\payloads\\createPlaylist.json");
        String playlistId = with().body(file).when().post("/users/31staj7ol57mdzzpj2mb2cdf7klq/playlists").then().assertThat().statusCode(201).
                body("name",equalTo("Myplaylist"),
                        "description",equalTo("EnglishSongs"),
                        "public",equalTo(false)).extract().response().path("id");
        with().pathParam("id",playlistId).body("{\n" +
                "    \"name\": \"Updated Playlist Name12\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": true\n" +
                "}").put("/playlists/{id}").then().assertThat().statusCode(200);
    }

    @Test
    public void negativeTestCase_UnAuthorized_playlist() {
        //File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\payloads\\createPlaylist.json");
        String payload = "{\n" +
                "    \"name\": \"Myplaylist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        with().log().all().baseUri("https://api.spotify.com").basePath("/v1").
                contentType("application/json").
                header("Authorization", "Bearer 12344").body(payload).when().post("/users/31staj7ol57mdzzpj2mb2cdf7klq/playlists").then().assertThat().statusCode(401).
                body("error.status",equalTo(401),
                        "error.message",equalTo("Invalid access token"));
    }

    @Test
    public void badRequest_playlist() throws IOException, ParseException {

        FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\payloads\\createPlaylist.json");
        JSONParser jsonParser = new JSONParser();
        String payload = jsonParser.parse(fileReader).toString();
        payload = JsonPath.parse(payload).set("$.name",null).jsonString();
        with().body(payload).when().post("/users/31staj7ol57mdzzpj2mb2cdf7klq/playlists").then().
                assertThat().body("error.status",equalTo(400),
                        "error.message",equalTo("Missing required field: name")).statusCode(400);

    }



}
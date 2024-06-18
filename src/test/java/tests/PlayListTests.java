package tests;

import com.spotify.auth2.pojo.ErrorRoot;
import com.spotify.auth2.pojo.Playlist;
import com.spotify.oauth2.api.ApplicationAPI.PlaylistAPI;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.spotify.oauth2.api.StatusCode.CODE_200;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlayListTests extends BaseTest {

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        //return new Playlist()..setName(name).setDescription(description).setMypublic(_public).build();
        /*Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setMypublic(_public);
        return playlist;*/
        return Playlist.builder().name(name).description(description).mypublic(_public).build();
    }

    @Step
    public void assertPlaylistEquals(Playlist deserializedPlaylist, Playlist requestPlaylist) {
        assertThat(deserializedPlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(deserializedPlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(deserializedPlaylist.isMypublic(), equalTo(requestPlaylist.isMypublic()));
    }

    public void assertStatusCode(int expectedStatusCode, StatusCode statusCode) {
        assertThat(expectedStatusCode, equalTo(statusCode.getCode()));
    }

    public void assertError(ErrorRoot errorRoot, StatusCode statusCode) {
        assertThat(errorRoot.getError().getStatus(), equalTo(statusCode.getCode()));
        assertThat(errorRoot.getError().getMessage(), equalTo(statusCode.getMsg()));
    }

    @Issue("IC-14145")
    @Story("Create a playlist story")
    @Description("This is create playlist test")
    @Test(description = "should be able to create playlist")
    public void create_playlist() {
        Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(), StatusCode.CODE_201);
        assertPlaylistEquals(response.as(Playlist.class), requestPlaylist);

    }

    @Test
    public void get_playlist() {
        Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
        String playlistId = PlaylistAPI.post(requestPlaylist).path("id");
        Response responsePlaylist = PlaylistAPI.get(playlistId);
        assertStatusCode(responsePlaylist.getStatusCode(), StatusCode.CODE_200);
        assertPlaylistEquals(responsePlaylist.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithExpiredToken() {
        Playlist requestPlaylist = playlistBuilder("alpha", "alpha Description", true);
        Response response = PlaylistAPI.post("BQB7HS1kQQ3ZtQVMyfM7O-026vBPDyWRZp46-Yx0fIRzG5dj8lG8iSCPJVrU583kNBhKgdfxYLbT0h8gH-1TWpgoA4IdJ-0aeKxaGu38fvLXVOrSYQvcb5_NdlG85-Bp2wyYgtOcpwax1JOdSvjdet0bnE58L0yWKh-BY4N2zAlemfT0xa9iOtHK0YLOljmDA9-ya8GfSvY_d0NcD6Mb2kSpzWjHk4GRj38nfiWlpuHPX9UlFP0BQo4opMTw2QYs6euPWiCgHle7HSLh", requestPlaylist);
        assertError(response.as(ErrorRoot.class), StatusCode.CODE_401);
    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithoutName() throws IOException, ParseException {
        Playlist requestPlaylist = playlistBuilder(null, "alpha Description", true);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertError(response.as(ErrorRoot.class), StatusCode.CODE_400);
    }

    @Test
    public void update_playlist() {
        Playlist requestPlaylist = playlistBuilder("alpha", "alpha Description", true);
        String playlistId = PlaylistAPI.post(requestPlaylist).path("id");
        requestPlaylist = playlistBuilder("alpha updated", "alpha Description updated", true);
        Response response = PlaylistAPI.put(playlistId, requestPlaylist);
        assertStatusCode(response.getStatusCode(), CODE_200);
    }

}

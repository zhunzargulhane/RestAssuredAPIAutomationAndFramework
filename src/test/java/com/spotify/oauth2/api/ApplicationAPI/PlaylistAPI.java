package com.spotify.oauth2.api.ApplicationAPI;

import com.spotify.auth2.pojo.Playlist;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getAccessToken;

public class PlaylistAPI {
    @Step
    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(USERS + "/"+ConfigLoader.getInstance().getUserId() + PLAYLISTS, getAccessToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get(getAccessToken(), PLAYLISTS + "/" + playlistId + "");
    }

    public static Response put(String playlistId, Playlist requestPlaylist) {
        return RestResource.put(getAccessToken(), requestPlaylist, PLAYLISTS + "/" + playlistId);
    }
}

package com.spotify.auth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalUrls {
    String spotify;

    @JsonProperty("spotify")
    public String getSpotify() {
        return this.spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }


}

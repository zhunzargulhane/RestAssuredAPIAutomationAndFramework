package com.spotify.auth2.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
//@Data
@Value
//@Getter @Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value="public",allowGetters = true)
public class Playlist {
    boolean collaborative;

    String description;

    String uri;

    Tracks tracks;

    ExternalUrls external_urls;

    String type;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    boolean mypublic;

    String href;

    Followers followers;

    String id;

    Object images;

    String name;

    Owner owner;

    String snapshot_id;

    Object primary_color;


    /* ("collaborative")
    public boolean getCollaborative() {
        return this.collaborative;
    }

    public void setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
    }


    ("description")
    public String getDescription() {
        return this.description;
    }

    public Playlist setDescription(String description) {
        this.description = description;
        return this;
    }


    ("external_urls")
    public ExternalUrls getExternal_urls() {
        return this.external_urls;
    }

    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls;
    }


    ("followers")
    public Followers getFollowers() {
        return this.followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }


    ("href")
    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    ("id")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    ("images")
    public Object getImages() {
        return this.images;
    }

    public void setImages(Object images) {
        this.images = images;
    }


    ("name")
    public String getName() {
        return this.name;
    }

    public Playlist setName(String name) {
        this.name = name;
        return this;
    }


    ("owner")
    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    ("primary_color")
    public Object getPrimary_color() {
        return this.primary_color;
    }

    public void setPrimary_color(Object primary_color) {
        this.primary_color = primary_color;
    }

    ("public")
    public boolean getMypublic() {
        return this.mypublic;
    }

    public Playlist setMypublic(boolean mypublic) {
        this.mypublic = mypublic;
        return this;
    }


    ("snapshot_id")
    public String getSnapshot_id() {
        return this.snapshot_id;
    }

    public void setSnapshot_id(String snapshot_id) {
        this.snapshot_id = snapshot_id;
    }


    ("tracks")
    public Tracks getTracks() {
        return this.tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }


    ("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    ("uri")
    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

*/
}

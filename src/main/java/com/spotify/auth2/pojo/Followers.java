package com.spotify.auth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Followers {
    @JsonProperty("href")
    public Object getHref() {
        return this.href;
    }

    public void setHref(Object href) {
        this.href = href;
    }

    Object href;

    @JsonProperty("total")
    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    int total;
}

package com.spotify.oauth2.api.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader = null;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null)
            configLoader = new ConfigLoader();
        return configLoader;
    }

    public String getClientSecret() {
        String clientSecret = properties.getProperty("client_secret");
        if (clientSecret != null) return clientSecret;
        else throw new RuntimeException("property client_secret is not specified in config.properties file");
    }

    public String getClientId() {
        String client_id = properties.getProperty("client_id");
        if (client_id != null) return client_id;
        else throw new RuntimeException("property client_id is not specified in config.properties file");
    }

    public String getUserId() {
        String user_id = properties.getProperty("user_id");
        if (user_id != null) return user_id;
        else throw new RuntimeException("property user_id is not specified in config.properties file");
    }

    public String getGrantType() {
        String grant_type = properties.getProperty("grant_type");
        if (grant_type != null) return grant_type;
        else throw new RuntimeException("property grant_type is not specified in config.properties file");
    }

    public String getRefreshToken() {
        String refresh_token = properties.getProperty("refresh_token");
        if (refresh_token != null) return refresh_token;
        else throw new RuntimeException("property refresh_token is not specified in config.properties file");
    }

    public String getRedirectURI() {
        String redirect_uri = properties.getProperty("redirect_uri");
        if (redirect_uri != null) return redirect_uri;
        else throw new RuntimeException("property redirect_uri is not specified in config.properties file");
    }

}

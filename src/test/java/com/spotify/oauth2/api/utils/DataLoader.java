package com.spotify.oauth2.api.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader = null;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data_loader.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null)
            dataLoader = new DataLoader();
        return dataLoader;
    }

   public String getPlaylistId(){
        String playlist_id = properties.getProperty("playlist_id");
        if(playlist_id!=null) return playlist_id;
        else throw new RuntimeException("property file do not have playlist_id field in data_loader.properties file");
   }

}

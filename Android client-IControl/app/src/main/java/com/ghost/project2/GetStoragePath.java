package com.ghost.project2;


public class GetStoragePath {

    public String getStoragePath() {
        String internal = System.getenv("EXTERNAL_STORAGE");
        String external = System.getenv("SECONDARY_STORAGE");
        if (external == null || external.equals("")) {
            return internal;
        }
        return external;

    }
}

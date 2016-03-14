package com.perich.instagram;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by perich on 3/10/16.
 */
public class InstagramPhoto {
    public String username;
    public String userPPUrl;
    public String caption;
    public String imageUrl;
    public int imageHeight;
    public int likesCount;
    public int commentCount;
    public long createdAt;

    public static InstagramPhoto fromJSON(JSONObject json) {
        InstagramPhoto obj = new InstagramPhoto();
        try {
            obj.username    = json.getJSONObject("user").getString("username");
            obj.userPPUrl   = json.getJSONObject("user").getString("profile_picture");
            obj.caption     = json.getJSONObject("caption").getString("text");
            obj.imageUrl    = json.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            obj.imageHeight = json.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
            obj.likesCount  = json.getJSONObject("likes").getInt("count");
            obj.commentCount= json.getJSONObject("comments").getInt("count");
            obj.createdAt   = json.getJSONObject("caption").getLong("created_time");
        } catch (JSONException e) {}
        return obj;
    }

    public String timeAgoInWords() {
        long unixTime = System.currentTimeMillis() / 1000L;
        long difference = unixTime - this.createdAt;
        long differenceMins = difference / 60;
        long differenceHours = differenceMins / 60;
        if (differenceHours > 1) {
            return String.valueOf(differenceHours) + " hours ago";
        }
        else if (differenceHours == 1) {
            return String.valueOf(differenceHours) + " hour ago";
        }
        else if (differenceMins > 1) {
            return String.valueOf(differenceMins) + " minutes ago";
        }
        else {
            return "Just now";
        }
    }
}

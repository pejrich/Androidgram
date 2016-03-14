package com.perich.instagram;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public ArrayList comments;

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
            // Get comments
            JSONArray jsonComments = json.getJSONObject("comments").getJSONArray("data");
            obj.comments = new ArrayList<InstagramComment>();
            for (int i = 0; i < jsonComments.length(); i++) {
                obj.comments.add(InstagramComment.fromJSON(jsonComments.getJSONObject(i)));
            }
        } catch (JSONException e) {}
        return obj;
    }
}

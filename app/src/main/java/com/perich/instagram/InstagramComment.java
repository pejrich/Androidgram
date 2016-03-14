package com.perich.instagram;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class InstagramComment {
    public String username;
    public int userId;
    public String text;
    public String profilePicture;
    public long createdAt;

    public static InstagramComment fromJSON(JSONObject json) {
        InstagramComment comment = new InstagramComment();
        try {
            comment.username        =  json.getJSONObject("from").getString("username");
            comment.userId          =  json.getJSONObject("from").getInt("id");
            comment.text            =  json.getString("text");
            comment.profilePicture  =  json.getJSONObject("from").getString("profile_picture");
            comment.createdAt       =  json.getLong("created_time");

        } catch (JSONException e) {
            Log.i("logger", "There is an error: "+ e.toString());
        }
        return comment;
    }
}

//Example Instagram Response
/*   {created_time: "1457944079",
     text: "Wow, soooo pretty !",
     from: {
       username: "kinanttiadz",
       profile_picture: "https://igcdn-photos-f-a.akamaihd.net/hphotos-ak-xat1/t51.2885-19/s150x150/10683905_450489231809317_1740139717_a.jpg",
       id: "2273682114",
       full_name: "Kinantti Aqilah Dzaki"
     },
     id: "1205644784404202637"}  */
package com.perich.instagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by perich on 3/14/16.
 */
public class InstagramCommentsAdapter extends ArrayAdapter<InstagramComment> {
    public InstagramCommentsAdapter(Context context, List<InstagramComment> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("logger", "GVCALLED");
        InstagramComment comment = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        TextView tvCommentUsername = (TextView) convertView.findViewById(R.id.tvCommentUsername);
        tvCommentUsername.setText(comment.username);
        TextView tvCommentTimeAgo = (TextView) convertView.findViewById(R.id.tvCommentTimeAgo);
        tvCommentTimeAgo.setText("1 hours ago");
        TextView tvCommentText = (TextView) convertView.findViewById(R.id.tvCommentText);
        tvCommentText.setText(comment.text);
        return convertView;
    }
}

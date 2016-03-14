package com.perich.instagram;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perich on 3/10/16.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    public Context that;

    private InstagramCommentsAdapter aComments;

    // Header elements
    public ImageView ivProfilePicture;
    public TextView tvUsername;
    public TextView tvTimestamp;
    // Photo
    public ImageView ivPhoto;
    // Caption elements
    public TextView tvLikes;
    public TextView tvCaptionUsername;
    public TextView tvCaptionCaption;
    // Comment elements
    public TextView tvCommentHeader;

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        that = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get data item for this position
        InstagramPhoto photo = getItem(position);
        // check if using a recylced view, if not we need to inflate
        if (convertView == null) {
            // create a new view
            // args: view, container, attach_to_now?
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        getViewElements(convertView);
        setViewElementsData(photo);

        // Setup comments adapter
//        aComments = new InstagramCommentsAdapter(that, photo.comments);
        aComments = new InstagramCommentsAdapter(that, new ArrayList<InstagramComment>());
        // find the list view
        ListView lvComments = (ListView) convertView.findViewById(R.id.lvComments);
        // bind adapter to listView
        lvComments.setAdapter(aComments);
        InstagramComment co = new InstagramComment();
        co.username = "uusseerr";
        co.text = "tteexxtt";
        aComments.add(co);
        aComments.add(co);
        aComments.add(co);

//        aComments.notifyDataSetChanged();

        return convertView;
    }

    private void getViewElements(View view) {
        // Header elements
        ivProfilePicture = (ImageView) view.findViewById(R.id.ivProfilePicture);
        tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        tvTimestamp = (TextView) view.findViewById(R.id.tvTimestamp);
        // Photo
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        // Caption elements
        tvLikes = (TextView) view.findViewById(R.id.tvLikes);
        tvCaptionUsername = (TextView) view.findViewById(R.id.tvCaptionUsername);
        tvCaptionCaption = (TextView) view.findViewById(R.id.tvCaptionCaption);
        // Comment elements
        tvCommentHeader = (TextView) view.findViewById(R.id.tvCommentHeader);
    }

    private void setViewElementsData(InstagramPhoto photo) {
        clearOldImages();
        // Header elements
        Picasso.with(getContext()).load(photo.userPPUrl).into(ivProfilePicture);
        tvUsername.setText(photo.username);
        tvTimestamp.setText(timeAgoInHours(photo.createdAt));
        // Insert the main photo
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        // Caption elements
        tvLikes.setText(pluralizeByNumber(photo.likesCount, "like", "likes"));
        tvCaptionUsername.setText(photo.username);
        tvCaptionCaption.setText(captionMinusUsername(photo.caption, photo.username));
        // Comment elements
        tvCommentHeader.setText(pluralizeByNumber(photo.commentCount, "comment", "comments"));
    }

    private String pluralizeByNumber(int count, String singluar , String plural) {
        // Return "1 like", "123 likes" or "1 comment" "0 comments", etc.
        return String.valueOf(count) + " " + ((count == 1) ? singluar : plural);
    }


    private String captionMinusUsername(String caption, String username) {
        //  A bit of a hacky way to get the username
        String returnString = "";
        Paint paint = new Paint();
        double numSpaces = (paint.measureText(username) / 3.0) + 1;
        for (int i = 0; i < numSpaces; i++) {
            returnString += " ";
        }
        return returnString += caption;
    }

    private String timeAgoInHours(long timestamp) {
        long unixTime = System.currentTimeMillis() / 1000L;
        long difference = unixTime - timestamp;
        return String.valueOf(difference / 60 / 60) + " hours ago";
    }

    private void clearOldImages() {
        // In case the view is recyled, we want to clear out old images
        ivPhoto.setImageResource(0);
        ivProfilePicture.setImageResource(0);
    }


}

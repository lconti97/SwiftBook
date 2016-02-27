package com.rmiapps.swiftbook;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity  extends Activity {
    private EditText postText;
    private Button nextButton;
    private Button postButton;
    private LinearLayout buttonBar;
    private boolean isPostView;
    private Button likeButton;
    private Button commentsButton;
    private String contentType;
    private LinearLayout contentLayout;
    private boolean liked;

    private TextView user;
    private TextView status;
    private ImageView image;
    private TextView caption;
    private Button nextPic;
    private int[] imageIds;
    private int imageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPostView = true;
        liked = false;
        setContentView(R.layout.activity_main);
        postText = (EditText) findViewById(R.id.post_text);
        nextButton = (Button) findViewById(R.id.next_button);
        postButton = (Button) findViewById(R.id.post_button);
        likeButton = (Button) findViewById(R.id.like_button);
        commentsButton = (Button) findViewById(R.id.comments_button);
        buttonBar = (LinearLayout) findViewById(R.id.button_bar);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPostView) {
                    hideView(postButton);
                    postText.setEnabled(false);
                    ViewGroup.LayoutParams loparams = postText.getLayoutParams();
                    loparams.width = 0;
                    loparams.height = 0;
                    postText.setLayoutParams(loparams);
                    showButton(likeButton);
                    showButton(commentsButton);
                    likeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!liked) {
                                liked = true;
                                likeButton.setText("LIKED!");
                                likeButton.setBackgroundResource(R.color.darkTeal);
                            }
                            else {
                                liked = false;
                                likeButton.setText("LIKE");
                                likeButton.setBackgroundResource(R.color.teal);
                            }

                        }
                    });
                    isPostView = false;
                }
                contentLayout.removeAllViews();
                if(liked) {
                    liked = false;
                    likeButton.setText("LIKE");
                    likeButton.setBackgroundResource(R.color.teal);
                }
                nextPost();
            }
        });
    }

    private void nextPost() {

//        displayTextPost();
//        displayPicturePost();
        displayAlbumPost();
    }

    private void displayTextPost() {
        user = createTextView("Kevin Conti");
        user.setTypeface(null, Typeface.BOLD);
        status = createTextView("Look how I save $2.48 million a day!");
    }

    private void displayPicturePost() {
        user = createTextView("Lucas Conti");
        user.setTypeface(null, Typeface.BOLD);
        caption = createTextView("I'm frat af!");
        //TODO: set ellipsize
        caption.setMaxLines(2);
        image = new ImageView(getApplicationContext());
        image.setImageResource(R.mipmap.psk);
        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        imageParams.gravity = Gravity.CENTER;
        image.setLayoutParams(imageParams);
        image.setAdjustViewBounds(true);
        contentLayout.addView(image);
    }

    private void displayAlbumPost() {
        user = createTextView("Lucas Conti");
        user.setTypeface(null, Typeface.BOLD);
        caption = createTextView("I'm frat af!");
        caption.setMaxLines(2);
        image = new ImageView(getApplicationContext());
        image.setImageResource(R.mipmap.psk);
        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        imageParams.gravity = Gravity.CENTER;
        imageParams.setMargins(0,40,0,40);
        image.setLayoutParams(imageParams);
        image.setAdjustViewBounds(true);
        contentLayout.addView(image);
        nextPic = new Button(getApplicationContext());
        ViewGroup.LayoutParams buttonParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nextPic.setText("NEXT PHOTO");
        nextPic.setTextColor(Color.BLACK);
        nextPic.setBackgroundResource(R.color.teal);
        nextPic.setLayoutParams(buttonParams);
        contentLayout.addView(nextPic);
        imageIds = new int[2];
        imageIds[0] = R.mipmap.psk;
        imageIds[1] = R.mipmap.psk2;
        imageIndex = 0;
        nextPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageIndex++;
                image.setImageResource(imageIds[imageIndex]);
                if(imageIndex + 1 == imageIds.length) {
                    nextPic.setText("NO MORE PICTURES");
                    nextPic.setEnabled(false);
                }
            }
        });
    }

    private void hideView(View v) {
        LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) v.getLayoutParams();
        loparams.weight = 0;
        loparams.width = 0;
        loparams.height = 0;
        v.setLayoutParams(loparams);
    }

    private void showButton(Button b) {
        LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) b.getLayoutParams();
        loparams.weight = 1;
        loparams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        loparams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        b.setLayoutParams(loparams);
    }

    private TextView createTextView(String text) {
        TextView t = new TextView(getApplicationContext());
        t.setText(text);
        t.setTextColor(Color.BLACK);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        contentLayout.addView(t);
        return t;
    }
}

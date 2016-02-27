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

    private TextView user;
    private TextView status;
    private ImageView image;
    private TextView caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPostView = true;
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
                    isPostView = false;
                }
                nextPost();
            }
        });
    }

    private void nextPost() {

//        displayTextPost();
        displayPicturePost();
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
        caption.setMaxLines(2);
        image = new ImageView(getApplicationContext());
        image.setImageResource(R.mipmap.psk);
        LinearLayout.LayoutParams loparams =
//                (LinearLayout.LayoutParams) image.getLayoutParams();
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1);
//        loparams.weight = 1;
        loparams.gravity = Gravity.CENTER;
        image.setLayoutParams(loparams);
        image.setAdjustViewBounds(true);
        contentLayout.addView(image);

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

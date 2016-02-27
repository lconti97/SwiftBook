package com.rmiapps.swiftbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity  extends Activity {
    EditText postText;
    Button nextButton;
    Button postButton;
    LinearLayout buttonBar;
    boolean isPostView;
    Button likeButton;
    Button commentsButton;

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
            }
        });
    }

    private void hideView(View v) {
        LayoutParams loparams = (LayoutParams) v.getLayoutParams();
        loparams.weight = 0;
        loparams.width = 0;
        loparams.height = 0;
        v.setLayoutParams(loparams);
    }

    private void showButton(Button b) {
        LayoutParams loparams = (LayoutParams) b.getLayoutParams();
        loparams.weight = 1;
        loparams.width = LayoutParams.WRAP_CONTENT;
        loparams.height = LayoutParams.WRAP_CONTENT;
        b.setLayoutParams(loparams);
    }
}

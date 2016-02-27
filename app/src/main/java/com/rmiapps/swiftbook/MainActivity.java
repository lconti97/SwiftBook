package com.rmiapps.swiftbook;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        buttonBar = (LinearLayout) findViewById(R.id.button_bar);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPostView) {
//                    buttonBar.removeView(postButton);
                    LinearLayout.LayoutParams loparams =
                            (LinearLayout.LayoutParams) postButton.getLayoutParams();
                    loparams.weight = 0;
                    loparams.width = 0;
                    postButton.setLayoutParams(loparams);
                }

            }
        });
    }
}

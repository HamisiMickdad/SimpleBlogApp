package com.hamsofts.simpleblogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostDetails extends AppCompatActivity {

    TextView tvTitle, tvBody, tvTime, tvAuthor;
    ImageView imgBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_desc);

        tvBody = (TextView) findViewById(R.id.tvBody);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTime = (TextView) findViewById(R.id.post_time);
        tvAuthor = (TextView) findViewById(R.id.appName);



    }
}

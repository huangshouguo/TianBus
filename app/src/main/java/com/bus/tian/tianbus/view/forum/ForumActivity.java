package com.bus.tian.tianbus.view.forum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bus.tian.tianbus.R;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, ForumActivity.class);
        context.startActivity(intent);
    }
}

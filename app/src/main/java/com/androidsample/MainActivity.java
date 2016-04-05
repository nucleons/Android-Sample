package com.androidsample;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidsample.common.BaseActivity;
import com.androidsample.model.UserDetail;
import com.androidsample.task.ReadFromFileTask;
import com.androidsample.util.FragmentHelper;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        AddUserFragment();
    }

    private void AddUserFragment(){
        Fragment fragment = new UserDetailFragment();
        FragmentHelper.addAndReplaceContentFragment(R.id.frame_container,this,fragment);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}

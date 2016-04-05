package com.androidsample.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidsample.R;

/**
 * Created by ADITYA on 04-Apr-16.
 */
public class BaseActivity extends FragmentActivity{

    private ViewGroup mContainerView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.base_layout);
        mContainerView = (ViewGroup) findViewById(R.id.base_container);
    }

    public void setLayout(int resId) {
        View view = getLayoutInflater().inflate(resId, null);
        mContainerView.addView(view);
    }
}

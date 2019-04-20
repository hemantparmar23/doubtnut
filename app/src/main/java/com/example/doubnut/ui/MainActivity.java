package com.example.doubnut.ui;

import android.os.Bundle;

import com.example.doubnut.R;
import com.example.doubnut.base.BaseActivity;
import com.example.doubnut.ui.newlist.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}

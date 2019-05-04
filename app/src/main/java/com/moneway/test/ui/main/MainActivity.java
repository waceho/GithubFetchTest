package com.moneway.test.ui.main;

import android.os.Bundle;

import com.moneway.test.R;
import com.moneway.test.base.BaseActivity;
import com.moneway.test.ui.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(
                    R.id.mainScreenContainer,
                    new HomeFragment()
            ).commit();
    }
}

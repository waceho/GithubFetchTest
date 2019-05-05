package com.moneway.test.ui.main;

import android.os.Bundle;

import com.moneway.test.R;
import com.moneway.test.base.BaseActivity;
import com.moneway.test.ui.home.HomeFragment;

import java.util.Objects;

import androidx.fragment.app.FragmentManager;

public class MainActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

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

    @Override
    public void onBackStackChanged() {
        canDisplayHomeUp();
    }

    /** display home as up if needed **/
    public void canDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount()>0;
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(canGoBack);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }
}

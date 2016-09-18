package com.daprlabs.aaron.swipedeck2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentActivity extends android.support.v4.app.FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initFragment();
    }

    private void initFragment(){
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,mainFragment,"firstFragment");
        fragmentTransaction.commit();
    }
}

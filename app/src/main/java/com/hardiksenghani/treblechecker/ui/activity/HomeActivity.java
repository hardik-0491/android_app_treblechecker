package com.hardiksenghani.treblechecker.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hardiksenghani.treblechecker.R;
import com.hardiksenghani.treblechecker.ui.fragment.SeamlessUpdateFragment;
import com.hardiksenghani.treblechecker.ui.fragment.TrebleFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean result = false;
            Fragment targetFragment = null;
            int enterTransition = android.R.anim.slide_in_left;
            int exitTransition = android.R.anim.slide_out_right;
            switch (item.getItemId()) {
                case R.id.navigation_treble:
                    targetFragment = TrebleFragment.newInstance();
                    result = true;
                    break;
                case R.id.navigation_ab_update:
                    targetFragment = SeamlessUpdateFragment.newInstance();
                    result = true;
                    break;
            }
            if (targetFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(enterTransition, exitTransition);
                Fragment sourceFragment = fragmentManager.findFragmentById(R.id.activity_home_fragment);
                if (sourceFragment != null) {
                    fragmentTransaction.remove(sourceFragment);
                }
                fragmentTransaction.replace(R.id.activity_home_fragment, targetFragment);
                fragmentTransaction.commit();
            }
            return result;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.activity_home_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_treble);
        }
    }

}

package com.treverthomas.summonerssearch.core;

import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.treverthomas.summonerssearch.R;
import com.treverthomas.summonerssearch.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    private static final String HOME_TAG = "home_fragment";
    private static final String FAVORITES_TAG = "favorites_fragment";
    private static final String SEARCH_TAG = "SEARCH_FRAGMENT";

    private FragmentManager fragmentManager;

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        container = (FrameLayout) findViewById(R.id.fragment_container);

        startHomeFragment(HOME_TAG);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                startHomeFragment(FAVORITES_TAG);
                                return true;
                            case R.id.action_schedules:
                                startHomeFragment(HOME_TAG);
                                return true;
                            case R.id.action_music:
                                startHomeFragment(SEARCH_TAG);
                                return true;
                        }
                        return true;
                    }
                });
    }

    //TODO: Convert back to only startHome after testing
    private void startHomeFragment(String tagTitle) {
        if (fragmentManager.findFragmentByTag(tagTitle) != null) {
            if (fragmentManager.findFragmentById(R.id.fragment_container).getTag().equals(tagTitle)) {
                //Exists and is front fragment
                Toast.makeText(this, "Instance of " + tagTitle, Toast.LENGTH_SHORT).show();
            } else {
                //Exists but is not front fragment
                HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(tagTitle);
                replaceFragment(homeFragment, tagTitle);
                Toast.makeText(this, "Fragment Count:" + fragmentManager.getFragments().size(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //Does not exist
            HomeFragment homeFragment = HomeFragment.newInstance(tagTitle);
            addFragmentToStack(homeFragment, tagTitle);
        }


    }

    private void removeFragment(Fragment fragment) {
        fragmentManager.beginTransaction().remove(fragment).commit();
    }

    private void replaceFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, tag).commit();
    }

    private void addFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment, tag).commit();
    }

    private void addFragmentToStack(Fragment fragment, String tag) {
        fragmentManager.beginTransaction().addToBackStack(tag).add(R.id.fragment_container, fragment, tag).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

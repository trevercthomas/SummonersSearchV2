package com.treverthomas.summonerssearch.home;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.treverthomas.summonerssearch.R;
import com.treverthomas.summonerssearch.SummonersApplication;
import com.treverthomas.summonerssearch.core.dagger.DaggerMainActivityComponent;
import com.treverthomas.summonerssearch.core.dagger.MainActivityComponent;
import com.treverthomas.summonerssearch.core.dagger.MainActivityModule;
import com.treverthomas.summonerssearch.core.dagger.ViewComponentInjector;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ViewComponentInjector {

    private MainActivityComponent component;

    private HomeView homeView;

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createMainComponent();
        component.inject(this);

        setContentView(R.layout.activity_main);
        homeView = (HomeView) findViewById(R.id.homeView);

        viewSwitch(true, false, false);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                //TODO: switch to (false, true, false)
                                viewSwitch(false, false, false);
                                return true;
                            case R.id.action_home:
                                viewSwitch(true, false, false);
                                return true;
                            case R.id.action_search:
                                //TODO: switch to (false, false, true)
                                viewSwitch(true, false, false);
                                return true;
                        }
                        return true;
                    }
                });
    }

    private void createMainComponent() {
        setMainComponent(DaggerMainActivityComponent.builder()
                .applicationComponent(SummonersApplication.component())
                .mainActivityModule(new MainActivityModule(this))
                .build());
    }


    private void setMainComponent(MainActivityComponent instance) {
        if (component == null) {
            component = instance;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.load();
    }

    private void viewSwitch(boolean isHome, boolean isFavorite, boolean isSearch) {
        homeView.setVisibility(isHome ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public MainActivityComponent getBaseComponent() {
        return component;
    }
}

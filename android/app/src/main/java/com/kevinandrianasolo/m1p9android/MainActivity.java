package com.kevinandrianasolo.m1p9android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.kevinandrianasolo.m1p9android.databinding.ActivityMainBinding;
import com.kevinandrianasolo.m1p9android.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    public SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        // Hide default ActionBar
        getSupportActionBar().hide();

        this.drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category, R.id.nav_account, R.id.nav_setting, R.id.nav_category_courses, R.id.nav_login)
                .setDrawerLayout(this.drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ConstraintLayout appbar = findViewById(R.id.appbar);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.nav_course || destination.getId() == R.id.nav_login ) {
                    appbar.setVisibility(View.GONE);
                } else {
                    appbar.setVisibility(View.VISIBLE);
                }
            }
        });
        this.initMenu();

        /**
         * Customize navigation drawer when a user is connected or not
         */
        Menu navMenu = navigationView.getMenu();
        MenuItem accountItem = navMenu.findItem(R.id.nav_account);
        MenuItem loginItem = navMenu.findItem(R.id.nav_login);


        /**
         * Getting the connected USER :
         */
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId" , null);

        Boolean isAuthentificated = userId!=null;
        if(accountItem!=null) accountItem.setVisible(isAuthentificated);
        if(loginItem!=null) loginItem.setVisible(!isAuthentificated);

        /**
         * Subscribe change Event to the SharedPreference
         */
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                String userId = sharedPref.getString("userId" , null);
                Boolean isAuthentificated = userId!=null;
                if(accountItem!=null) accountItem.setVisible(isAuthentificated);
                if(loginItem!=null) loginItem.setVisible(!isAuthentificated);
            }
        };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
        sharedPreferencesUtils.setSharedPreferences(sharedPref);
    }

    public void initMenu(){
        findViewById(R.id.toolbar_menu_launcher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // To programatically Show and Hide Menu on the left
                MainActivity.this.drawer.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
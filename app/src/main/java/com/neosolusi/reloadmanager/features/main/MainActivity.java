package com.neosolusi.reloadmanager.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.features.customer.CustomerFagment;
import com.neosolusi.reloadmanager.features.main.masterdetail.ItemListActivity;
import com.neosolusi.reloadmanager.features.pos.grosir.MkiosActivity;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureLayout();
    }

    private void configureLayout()
    {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        MainFragment fragment = MainFragment.getInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
    }

    @Override public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override public void run()
            {
                final int id = item.getItemId();

                if (id == R.id.nav_home) {
//            startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                    CustomerFagment fragment = CustomerFagment.getInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
                } else if (id == R.id.nav_profil) {
                    startActivity(new Intent(MainActivity.this, ItemListActivity.class));
                } else if (id == R.id.nav_list_rute) {
                    startActivity(new Intent(MainActivity.this, MkiosActivity.class));
                } else if (id == R.id.nav_maps) {

                } else if (id == R.id.nav_sell) {

                } else if (id == R.id.nav_mkios) {
                    
                }
            }
        }, 250);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportActionBar().setTitle(item.getTitle());

        return true;
    }

    @OnClick(R.id.fab)
    public void fabClicked()
    {
        Snackbar.make(
                findViewById(R.id.main_layout),
                "Replace with your own action",
                Snackbar.LENGTH_LONG
        ).setAction("Action", null).show();
    }
}
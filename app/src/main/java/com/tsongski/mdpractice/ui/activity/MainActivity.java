package com.tsongski.mdpractice.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsongski.mdpractice.R;
import com.tsongski.mdpractice.ui.fragment.HomeFragment;
import com.tsongski.mdpractice.ui.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Snackbar mSnacker;
    private CoordinatorLayout mContainer;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecycler;
    private static Activity mMainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(this);
        mContainer = (CoordinatorLayout) findViewById(R.id.container);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mRecycler = (RecyclerView) findViewById(R.id.content);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(new ContentAdapter());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(toggle);
        setStaticActivity();
        createSnack();
    }

    private void createSnack() {
        mSnacker = Snackbar.make(mContainer, "click back again to exit", Snackbar.LENGTH_LONG);
        mSnacker.setAction("exit", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnacker.dismiss();
            }
        });

        mSnacker.setCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                mSnacker = null;
                createSnack();
            }
        });
    }

    private void setStaticActivity() {
        mMainActivity = this;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (mSnacker.isShown()) {
                super.onBackPressed();
            } else {
                mSnacker.show();
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
//        switchFragment(menuItem.getItemId());
        return true;
    }

    private void switchFragment(int id) {
        Fragment f = null;
        switch (id) {
            case R.id.action_home:
                f = new HomeFragment();
                break;
            case R.id.action_login:
                f = new LoginFragment();
                break;
            case R.id.action_about:
                break;
        }
        if (f == null)
            return;
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content, f)
//                .commit();
    }

    private class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {
        @Override
        public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContentViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.home_name_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ContentViewHolder holder, int position) {
            holder.name.setText(String.valueOf((char) ('a' + position)));
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ContentViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }


}

package com.tsongski.mdpractice.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tsongski.mdpractice.R;
import com.tsongski.mdpractice.ui.fragment.HomeFragment;
import com.tsongski.mdpractice.ui.fragment.MSTestFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	public static final String TAG = MainActivity.class.getSimpleName();

	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
		navigationView.setNavigationItemSelectedListener(this);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		mDrawerLayout.closeDrawer(GravityCompat.START);
		switchFragment(menuItem.getItemId());
		return true;
	}

	private void switchFragment(int id) {
		Fragment f = null;
		switch (id) {
			case R.id.action_home:
				f = new HomeFragment();
				break;
			case R.id.action_login:
				f = new MSTestFragment();
				break;
			case R.id.action_about:
				break;
		}
		if (f == null)
			return;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content, f)
				.commit();
	}

}

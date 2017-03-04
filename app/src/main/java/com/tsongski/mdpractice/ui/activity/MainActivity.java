package com.tsongski.mdpractice.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.tsongski.mdpractice.R;
import com.tsongski.mdpractice.ui.fragment.HomeFragment;
import com.tsongski.mdpractice.ui.fragment.LoginFragment;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

	public static final String TAG = MainActivity.class.getSimpleName();

	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mNavigationView = (NavigationView) findViewById(R.id.navigation);
		mNavigationView.setNavigationItemSelectedListener(this);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		findViewById(R.id.excel).setOnClickListener(this);
		findViewById(R.id.word).setOnClickListener(this);
		findViewById(R.id.power_point).setOnClickListener(this);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
		mDrawerLayout.setDrawerListener(toggle);
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
				f = new LoginFragment();
				break;
			case R.id.action_about:
				break;
		}
		if (f == null)
			return;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.excel:
				break;
			case R.id.word:
				break;
			case R.id.power_point:
				gotoPowerPoint();
				break;
		}
	}

	private void gotoPowerPoint() {
		String scheme = "ms-word::ofv|u|" + formatEncode("http://res1.xdf100.com/media/pad_import_document/7d/04/7d04af6595f723f58bd483f1e1d3e2ff.docx");
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
		startActivity(intent);
	}

	public String formatEncode(String originUrl) {
		if (TextUtils.isEmpty(originUrl)) {
			return "";
		}
		try {
			URL url = new URL(originUrl);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			return uri.toURL().toString();
		} catch (MalformedURLException e) {
		} catch (URISyntaxException e) {
		}
		return originUrl;
	}
}

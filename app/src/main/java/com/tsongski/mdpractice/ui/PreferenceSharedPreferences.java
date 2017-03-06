package com.tsongski.mdpractice.ui;

import android.content.SharedPreferences;

import com.tsongski.mdpractice.ui.util.Compat;

/**
 * Created by tsongski on 2017/3/6.
 */

public class PreferenceSharedPreferences implements Preferences {
	protected static final String SHARED_PREF_FILE_NAME = "app_preferences";

	private SharedPreferences mPref;

	protected SharedPreferences getSharedPreferences() {
		if (mPref == null) {
			mPref = Compat.getSharedPreferences(App.Instance(), SHARED_PREF_FILE_NAME);
		}
		return mPref;
	}

	@Override
	public void delete() {
		getSharedPreferences().edit().clear().commit();
	}

	@Override
	public String getString(String key) {
		return getString(key, null);
	}

	@Override
	public String getString(String key, String def) {
		return getSharedPreferences().getString(key, def);
	}

	@Override
	public int getInt(String key) {
		return getInt(key, 0);
	}

	@Override
	public int getInt(String key, int def) {
		return getSharedPreferences().getInt(key, def);
	}

	@Override
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		return getSharedPreferences().getBoolean(key, def);
	}

	@Override
	public long getLong(String key) {
		return getLong(key, 0);
	}

	@Override
	public long getLong(String key, long def) {
		return getSharedPreferences().getLong(key, def);
	}

	@Override
	public void setString(String key, String value) {
		SharedPreferences preferences = getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	@Override
	public void setInt(String key, int value) {
		SharedPreferences preferences = getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	@Override
	public void setLong(String key, long value) {
		SharedPreferences preferences = getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	@Override
	public void setBoolean(String key, boolean value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}

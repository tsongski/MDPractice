package com.tsongski.mdpractice.ui;

/**
 * Created by tsongski on 2017/3/6.
 */

public interface Preferences {
	void delete();

	String getString(String key);

	String getString(String key, String def);

	int getInt(String key);

	int getInt(String key, int def);

	boolean getBoolean(String key);

	boolean getBoolean(String key, boolean def);

	long getLong(String key);

	long getLong(String key, long def);

	void setString(String key, String value);

	void setInt(String key, int value);

	void setLong(String key, long value);

	void setBoolean(String key, boolean value);
}

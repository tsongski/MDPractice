package com.tsongski.mdpractice.ui;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.tsongski.mdpractice.BuildConfig;

import java.util.Properties;

/**
 * Created by tsongski on 2017/3/6.
 */

public class App extends Application {
	protected static final String TAG = App.class.getSimpleName();
	public static final String APP_MODE = "mode";
	public static final String KEY_APP_MODE_DEFAULT = "product";

	private static App sInstance;
	private Properties mProps;

	public static App Instance() {
		return sInstance;
	}

	private static Handler sUIHandler;

	@Override
	public void onCreate() {
		super.onCreate();
		sUIHandler = onCreateUIHandler();
	}

	protected Handler getUIHandler() {
		return sUIHandler;
	}

	protected Handler onCreateUIHandler() {
		return new Handler();
	}

	public String getProperty(String key) {
		if (mProps == null) {
			if (BuildConfig.DEBUG) {
				Log.w(TAG, "application props is null");
			}

			Properties props = new Properties();
			getPropertyLoader().load(this, props);
			mProps = props;
		}
		return mProps.getProperty(key);
	}

	private ConfigLoader getPropertyLoader() {
		return new ConfigLoaderFromXml();
	}

	public String getMode() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
			String appMode = appInfo.metaData.getString(App.APP_MODE);
			if (TextUtils.isEmpty(appMode)) {
				appMode = App.KEY_APP_MODE_DEFAULT;
			}
			return appMode;
		} catch (PackageManager.NameNotFoundException e) {
			if (BuildConfig.DEBUG) {
				Log.w(TAG, "get application meta failed", e);
			}
			return App.KEY_APP_MODE_DEFAULT;
		}
	}
}

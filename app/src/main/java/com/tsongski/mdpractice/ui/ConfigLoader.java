package com.tsongski.mdpractice.ui;

import java.util.Properties;

/**
 * Created by tsongski on 2017/3/6.
 */

public interface ConfigLoader {

	String TAG = ConfigLoader.class.getSimpleName();

	/**
	 * load configurations to props
	 *
	 * @param props
	 * @param app
	 */
	void load(App app, Properties props);
}

package com.tsongski.mdpractice.ui;

import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.Log;

import com.tsongski.mdpractice.BuildConfig;
import com.tsongski.mdpractice.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tsongski on 2017/3/6.
 */

public class ConfigLoaderFromXml implements ConfigLoader {

	@Override
	public void load(App app, Properties props) {
		List<String> modeList = parseAppMode(app.getMode());
		load(props, modeList);

		Enumeration<?> propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String propName = (String) propNames.nextElement();
			replacePlaceholder(propName, props);
		}
	}

	private void load(Properties props, List<String> modeList) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "load config from xml");
			for (String mode : modeList) {
				xmlConfigToProperty(getResIdOfMode(mode), props);
			}
		}
	}

	private int getResIdOfMode(String mode) {
		try {
			Class<?> resXml = Class.forName("com.eastalliance.pad.R$xml");
			return resXml.getField("config_" + mode).getInt(resXml);
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "cannot find R.xml.config_" + mode, e);
			}
			return R.xml.config_common;
		}
	}

	private void xmlConfigToProperty(int xmlResId, Properties props) {
		try {
			XmlResourceParser parser = App.Instance().getResources().getXml(xmlResId);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String tagName = parser.getName();
					if ("config".equals(tagName)) {
						String key = parser.getAttributeValue(null, "key");
						String value = parser.getAttributeValue(null, "value");

						props.setProperty(key, value);
					}
				}
				eventType = parser.next();
			}

		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

	private List<String> parseAppMode(String mode) {
		List<String> list = new LinkedList<>();
		list.add("common");
		if (TextUtils.isEmpty(mode)) {
			return list;
		}

		String[] modes = mode.split("[,]]");
		for (String modeName : modes) {
			list.add(modeName.trim());
		}
		return list;
	}

	private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{.+?\\}");

	private void replacePlaceholder(String key, Properties props) {
		String value = props.getProperty(key);
		Matcher m = PLACEHOLDER_PATTERN.matcher(value);
		boolean has = false;
		while (m.find()) {
			has = true;
			String match = m.group();
			String propName = match.substring(2, match.length() - 1);
			replacePlaceholder(propName, props);
			value = m.replaceFirst(props.getProperty(propName));
		}
		if (has) {
			props.put(key, value);
		}
	}
}

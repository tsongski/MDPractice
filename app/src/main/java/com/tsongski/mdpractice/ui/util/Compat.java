package com.tsongski.mdpractice.ui.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewTreeObserver;

import com.tsongski.mdpractice.ui.component.Device;

import java.util.List;

/**
 * Created by tsongski on 2017/3/6.
 */

public class Compat {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static SharedPreferences getSharedPreferences(Context context, String fileName) {
		if (Device.hasHONEYCOMB()) {
			return context.getSharedPreferences(fileName, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
		} else {
			return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressWarnings("deprecation")
	public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener l) {
		if (Device.hasJELLY_BEAN()) {
			v.getViewTreeObserver().removeOnGlobalLayoutListener(l);
		} else {
			v.getViewTreeObserver().removeGlobalOnLayoutListener(l);
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressWarnings("deprecation")
	public static void setBackground(View view, Drawable drawable) {
		if (Device.hasJELLY_BEAN()) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}

	public static void setCameraFocusMode(Camera.Parameters params) {
		List<String> focusModes = params.getSupportedFocusModes();
		if (focusModes == null) { // device ha no camera
			return;
		}

		if (Device.hasICE_CREAM_SANDWICH()) {
			if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
			} else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			} else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
			}
		} else if (Device.hasGINGERBREAD()) {
			if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			} else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
			}
		} else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
			if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
			}
		}
	}

	public static void startActivities(Context context, Intent[] intents) {
		if (!ContextCompat.startActivities(context, intents)) {
			for (Intent intent : intents) {
				context.startActivity(intent);
			}
		}
	}

}

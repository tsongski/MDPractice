package com.tsongski.mdpractice.ui.component;

import android.os.Build;

import com.tsongski.mdpractice.ui.App;
import com.tsongski.mdpractice.ui.DeviceImpl;

/**
 * Created by tsongski on 2017/3/6.
 */

public abstract class Device {
	public static final String NO_DEVICE_ID = "E00000000000000";

	private static Device sDevice;

	public static void create(App app) {
		sDevice = new DeviceImpl(app);
	}

	public static Device get() {
		return sDevice;
	}

	public static int getOSSDKVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * Android 1.6, SDK_INT=4
	 *
	 * @return
	 */
	public static boolean hasDONUT() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.DONUT;
	}

	/**
	 * Android 2.0 SDK_INT=5
	 *
	 * @return
	 */
	public static boolean hasECLAIR() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.ECLAIR;
	}

	/**
	 * Android 2.0.1 SDK_INT=6
	 *
	 * @return
	 */
	public static boolean hasECLAIR_0_1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.ECLAIR_0_1;
	}

	/**
	 * Android 2.1 SDK_INT=7
	 *
	 * @return
	 */
	public static boolean hasECLAIR_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.ECLAIR_MR1;
	}

	/**
	 * static  2.2 SDK_INT=8
	 *
	 * @return
	 */
	public static boolean hasFROYO() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.FROYO;
	}

	/**
	 * Android 2.3 SDK_INT=9
	 *
	 * @return
	 */
	public static boolean hasGINGERBREAD() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.GINGERBREAD;
	}

	/**
	 * Android 2.3.3 SDK_INT=10
	 *
	 * @return
	 */
	public static boolean hasGINGERBREAD_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.GINGERBREAD_MR1;
	}

	/**
	 * Android 3.0 SDK_INT=11
	 *
	 * @return
	 */
	public static boolean hasHONEYCOMB() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.HONEYCOMB;
	}

	/**
	 * Android 3.1 SDK_INT=12
	 *
	 * @return
	 */
	public static boolean hasHONEYCOMB_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1;
	}

	/**
	 * Android 3.2 SDK_INT=13
	 *
	 * @return
	 */
	public static boolean hasHONEYCOMB_MR2() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2;
	}

	/**
	 * Android 4.0,4.0.1,4.0.2 SDK_INT=14
	 *
	 * @return
	 */
	public static boolean hasICE_CREAM_SANDWICH() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}

	/**
	 * Android 4.0.3,4.0.4 SDK_INT=15
	 *
	 * @return
	 */
	public static boolean hasICE_CREAM_SANDWICH_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
	}

	/**
	 * Android 4.1,4.1.1 SDK_INT=16
	 *
	 * @return
	 */
	public static boolean hasJELLY_BEAN() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.JELLY_BEAN;
	}

	/**
	 * Android 4.2 SDK_INT=17
	 *
	 * @return
	 */
	public static boolean hasJELLY_BEAN_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
	}

	/**
	 * Android 4.3 SDK_INT=18
	 *
	 * @return
	 */
	public static boolean hasJELLY_BEAN_MR2() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;
	}

	/**
	 * Android 4.4 SDK_INT=19
	 *
	 * @return
	 */
	public static boolean hasKITKAT() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.KITKAT;
	}

	/**
	 * Android 4.4W(W is 'Wear') SDK_INT=20
	 *
	 * @return
	 */
	public static boolean hasKITKAT_WATCH() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.KITKAT_WATCH;
	}

	/**
	 * Android 5.0 SDK_INT=21
	 *
	 * @return
	 */
	public static boolean hasLOLLIPOP() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.LOLLIPOP;
	}

	/**
	 * Android 5.1 SDK_INT=22
	 *
	 * @return
	 */
	public static boolean hasLOLLIPOP_MR1() {
		return getOSSDKVersion() >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1;
	}

	/**
	 * Android 6.0 SDK_INT= 23
	 */
	public static boolean hasM() {
		return getOSSDKVersion() >= Build.VERSION_CODES.M;
	}

}

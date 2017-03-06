package com.tsongski.mdpractice.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by tsongski on 16/9/21.
 */
public class Utils {
	public static final String TAG = Utils.class.getSimpleName();

	public static String getRomSpace(Context context) {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSizeLong();
		long totalBlocks = stat.getBlockCountLong();
		long availableBlocks = stat.getAvailableBlocksLong();

		String totalSize = Formatter.formatFileSize(context, blockSize * totalBlocks);
		String availableSize = Formatter.formatFileSize(context, availableBlocks * blockSize);

		return availableSize + "/" + totalSize;
	}

	public static File getScreenShot(Context context) {
		Date now = new Date();
		String str = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now).toString();
		File dir;
		try {
			if (hasExternalStorage()) {
				dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Uda");
				if (!dir.exists()) {
					if (dir.mkdir()) {
						new File(dir, ".nomedia").createNewFile();
					}
					Log.v(TAG, "create ExternalStorageDir");
				}
			} else {
				dir = context.getApplicationContext().getDir("Uda", Context.MODE_PRIVATE);
				if (!dir.exists()) {
					Log.v(TAG, "create InternalStorageDir");
					dir.mkdir();
				}
			}

			if (context instanceof Activity) {
				View decor = ((Activity) context).getWindow().getDecorView().getRootView();
				decor.setDrawingCacheEnabled(true);
				Bitmap bitmap = Bitmap.createBitmap(decor.getDrawingCache());
				decor.setDrawingCacheEnabled(false);

				File file = new File(dir.getAbsolutePath() + File.separator + str + ".jpg");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileOutputStream fileOutputStream = new FileOutputStream(file);
				int quality = 100;
				bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();
				return file;
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "文件没有找到");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean hasExternalStorage() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
	}
}

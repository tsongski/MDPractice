package com.tsongski.mdpractice.ui;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by tsongski on 16/9/21.
 */
public class Utils {

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
}

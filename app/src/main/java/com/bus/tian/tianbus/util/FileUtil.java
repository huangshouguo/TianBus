package com.bus.tian.tianbus.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;


/**
 * Created by hsg on 11/12/16.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static Bitmap getBitmapByFilePathName(final String filePathName) {
        if (!TextUtils.isEmpty(filePathName)) {
            try {
                return BitmapFactory.decodeFile(filePathName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap compressBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            Log.d(TAG, "compressBitmap: before size = " + bitmap.getByteCount());
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                int options = 100;

                //循环判断如果压缩后图片是否大于1MB,大于继续压缩
                while (baos.toByteArray().length / 1024 > 1024) {
                    baos.reset();
                    options -= 10;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                }

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                Bitmap result = BitmapFactory.decodeStream(bais);
                Log.d(TAG, "compressBitmap: after size = " + result.getByteCount());
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static File getFileFromBitmap(final String fileName, Bitmap bitmap) {
        if (bitmap != null) {
            File file = new File(fileName);
            ByteArrayOutputStream baos = null;
            FileOutputStream fos = null;

            try {
//                File storagePaht = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                file = File.createTempFile("temp", ".jpg", storagePaht);

                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                fos = new FileOutputStream(file);
                fos.write(baos.toByteArray());
                baos.flush();
                fos.flush();
                bitmap.recycle();
                baos.close();
                fos.close();
                return file;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null) {
                        baos.flush();
                        baos.close();
                    }

                    if (fos != null) {
                        fos.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String readableFileSize(String filePathName) {
        if (!TextUtils.isEmpty(filePathName)) {
            try {

                File file = new File(filePathName);
                long size = file.length();
                if (size > 0) {
                    final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
                    int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
                    return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "0B";
    }
}

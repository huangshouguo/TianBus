package com.bus.tian.tianbus.util;

import android.text.TextUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by hsg on 11/12/16.
 */

public class MultiPartUtil {

    public static MultipartBody.Part imageFileToPart(final String filePathName) {

        File file = FileUtil.getFileFromBitmap(filePathName, FileUtil.getBitmapByFilePathName(filePathName));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        return part;
    }

    public static MultipartBody.Part videoFileToPart(final String filePathName) {
        File file = new File(filePathName);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("video", file.getName(), requestBody);
        return part;
    }

    public static RequestBody string2RequestBody(final String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        return RequestBody.create(MediaType.parse("multipart/form-data"), data);
    }
}

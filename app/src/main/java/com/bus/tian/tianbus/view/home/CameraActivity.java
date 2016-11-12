package com.bus.tian.tianbus.view.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.util.LocationUtil;
import com.bus.tian.tianbus.view.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends BaseActivity {
    private static final int REQ_TAKE_PHOTO = 1;
    private static final int REQ_VIDEO_CAPTURE = 2;
    private static final String START_TAG = "start_tag";
    private static final String START_PHOTO = "start_photo";
    private static final String START_VIDEO = "start_video";


    @BindView(R.id.edit_camera_comment)
    EditText editComment;
    @BindView(R.id.image_camera_show)
    ImageView imageShow;
    @BindView(R.id.btn_camera_upload)
    Button btnUpload;
    @BindView(R.id.edit_camera_location)
    EditText editLocation;
    @BindView(R.id.video_camera_show)
    VideoView videoShow;


    private Uri imageUri;
    private String strStartSource;
    private String strCurPhotoPath;

    public static void actionStartPhoto(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(START_TAG, START_PHOTO);
        context.startActivity(intent);
    }

    public static void actionStartVideo(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(START_TAG, START_VIDEO);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        this.strStartSource = intent.getStringExtra(START_TAG);
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            // log it when the location changes
            Log.d("LocationActivity", "location:" + location);
        }

        public void onProviderDisabled(String provider) {
            // Provider被disable时触发此函数，比如GPS被关闭
        }

        public void onProviderEnabled(String provider) {
            //  Provider被enable时触发此函数，比如GPS被打开
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        }
    };

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(this.strStartSource)) {
            switch (this.strStartSource) {
                case START_PHOTO:
                    startCameraForPhoto();
                    break;
                case START_VIDEO:
                    startCameraForVideo();
                    break;
                default:
                    break;
            }
        } else {
            finish();
        }

        this.editLocation.setText(LocationUtil.getInstance().getLocationAddress());
    }

    @Override
    protected void onRelease() {

    }

    @OnClick(R.id.btn_camera_upload)
    public void onClick() {
    }

    private void startCameraForPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFile();
        if (photoFile != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(intent, REQ_TAKE_PHOTO);
        }
    }

    private void startCameraForVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, REQ_VIDEO_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    reviewPhoto();
                }
                break;
            case REQ_VIDEO_CAPTURE:
                if (resultCode == RESULT_OK) {
                    reviewVideo(data.getData());
                }
                break;
        }
    }

    private File createImageFile() {
        File image = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "Jpeg_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
            this.strCurPhotoPath = image.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private void reviewPhoto() {
        this.videoShow.setVisibility(View.GONE);
        Bitmap bitmap = BitmapFactory.decodeFile(this.strCurPhotoPath);
        this.imageShow.setImageBitmap(bitmap);
        this.imageShow.setVisibility(View.VISIBLE);
    }

    private void reviewVideo(Uri videoUri) {
        if (videoUri == null) {
            Log.e(TAG, "reviewVideo: videoUri = " + videoUri);
            return;
        }

        this.imageShow.setVisibility(View.GONE);
        this.videoShow.setVisibility(View.VISIBLE);
        this.videoShow.setMediaController(new MediaController(this));
        this.videoShow.setVideoURI(videoUri);
        this.videoShow.start();
    }

}
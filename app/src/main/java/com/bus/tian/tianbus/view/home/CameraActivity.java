package com.bus.tian.tianbus.view.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.bus.tian.tianbus.contract.IUploadFileContract;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.component.DaggerIUploadFileComponent;
import com.bus.tian.tianbus.di.module.UploadFileModule;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.util.LocationUtil;
import com.bus.tian.tianbus.view.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends BaseActivity implements IUploadFileContract.IView {
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
    @Inject
    IUploadFileContract.IPresenter presenter;

    private String strStartSource;
    private String strCurPhotoPath;
    private String strCurVideoPath;

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
        DaggerIUploadFileComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .uploadFileModule(new UploadFileModule(this))
                .build()
                .inject(this);
    }

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
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @OnClick(R.id.btn_camera_upload)
    public void onClick() {
        upload();
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
        File videoFile = createVideoFile();
        if (videoFile != null) {
            showRemainderMessage("仅可拍摄5秒钟短视频");
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
        }
        startActivityForResult(intent, REQ_VIDEO_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            finish();
            return;
        }

        switch (requestCode) {
            case REQ_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    reviewPhoto();
                }
                break;
            case REQ_VIDEO_CAPTURE:
                if (resultCode == RESULT_OK) {
                    reviewVideo();
                }
                break;
        }
    }

    private File createImageFile() {
        File image = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "image_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
            this.strCurPhotoPath = image.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private File createVideoFile() {
        File video = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "video_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            video = File.createTempFile(imageFileName, ".mp4", storageDir);
            this.strCurVideoPath = video.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return video;
    }

    private void reviewPhoto() {
        if (TextUtils.isEmpty(this.strCurPhotoPath)) {
            Log.e(TAG, "reviewPhoto: imagePath = " + strCurPhotoPath);
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeFile(this.strCurPhotoPath);
        this.imageShow.setImageBitmap(bitmap);
        this.videoShow.setVisibility(View.GONE);
        this.imageShow.setVisibility(View.VISIBLE);
    }

    private void reviewVideo() {
        if (this.strCurVideoPath == null) {
            Log.e(TAG, "reviewVideo: videoPath = " + strCurVideoPath);
            return;
        }
        this.imageShow.setVisibility(View.GONE);
        this.videoShow.setVisibility(View.VISIBLE);
        this.videoShow.setMediaController(new MediaController(this));
        this.videoShow.setVideoPath(this.strCurVideoPath);
        this.videoShow.start();
    }

    @Override
    public void uploadFileCompleted() {
        finish();
    }

    private String getCommentContent() {
        if (this.editComment == null) {
            return null;
        }
        if (this.editComment.getText() == null || TextUtils.isEmpty(this.editComment.getText().toString())) {
            showErrorMessage("评论不能为空");
            return null;
        }
        return this.editComment.getText().toString();
    }

    private String getLocation() {
        if (this.editLocation == null) {
            return null;
        }

        if (this.editLocation.getText() == null || TextUtils.isEmpty(this.editLocation.getText().toString())) {
            showErrorMessage("位置信息不能为空");
            return null;
        }
        return this.editLocation.getText().toString();
    }

    private void upload() {
        if (this.presenter != null) {

            String strComment = getCommentContent();
            String strLocation = getLocation();

            if (TextUtils.isEmpty(strComment) || TextUtils.isEmpty(strLocation)) {
                return;
            }

            ImageVideoBean imageVideoBean = new ImageVideoBean();
            imageVideoBean.setComment(strComment);
            imageVideoBean.setLocation(strLocation);

            switch (this.strStartSource) {
                case START_PHOTO:
                    imageVideoBean.setFilePath(this.strCurPhotoPath);
                    this.presenter.uploadImageFile(imageVideoBean);
                    break;
                case START_VIDEO:
                    imageVideoBean.setFilePath(this.strCurVideoPath);
                    this.presenter.uploadVideoFile(imageVideoBean);
                    break;
                default:
                    break;
            }
        }
    }
}
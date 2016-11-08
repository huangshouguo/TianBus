package com.bus.tian.tianbus.view.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bus.tian.tianbus.R;
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

    @BindView(R.id.edit_camera_comment)
    EditText editComment;
    @BindView(R.id.image_camera_show)
    ImageView imageShow;
    @BindView(R.id.btn_camera_upload)
    Button btnUpload;

    private Uri imageUri;
    private String strCurPhotoPath;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        startCamera();
    }

    @Override
    protected void onRelease() {

    }

    @OnClick(R.id.btn_camera_upload)
    public void onClick() {
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = createImageFile();

        if (photoFile != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(intent, REQ_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    setPicture();
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

    private void setPicture() {
        Bitmap bitmap = BitmapFactory.decodeFile(this.strCurPhotoPath);
        this.imageShow.setImageBitmap(bitmap);
    }
}

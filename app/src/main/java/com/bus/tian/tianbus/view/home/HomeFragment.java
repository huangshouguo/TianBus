package com.bus.tian.tianbus.view.home;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IHomeContract;
import com.bus.tian.tianbus.di.component.DaggerIHomeComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.HomeModule;
import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class HomeFragment extends BaseFragment implements IHomeContract.IView {
    private static final String TAG = "HomeFragment";

    @BindView(R.id.list_view_home)
    ListView listViewHome;
    @Inject
    IHomeContract.IPresenter presenter;

    private AnnouncementAdapter announcementAdapter;
    private List<CopAnnouncementBean> copAnnouncementBeanList;
    private MainActivity mainActivity;
    private HeaderViewHolder headerViewHolder;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        DaggerIHomeComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        this.copAnnouncementBeanList = new ArrayList<>();
        this.announcementAdapter = new AnnouncementAdapter(mainActivity, R.layout.list_item_announcement, this.copAnnouncementBeanList);
        View header = LayoutInflater.from(mainActivity).inflate(R.layout.sub_home_header, this.listViewHome, false);
        this.listViewHome.addHeaderView(header, null, false);
        this.listViewHome.setAdapter(this.announcementAdapter);
        this.headerViewHolder = new HeaderViewHolder(header);
        loadAnnouncementData();
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
        }
    }

    @Override
    public void updateCopAnnouncementListView(List<CopAnnouncementBean> copAnnouncementBeanList) {
        if (copAnnouncementBeanList != null) {
            this.copAnnouncementBeanList.clear();
            this.copAnnouncementBeanList.addAll(copAnnouncementBeanList);
            if (this.announcementAdapter != null) {
                this.announcementAdapter.notifyDataSetChanged();
            }
        }
    }


    @OnItemClick(R.id.list_view_home)
    void onItemClickedOfAnnouncement(AdapterView<?> parent, View view, int position, long id) {
        if ((this.copAnnouncementBeanList != null) && (this.copAnnouncementBeanList.size() > 0) && (id >= 0) && (id < this.copAnnouncementBeanList.size())) {
            AnnouncementDetailActivity.actionStart(mainActivity, this.copAnnouncementBeanList.get((int) id));
        }
    }

    private void loadAnnouncementData() {
        if (this.presenter != null) {
            this.presenter.loadCopAnnouncementData();
        }
    }

    class HeaderViewHolder {
        @BindView(R.id.btn_cop_110)
        Button btnCop110;
        @BindView(R.id.btn_cop_local)
        Button btnCopLocal;
        @BindView(R.id.btn_capture_photo)
        Button btnCapturePhoto;
        @BindView(R.id.btn_capture_video)
        Button btnCaptureVideo;


        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.btn_cop_110, R.id.btn_cop_local, R.id.btn_capture_photo, R.id.btn_capture_video})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_cop_110:
                    callCopper();
                    break;
                case R.id.btn_cop_local:
                    callLocalCopper();
                    break;
                case R.id.btn_capture_photo:
                    startCaptureImage();
                    break;
                case R.id.btn_capture_video:
                    startCaptureVideo();
                    break;
                default:
                    break;
            }
        }

        private void callCopper() {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:110"));
            startActivity(intent);
        }

        private void callLocalCopper() {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:110"));
            startActivity(intent);
        }

        private void startCaptureImage() {
            CameraActivity.actionStartPhoto(baseActivity);
        }

        private void startCaptureVideo() {
            CameraActivity.actionStartVideo(baseActivity);
        }
    }
}

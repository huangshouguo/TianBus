package com.bus.tian.tianbus.view.home;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

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

    @BindView(R.id.list_view_cop_announcement)
    ListView listViewCopAnnouncement;
    @BindView(R.id.list_view_message_board)
    ListView listViewMessageBoard;
    @BindView(R.id.btn_cop_110)
    Button btnCop110;
    @BindView(R.id.btn_cop_local)
    Button btnCopLocal;
    @BindView(R.id.btn_capture_photo)
    Button btnCapturePhoto;
    @BindView(R.id.btn_capture_video)
    Button btnCaptureVideo;
    @Inject
    IHomeContract.IPresenter presenter;
    @BindView(R.id.scroll_view_home)
    ScrollView scrollViewHome;

    private AnnouncementAdapter announcementAdapter;
    private List<CopAnnouncementBean> copAnnouncementBeanList;
    private MainActivity mainActivity;

    @Override
    public void onResume() {
        super.onResume();
        this.preShowAction();
    }

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
        initAnnouncementListView();
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

    @OnClick({R.id.btn_cop_110, R.id.btn_cop_local, R.id.btn_capture_photo, R.id.btn_capture_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cop_110:
                break;
            case R.id.btn_cop_local:
                break;
            case R.id.btn_capture_photo:
                break;
            case R.id.btn_capture_video:
                break;
        }
    }

    @OnItemClick(R.id.list_view_cop_announcement)
    void onItemClickedOfAnnouncement(AdapterView<?> parent, View view, int position, long id) {
        if ((this.copAnnouncementBeanList != null) && (this.copAnnouncementBeanList.size() > 0) && (id >= 0) && (id < this.copAnnouncementBeanList.size())) {
            AnnouncementDetailActivity.actionStart(mainActivity, this.copAnnouncementBeanList.get((int) id));
        }
    }

    public void preShowAction(){
        if (this.scrollViewHome != null){
            this.scrollViewHome.smoothScrollTo(0,0);
        }
    }

    private void initAnnouncementListView() {
        this.copAnnouncementBeanList = new ArrayList<>();
        this.announcementAdapter = new AnnouncementAdapter(this.mainActivity,
                R.layout.list_item_announcement,
                this.copAnnouncementBeanList);
        this.listViewCopAnnouncement.setAdapter(this.announcementAdapter);
    }

    private void loadAnnouncementData() {
        if (this.presenter != null) {
            this.presenter.loadCopAnnouncementData();
        }
    }
}

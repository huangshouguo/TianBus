package com.bus.tian.tianbus.view.forum;

import android.widget.EditText;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IForumCreateContract;
import com.bus.tian.tianbus.di.component.DaggerIForumCreateComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.ForumCreateModule;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.view.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumCreateFragment extends BaseFragment implements IForumCreateContract.IView {

    @BindView(R.id.edit_forum_create)
    EditText editCreate;
    @Inject
    IForumCreateContract.IPresenter presenter;

    private ForumActivity forumActivity;

    public ForumCreateFragment() {
    }

    public static ForumCreateFragment getInstance() {
        return new ForumCreateFragment();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_forum_create;
    }

    @Override
    protected void initData() {
        DaggerIForumCreateComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .forumCreateModule(new ForumCreateModule(this))
                .build()
                .inject(this);

        this.forumActivity = (ForumActivity) baseActivity;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @Override
    public void updateView(ForumSummaryBean forumSummaryBean) {
        this.forumActivity.showForumSummaryFragment();
    }

    public void sendCreatedTheme() {
        if (this.presenter != null) {
            this.presenter.createForumTheme(this.editCreate.getText().toString());
        }
    }
}

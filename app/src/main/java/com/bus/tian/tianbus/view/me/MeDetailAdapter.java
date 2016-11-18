package com.bus.tian.tianbus.view.me;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/18/16.
 */

public class MeDetailAdapter extends BaseQuickAdapter<ImageVideoBean, BaseViewHolder> {
    public MeDetailAdapter(int layoutResId, List<ImageVideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ImageVideoBean imageVideoBean) {
        baseViewHolder.setText(R.id.text_list_item_me_detail_comment, imageVideoBean.getComment());
        baseViewHolder.setText(R.id.text_list_item_me_detail_createtime, imageVideoBean.getCreateTimeImpl());
        baseViewHolder.setText(R.id.text_list_item_me_detail_location, imageVideoBean.getLocation());
    }
}

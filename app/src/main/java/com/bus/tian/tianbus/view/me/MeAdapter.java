package com.bus.tian.tianbus.view.me;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.ImageTextBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/18/16.
 */

public class MeAdapter extends BaseQuickAdapter<ImageTextBean, BaseViewHolder> {
    public MeAdapter(int layoutResId, List<ImageTextBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ImageTextBean imageTextBean) {
        baseViewHolder.setImageResource(R.id.image_me_item, imageTextBean.getImageResId());
        baseViewHolder.setText(R.id.text_me_item, imageTextBean.getText());
    }
}

package com.bus.tian.tianbus.view.me;

import com.bus.tian.tianbus.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/18/16.
 */

public class MeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MeAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.text_me_item, s);
    }
}

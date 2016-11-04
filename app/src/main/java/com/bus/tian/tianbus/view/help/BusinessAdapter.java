package com.bus.tian.tianbus.view.help;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.HelpBizBean;
import com.bus.tian.tianbus.model.bean.TitleContentBean;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/4/16.
 */

public class BusinessAdapter extends BaseSectionQuickAdapter<HelpBizBean, BaseViewHolder> {

    public BusinessAdapter(int layoutResId, int sectionHeadResId, List<HelpBizBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, HelpBizBean helpBizBean) {
        baseViewHolder.setText(R.id.text_section_header, helpBizBean.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HelpBizBean helpBizBean) {
        TitleContentBean titleContentBean = (TitleContentBean) helpBizBean.t;
        baseViewHolder.setText(R.id.text_list_section_item, titleContentBean.getTitle());
    }
}

package com.bus.tian.tianbus.model.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by hsg on 11/4/16.
 */

public class HelpBizBean extends SectionEntity<TitleContentBean> {
    public HelpBizBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HelpBizBean(TitleContentBean titleContentBean) {
        super(titleContentBean);
    }
}

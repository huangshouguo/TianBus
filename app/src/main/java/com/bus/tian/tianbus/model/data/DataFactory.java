package com.bus.tian.tianbus.model.data;

import com.bus.tian.tianbus.model.bean.HelpBizBean;
import com.bus.tian.tianbus.model.bean.TitleContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsg on 11/4/16.
 */

public class DataFactory {

    public static List<HelpBizBean> getHelpBizData() {
        List<HelpBizBean> list = new ArrayList<>();

        //section1
        list.add(new HelpBizBean(true, "section 1"));


        TitleContentBean section1Test1 = new TitleContentBean();
        section1Test1.setTitle("title1");
        section1Test1.setContent("content1");
        list.add(new HelpBizBean(section1Test1));

        TitleContentBean section1Test2 = new TitleContentBean();
        section1Test2.setTitle("title2");
        section1Test2.setContent("content2");
        list.add(new HelpBizBean(section1Test2));

        //section2
        list.add(new HelpBizBean(true, "section 2"));

        TitleContentBean section2Test1 = new TitleContentBean();
        section2Test1.setTitle("title1");
        section2Test1.setContent("content1");
        list.add(new HelpBizBean(section2Test1));

        TitleContentBean section2Test2 = new TitleContentBean();
        section2Test2.setTitle("title2");
        section2Test2.setContent("content2");
        list.add(new HelpBizBean(section2Test2));

        return list;
    }
}

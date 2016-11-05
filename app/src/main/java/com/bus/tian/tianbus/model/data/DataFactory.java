package com.bus.tian.tianbus.model.data;

import com.bus.tian.tianbus.model.bean.HelpBizBean;
import com.bus.tian.tianbus.model.bean.TextLinkBean;
import com.bus.tian.tianbus.model.bean.TitleContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsg on 11/4/16.
 */

public class DataFactory {

    public static List<TextLinkBean> getHelpLawData() {
        List<TextLinkBean> dataList = new ArrayList<>();

        TextLinkBean lawMaster = new TextLinkBean();
        lawMaster.setText("中华人民共和国宪法");
        lawMaster.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E5%AE%AA%E6%B3%95&norm_id=1158746347&content=null");

        TextLinkBean lawCriminal = new TextLinkBean();
        lawCriminal.setText("中华人民共和国刑法");
        lawCriminal.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E5%88%91%E6%B3%95&norm_id=3034657581&content=null");

        TextLinkBean lawTraffic = new TextLinkBean();
        lawTraffic.setText("中华人民共和国道路交通安全法");
        lawTraffic.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E9%81%93%E8%B7%AF%E4%BA%A4%E9%80%9A%E5%AE%89%E5%85%A8%E6%B3%95&norm_id=2408220583&content=null");

        TextLinkBean lawSecurity = new TextLinkBean();
        lawSecurity.setText("中华人民共和国治安管理处罚法");
        lawSecurity.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E6%B2%BB%E5%AE%89%E7%AE%A1%E7%90%86%E5%A4%84%E7%BD%9A%E6%B3%95&norm_id=2294665965&content=null");

        if (dataList != null) {
            dataList.clear();
            dataList.add(lawMaster);
            dataList.add(lawCriminal);
            dataList.add(lawTraffic);
            dataList.add(lawSecurity);
        }

        return dataList;
    }

    public static List<HelpBizBean> getHelpBizData() {
        List<HelpBizBean> list = new ArrayList<>();

        //户籍业务
        list.add(new HelpBizBean(true, "户籍业务"));


        TitleContentBean section1Test1 = new TitleContentBean();
        section1Test1.setTitle("身份证业务");
        section1Test1.setContent("\n" +
                "一、所需材料\n" +
                "    \n" +
                "1、申领人、补领：申请人户口簿（个人信息页）\n" +
                "    \n" +
                "2、换领：到期身份证(正反面)\n" +
                "\n" +
                "\n" +
                "二、预受理时限： \n" +
                "3个工作日\n" +
                "\n" +
                "\n" +
                "三、办理费用\n" +
                "    \n" +
                "1、初次申领、换领每证20元\n" +
                "\n" +
                "    \n" +
                "2、丢失补领或损坏换领每证40元\n" +
                "\n" +
                "    \n" +
                "3、临时居民身份证每证10元\n" +
                "\n" +
                "\n" +
                "四、受理地点:户籍地派出所");
        list.add(new HelpBizBean(section1Test1));

        TitleContentBean section1Test2 = new TitleContentBean();
        section1Test2.setTitle("title2");
        section1Test2.setContent("content2");
        list.add(new HelpBizBean(section1Test2));

        //出入境业务
        list.add(new HelpBizBean(true, "出入境业务"));

        TitleContentBean section2Test1 = new TitleContentBean();
        section2Test1.setTitle("title1");
        section2Test1.setContent("content1");
        list.add(new HelpBizBean(section2Test1));

        TitleContentBean section2Test2 = new TitleContentBean();
        section2Test2.setTitle("title2");
        section2Test2.setContent("content2");
        list.add(new HelpBizBean(section2Test2));


        //治安业务
        list.add(new HelpBizBean(true, "治安业务"));

        TitleContentBean section3Test1 = new TitleContentBean();
        section3Test1.setTitle("title1");
        section3Test1.setContent("content1");
        list.add(new HelpBizBean(section3Test1));

        TitleContentBean section3Test2 = new TitleContentBean();
        section3Test2.setTitle("title2");
        section3Test2.setContent("content2");
        list.add(new HelpBizBean(section3Test2));


        //section4
        list.add(new HelpBizBean(true, "section 4"));

        TitleContentBean section4Test1 = new TitleContentBean();
        section4Test1.setTitle("title1");
        section4Test1.setContent("content1");
        list.add(new HelpBizBean(section3Test1));

        TitleContentBean section4Test2 = new TitleContentBean();
        section4Test2.setTitle("title2");
        section4Test2.setContent("content2");
        list.add(new HelpBizBean(section3Test2));

        return list;
    }
}

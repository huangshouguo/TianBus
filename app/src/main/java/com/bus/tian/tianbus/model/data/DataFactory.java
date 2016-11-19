package com.bus.tian.tianbus.model.data;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.HelpBizBean;
import com.bus.tian.tianbus.model.bean.ImageTextBean;
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
        section1Test2.setTitle("企业职工落户迁入");
        section1Test2.setContent("content2");
        list.add(new HelpBizBean(section1Test2));

        TitleContentBean section1Test3 = new TitleContentBean();
        section1Test3.setTitle("公民民族成分变更");
        section1Test3.setContent("content2");
        list.add(new HelpBizBean(section1Test3));

        TitleContentBean section1Test4 = new TitleContentBean();
        section1Test4.setTitle("军人专业和复原安置落户");
        section1Test4.setContent("content2");
        list.add(new HelpBizBean(section1Test4));

        TitleContentBean section1Test5 = new TitleContentBean();
        section1Test5.setTitle("大中专招生迁出");
        section1Test5.setContent("content2");
        list.add(new HelpBizBean(section1Test5));

        TitleContentBean section1Test6 = new TitleContentBean();
        section1Test6.setTitle("婴儿出生落户");
        section1Test6.setContent("content2");
        list.add(new HelpBizBean(section1Test6));

        TitleContentBean section1Test7 = new TitleContentBean();
        section1Test7.setTitle("居住证办理");
        section1Test7.setContent("content2");
        list.add(new HelpBizBean(section1Test7));

        TitleContentBean section1Test8 = new TitleContentBean();
        section1Test8.setTitle("干部、工人调动户口迁移迁入");
        section1Test8.setContent("content2");
        list.add(new HelpBizBean(section1Test8));

        TitleContentBean section1Test9 = new TitleContentBean();
        section1Test9.setTitle("投靠落户");
        section1Test9.setContent("content2");
        list.add(new HelpBizBean(section1Test9));

        TitleContentBean section1Test10 = new TitleContentBean();
        section1Test10.setTitle("购房户口迁入");
        section1Test10.setContent("content2");
        list.add(new HelpBizBean(section1Test10));

        TitleContentBean section1Test11 = new TitleContentBean();
        section1Test11.setTitle("随军家属户口迁移");
        section1Test11.setContent("content2");
        list.add(new HelpBizBean(section1Test11));

        TitleContentBean section1Test12 = new TitleContentBean();
        section1Test12.setTitle("高校毕业生落户");
        section1Test12.setContent("content2");
        list.add(new HelpBizBean(section1Test12));


        //出入境业务
        list.add(new HelpBizBean(true, "出入境业务"));

        TitleContentBean section2Test1 = new TitleContentBean();
        section2Test1.setTitle("中华人民共和国护照受理");
        section2Test1.setContent("中华人民共和国护照受理");
        list.add(new HelpBizBean(section2Test1));

        TitleContentBean section2Test2 = new TitleContentBean();
        section2Test2.setTitle("往来港澳通行证及签注");
        section2Test2.setContent("content2");
        list.add(new HelpBizBean(section2Test2));


        //治安业务
        list.add(new HelpBizBean(true, "治安业务"));

        TitleContentBean section3Test1 = new TitleContentBean();
        section3Test1.setTitle("典当行业许可证审批");
        section3Test1.setContent("content1");
        list.add(new HelpBizBean(section3Test1));

        TitleContentBean section3Test2 = new TitleContentBean();
        section3Test2.setTitle("大型群众活动审批");
        section3Test2.setContent("content2");
        list.add(new HelpBizBean(section3Test2));

        TitleContentBean section3Test3 = new TitleContentBean();
        section3Test3.setTitle("娱乐场所备案");
        section3Test3.setContent("content1");
        list.add(new HelpBizBean(section3Test3));

        TitleContentBean section3Test4 = new TitleContentBean();
        section3Test4.setTitle("旅馆业许可证审批—治安管理档案变更");
        section3Test4.setContent("content2");
        list.add(new HelpBizBean(section3Test4));

        TitleContentBean section3Test5 = new TitleContentBean();
        section3Test5.setTitle("旅馆业许可证审批—治安管理档案年度审核");
        section3Test5.setContent("content1");
        list.add(new HelpBizBean(section3Test5));

        TitleContentBean section3Test6 = new TitleContentBean();
        section3Test6.setTitle("旅馆业许可证审批—治安管理档案新办证");
        section3Test6.setContent("content2");
        list.add(new HelpBizBean(section3Test6));

        TitleContentBean section3Test7 = new TitleContentBean();
        section3Test7.setTitle("烟花爆竹道路运输许可证审批");
        section3Test7.setContent("content1");
        list.add(new HelpBizBean(section3Test7));

        TitleContentBean section3Test8 = new TitleContentBean();
        section3Test8.setTitle("焰火晚会烟花爆竹燃放工程审批");
        section3Test8.setContent("content2");
        list.add(new HelpBizBean(section3Test8));

        TitleContentBean section3Test9 = new TitleContentBean();
        section3Test9.setTitle("购买运输民用爆炸物品审批");
        section3Test9.setContent("content2");
        list.add(new HelpBizBean(section3Test9));

        return list;
    }

    public static List<ImageTextBean> getMeItemDataList() {
        List<ImageTextBean> dataList = new ArrayList<>();

        ImageTextBean itemImage = new ImageTextBean();
        itemImage.setImageResId(R.drawable.ic_photo);
        itemImage.setText("警情图片记录");

        dataList.add(itemImage);

        ImageTextBean itemVideo = new ImageTextBean();
        itemVideo.setImageResId(R.drawable.ic_video);
        itemVideo.setText("警情视频记录");
        dataList.add(itemVideo);

        return dataList;
    }
}

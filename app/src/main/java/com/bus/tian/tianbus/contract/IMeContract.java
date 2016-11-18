package com.bus.tian.tianbus.contract;

import java.util.List;

/**
 * Created by hsg on 2016/11/6.
 */

public interface IMeContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(List<String> data);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void logout();

        void loadData();
    }
}

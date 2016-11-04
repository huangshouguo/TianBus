package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IHelpBizContract;
import com.bus.tian.tianbus.model.data.DataFactory;

/**
 * Created by hsg on 11/4/16.
 */

public class HelpBizPresenter extends BasePresenter implements IHelpBizContract.IPresenter{

    private IHelpBizContract.IView view;

    public HelpBizPresenter(IHelpBizContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadData() {
        if (this.view == null){
            return;
        }

        this.view.updateView(DataFactory.getHelpBizData());
    }
}

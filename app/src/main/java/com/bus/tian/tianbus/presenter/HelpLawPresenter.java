package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IHelpLawContract;
import com.bus.tian.tianbus.model.data.DataFactory;

/**
 * Created by hsg on 11/4/16.
 */

public class HelpLawPresenter extends BasePresenter implements IHelpLawContract.IPresenter {

    private IHelpLawContract.IView view;

    public HelpLawPresenter(IHelpLawContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadData() {
        if (this.view == null){
            return;
        }

        this.view.updateView(DataFactory.getHelpLawData());
    }
}

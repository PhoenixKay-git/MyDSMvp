package com.bwie.test.mydsmvp.homepage.contract;

import com.bwie.test.bean.AdBean;
import com.bwie.test.bean.CatagoryBean;
import com.bwie.test.mydsmvp.base.BaseContract;

public interface HomPageContract {
    interface View extends BaseContract.BaseView {
        void getAdSuccess(AdBean adBean);
        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAd();
        void getCatagory();
    }
}

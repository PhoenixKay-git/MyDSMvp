package com.bwie.test.mydsmvp.classify.contract;

import com.bwie.test.mydsmvp.base.BaseContract;

public interface AddCartContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(String str);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void addCart(String uid, String pid, String token);
    }
}

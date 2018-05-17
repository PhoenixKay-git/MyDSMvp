package com.bwie.test.mydsmvp.shopcart.contract;

import com.bwie.test.bean.GetCartsBean;
import com.bwie.test.bean.SellerBean;
import com.bwie.test.mydsmvp.base.BaseContract;

import java.util.List;

public interface ShopcartContract {
    interface View extends BaseContract.BaseView {
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);
        void updateCartsSuccess(String msg);
        void deleteCartSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCarts(String uid, String token);
        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);
        void deleteCart(String uid, String pid, String token);
    }
}

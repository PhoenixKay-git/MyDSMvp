package com.bwie.test.mydsmvp.shopcart.presenter;

import com.bwie.test.bean.GetCartsBean;
import com.bwie.test.bean.SellerBean;
import com.bwie.test.mydsmvp.base.BasePresenter;
import com.bwie.test.mydsmvp.shopcart.contract.ShopcartContract;
import com.bwie.test.net.GetCartApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopcartPresenter extends BasePresenter<ShopcartContract.View> implements ShopcartContract.Presenter {
    private GetCartApi getCartApi;

    @Inject
    public ShopcartPresenter(GetCartApi getCartApi) {
        this.getCartApi = getCartApi;
    }

    @Override
    public void getCarts(String uid, String token) {
        getCartApi.getCatagory(uid,token)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<GetCartsBean>() {
            @Override
            public void accept(GetCartsBean getCartsBean) throws Exception {
                List<SellerBean> groupList = new ArrayList<>();//用于封装一级列表
                List<List<GetCartsBean.DataBean.ListBean>> childList = new ArrayList<>();//用于封装二级列表
                List<GetCartsBean.DataBean> data = getCartsBean.getData();

                for (int i = 0; i < data.size(); i++) {
                    GetCartsBean.DataBean dataBean = data.get(i);
                    SellerBean sellerBean = new SellerBean();
                    sellerBean.setSellerName(dataBean.getSellerName());
                    sellerBean.setSellerid(dataBean.getSellerid());
                    sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                    //true或者false要根据该商家下面的商品是否全选
                    groupList.add(sellerBean);

                    List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                    childList.add(list);
                }
                if (mView!=null){
                    mView.showCartList(groupList,childList);
                }
            }
        });
    }

    /**
     * 判断该商家下面的商品是否全选
     * @return
     */
    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean) {
        //获取该商家下面的所有商品
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if (0 == listBean.getSelected()) {
                //如果是0的话，表示有一个商品未选中
                return false;
            }
        }
        return true;
    }
}

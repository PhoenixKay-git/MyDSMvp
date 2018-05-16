package com.bwie.test.mydsmvp.shopcart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.test.bean.GetCartsBean;
import com.bwie.test.bean.SellerBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.mydsmvp.base.BaseActivity;
import com.bwie.test.mydsmvp.shopcart.contract.ShopcartContract;
import com.bwie.test.mydsmvp.shopcart.presenter.ShopcartPresenter;
import com.bwie.test.mydsmvp.R;
import com.bwie.test.utils.DialogUtil;
import com.bwie.test.utils.SharedPreferencesUtils;

import java.util.List;

public class ShopCartActivity extends BaseActivity<ShopcartPresenter> implements ShopcartContract.View {
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 合计：
     */
    private TextView mTvMoney;
    /**
     * 去结算：
     */
    private TextView mTvTotal;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //初始化dialog
        progressDialog = DialogUtil.getProgressDialog(this);
        String token = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "token", "");
        String uid = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "uid", "");
        mPresenter.getCarts(uid,token);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
    }

    /**
     * 判断所有商家是否全部选中
     * @param groupList
     * @return
     */
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
}

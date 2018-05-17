package com.bwie.test.mydsmvp.classify;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.bean.AdBean;
import com.bwie.test.bean.ProductsBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.mydsmvp.R;
import com.bwie.test.mydsmvp.base.BaseActivity;
import com.bwie.test.mydsmvp.classify.contract.AddCartContract;
import com.bwie.test.mydsmvp.classify.presenter.AddCartPresenter;
import com.bwie.test.mydsmvp.login.LoginActivity;
import com.bwie.test.mydsmvp.shopcart.ShopCartActivity;
import com.bwie.test.utils.GlideImageLoader;
import com.bwie.test.utils.SharedPreferencesUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class ListDetailsActivity extends BaseActivity<AddCartPresenter> implements View.OnClickListener, AddCartContract.View {
    private ProductsBean.DataBean bean;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    private ImageView mIvShare;
    /**
     * 购物车
     */
    private TextView mTvShopCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private AdBean.TuijianBean.ListBean listBean;
    private int flag;
    private String images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        //获取JavaBean
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", -1);
        if (flag == -1) {
            return;
        }
        if (flag == ListActivity.LISTACTIVITY) {
            bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");
            images = bean.getImages();
        } else {
            listBean = (AdBean.TuijianBean.ListBean) intent.getSerializableExtra("bean");
            images = listBean.getImages();
        }

        initView();
        //设置值
        setData();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list_details;
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        mTvShopCard = (TextView) findViewById(R.id.tvShopCard);
        mTvShopCard.setOnClickListener(this);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mTvAddCard.setOnClickListener(this);
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    /**
     * 设置值
     */
    private void setData() {
        int money = 0;
        if (flag == ListActivity.LISTACTIVITY) {
            money = bean.getSalenum();
        } else {
            money = listBean.getSalenum();
        }
        //给原价加横线
        SpannableString spannableString = new SpannableString("原价:" + money);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spannableString);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ListDetailsActivity.this, BannerDetailsActivity.class);
                intent.putExtra("imgs", images);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        String[] imgs = null;

        if (flag == ListActivity.LISTACTIVITY) {
            imgs = bean.getImages().split("\\|");
            mTvTitle.setText(bean.getTitle());
            mTvVipPrice.setText("现价：" + bean.getPrice());
        } else {
            imgs = listBean.getImages().split("\\|");
            mTvTitle.setText(listBean.getTitle());
            mTvVipPrice.setText("现价：" + listBean.getPrice());

        }
        //设置图片集合
        mBanner.setImages(Arrays.asList(imgs));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddCard:
                //先判断是否登录
                String token = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "token", "");
                if (TextUtils.isEmpty(token)) {
                    //还未登录
                    //跳转到登录页面
                    Intent intent = new Intent(ListDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //登录过了
                    String uid = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "uid", "");
                    int pid = 0;
                    if (flag == ListActivity.LISTACTIVITY) {
                        pid = bean.getPid();
                    } else {
                        pid = listBean.getPid();
                    }
                    mPresenter.addCart(uid, pid + "", token);
                }
                break;
            case R.id.tvShopCard:
                //跳转到购物车
                Intent intent = new Intent(ListDetailsActivity.this, ShopCartActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(String str) {
        Toast.makeText(ListDetailsActivity.this, str, Toast.LENGTH_LONG).show();
    }
}

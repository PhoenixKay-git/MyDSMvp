package com.bwie.test.mydsmvp.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.test.bean.ProductsBean;
import com.bwie.test.mydsmvp.R;
import com.bwie.test.mydsmvp.classify.adapter.MyAdapter;
import com.bwie.test.mydsmvp.widget.HackyViewPager;

import java.util.Arrays;
import java.util.List;

public class BannerDetailsActivity extends AppCompatActivity {
    private HackyViewPager mHvp;
    private TextView mTv;
    private ProductsBean.DataBean bean;
    private List<String> list;
    private int position;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_details);
        //获取JavaBean
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");
        String images = bean.getImages();
        String[] split = images.split("\\|");
        list = Arrays.asList(split);
        count = list.size();
        initView();

    }

    private void initView() {
        mHvp = (HackyViewPager) findViewById(R.id.hvp);
        mTv = (TextView) findViewById(R.id.tv);
        MyAdapter myAdapter = new MyAdapter(this, list);
        mHvp.setAdapter(myAdapter);
        mHvp.setCurrentItem(position);

        mTv.setText((position + 1) + "/" + count);

        mHvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                BannerDetailsActivity.this.position = p;
                mTv.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

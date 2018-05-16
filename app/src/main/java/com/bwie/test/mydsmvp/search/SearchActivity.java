package com.bwie.test.mydsmvp.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bwie.test.mydsmvp.R;
import com.bwie.test.mydsmvp.homepage.HomePageFragment;

public class SearchActivity extends AppCompatActivity {
    private TextView btn_fan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //隐藏原有标题
        getSupportActionBar().hide();

        btn_fan = (TextView) findViewById(R.id.btn_fan);
        btn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SearchActivity.this, HomePageFragment.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    //搜索
    public void btn_Click(View view) {
    }

    //清空历史搜索
    public void btn_clone(View view) {
    }
}

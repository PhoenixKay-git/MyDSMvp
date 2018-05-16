package com.bwie.test.component;

import com.bwie.test.module.HttpModule;
import com.bwie.test.mydsmvp.classify.ClassifyFragment;
import com.bwie.test.mydsmvp.classify.ListActivity;
import com.bwie.test.mydsmvp.classify.ListDetailsActivity;
import com.bwie.test.mydsmvp.homepage.HomePageFragment;
import com.bwie.test.mydsmvp.login.LoginActivity;
import com.bwie.test.mydsmvp.shopcart.ShopCartActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);
    void inject(HomePageFragment homePageFragment);

    void inject(ClassifyFragment classifyFragment);

    void inject(ListActivity listActivity);

    void inject(ListDetailsActivity listDetailsActivity);

    void inject(ShopCartActivity shopCartActivity);
}

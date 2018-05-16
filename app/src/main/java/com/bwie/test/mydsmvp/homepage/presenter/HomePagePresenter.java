package com.bwie.test.mydsmvp.homepage.presenter;

import com.bwie.test.bean.AdBean;
import com.bwie.test.bean.CatagoryBean;
import com.bwie.test.mydsmvp.base.BasePresenter;
import com.bwie.test.mydsmvp.homepage.contract.HomPageContract;
import com.bwie.test.net.AdApi;
import com.bwie.test.net.CatagoryApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePagePresenter extends BasePresenter<HomPageContract.View> implements HomPageContract.Presenter {
    private AdApi adApi;
    private CatagoryApi catagoryApi;

    @Inject
    public HomePagePresenter(AdApi adApi, CatagoryApi catagoryApi) {
        this.adApi = adApi;
        this.catagoryApi = catagoryApi;
    }

    @Override
    public void getAd() {
        adApi.getAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AdBean adBean) {
                        mView.getAdSuccess(adBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCatagory() {
        catagoryApi.getCatagory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                        mView.getCatagorySuccess(catagoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

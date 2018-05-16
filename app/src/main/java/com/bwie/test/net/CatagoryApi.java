package com.bwie.test.net;

import com.bwie.test.bean.CatagoryBean;

import io.reactivex.Observable;

public class CatagoryApi {
    private static CatagoryApi catagoryApi;
    private CatagoryApiService catagoryApiService;

    private CatagoryApi(CatagoryApiService catagoryApiService) {
        this.catagoryApiService = catagoryApiService;
    }

    public static CatagoryApi getCatagoryApi(CatagoryApiService catagoryApiService) {
        if (catagoryApi == null) {
            catagoryApi = new CatagoryApi(catagoryApiService);
        }
        return catagoryApi;
    }

    public Observable<CatagoryBean> getCatagory() {
        return catagoryApiService.getCatagory();
    }
}

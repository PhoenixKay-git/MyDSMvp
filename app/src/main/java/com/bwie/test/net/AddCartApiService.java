package com.bwie.test.net;

import com.bwie.test.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddCartApiService {
    @FormUrlEncoded
    @POST("product/addCart")
    Observable<BaseBean> addCart(@Field("Uid") String uid,
                                 @Field("Pid") String pid,
                                 @Field("Token") String token);

}

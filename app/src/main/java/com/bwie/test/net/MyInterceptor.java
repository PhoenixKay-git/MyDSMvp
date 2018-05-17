package com.bwie.test.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //创建一个FormBody.Builder对象,用于添加公共参数
        FormBody.Builder builder = new FormBody.Builder();
        //先获取原始的请求
        Request originalRequest = chain.request();
        //获取原始请求里的请求体数据
        FormBody formBody = (FormBody) originalRequest.body();
        for (int i = 0; i < formBody.size(); i++) {
            builder.add(formBody.name(i),formBody.value(i));
        }
        builder.add("source","android");
        FormBody body = builder.build();

        Request request = new Request.Builder()
                .url(originalRequest.url())
                .post(body)
                .build();

        Response response = chain.proceed(request);
        return response;
    }
}

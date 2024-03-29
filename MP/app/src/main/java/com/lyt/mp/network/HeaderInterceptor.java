package com.lyt.mp.network;

import com.lyt.mp.UserDataManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加统一请求header
 * Created by lx on 2016/8/25.
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("appCode","AGENT_ANDROID")
                .addHeader("deviceType","Android")
                .addHeader("token", UserDataManager.getInstance().getToken())
                .build();
        return chain.proceed(request);
    }

}

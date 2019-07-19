package com.lyt.mp.network;

import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpManager {

    // debug
    private String host = "http://10.8.9.183:8000";


    //product
//    private String host = "";



    /**
     * 网络访问要求singleton
     */
    private static OkHttpManager instance;

    // 必须要用的okhttpclient实例,在构造器中实例化保证单一实例
    private OkHttpClient mOkHttpClient;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private android.os.Handler mHandler;

    private String TAG = "OkHttpManager";

    public interface ResponseCallBack<T> {
        public void onFailure(String e);

        public void onSuccess(Map response);
    }

    private OkHttpManager() {
        /**
         * okHttp3中超时方法移植到Builder中
         */

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            mOkHttpClient = (new OkHttpClient()).newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new HeaderInterceptor())  // 统一Header
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        mHandler = new android.os.Handler(Looper.getMainLooper());

    }

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }

        return instance;
    }

    public void initBuilder() {

    }

    /**
     * 对外提供的Get方法访问
     *
     * @param url
     * @param callBack
     */
    public void get(String url, ResponseCallBack callBack) {
        /**
         * 通过url和GET方式构建Request
         */
        Log.i(TAG, "get: ");
        Request request = bulidRequestForGet(url);
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack);
    }

    /**
     * GET方式构建Request
     *
     * @param url
     * @return
     */
    private Request bulidRequestForGet(String url) {

        return new Request.Builder()
                .url(host + url)
                .get()
                .build();
    }

    /**
     * 对外提供的Post方法访问
     *
     * @param url
     * @param parms:   提交内容为表单数据
     * @param callBack
     */
    public void post(String url, Map parms, ResponseCallBack callBack) {
        /**
         * 通过url和POST方式构建Request
         */
        Log.i(TAG, url);
        Request request = bulidRequestForPostByForm(host + url, parms, false);
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack);

    }


    public void postMultiPart(String url, Map<String, String> parms, ResponseCallBack callBack) {
        /**
         * 通过url和POST方式构建Request
         */
        Log.i(TAG, url);
        Request request = bulidRequestForPostByForm(host + url, parms, true);
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack);
    }

    /**
     * POST方式构建Request {Form}
     *
     * @param url
     * @param parms
     * @return
     */
    private Request bulidRequestForPostByForm(String url, Map<String, String> parms, boolean isMultiPart) {

        RequestBody body = null;

        if (isMultiPart) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder = initBuilder(parms, builder);
            body = builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            builder = initBuilder(parms, builder);
            body = builder.build();
        }
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    private MultipartBody.Builder initBuilder(Map<String, String> parms, MultipartBody.Builder builder) {

        if (parms != null) {
            for (Map.Entry entry : parms.entrySet()) {
                if (entry.getValue() instanceof String) {
                    builder.addFormDataPart((String) entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof File) {
                    File file = (File) entry.getValue();
                    builder.addFormDataPart((String) entry.getKey(), file.getName(), RequestBody.create(null, file));
                }

            }

        }

        return builder;
    }

    private FormBody.Builder initBuilder(Map<String, String> parms, FormBody.Builder builder) {

        if (parms != null) {
            for (Map.Entry entry : parms.entrySet()) {
                if (entry.getValue() instanceof String) {
                    builder.add((String) entry.getKey(), (String) entry.getValue());
                }
            }

        }

        return builder;
    }


    /**
     * 对外提供的Post方法访问
     *
     * @param url
     * @param json:    提交内容为json数据
     * @param callBack
     */
    public void post(String url, String json, ResponseCallBack callBack) {
        /**
         * 通过url和POST方式构建Request
         */
        Request request = bulidRequestForPostByJson(host + url, json);


        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack);

    }

    /**
     * POST方式构建Request {json}
     *
     * @param url
     * @param json
     * @return
     */
    private Request bulidRequestForPostByJson(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Log.i(TAG, url);
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    private void requestNetWork(final Request request, final ResponseCallBack callBack) {

        /**
         * 此处可以处理联网环境
         */
//        callBack.onBefore(request);

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("okHttpError", "请求出错" + e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    Gson gson = new Gson();
                    Map<String,Object> map = gson.fromJson(json,Map.class);
                    Log.i(TAG, json);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(map);
//                            callBack.onAfter();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure("请求发生错误" + response.body());
//                            callBack.onAfter();
                        }
                    });
                }
                response.body().close();
            }
        });
    }


}


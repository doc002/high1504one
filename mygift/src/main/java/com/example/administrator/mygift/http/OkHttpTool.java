package com.example.administrator.mygift.http;

import android.os.Handler;

import com.example.administrator.mygift.tools.GsonTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 16-3-16.
 */

/**
 * OkHttpClient的使用封装
 */
public class OkHttpTool {
    public static OkHttpClient okHttpClient;

    static {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }

    private static Handler mHandler = new Handler();


    /**
     * OKHttp的GET请求
     *
     * @param url
     * @param cls
     * @param callBack
     * @param requestCode
     * @param <T>
     */
    public static <T> void okGet(String url, final Class<T> cls, final IOkCallBack callBack,
                                 int requestCode) {
        //创建一个请求
        //.header("Content-Type","text/html;charset:utf-8") Http协议的请求头信息
        Request request = new Request.Builder().tag(requestCode).url(url).build();
        //执行请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                //执行在工作线程（非UI线程），不能直接对UI进行更新
            }

            //请求成功
            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                //执行在工作线程（非UI线程），不能直接对UI进行更新
                String result = response.body().string();
                Object tag = response.request().tag();
                final T beanInfo = GsonTool.parseJson2Object(result, cls);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(beanInfo);
                    }
                });
            }
        });

    }

    /**
     * OkHttp 的Post请求
     *
     * @param url      请求的URl
     * @param param    传入的返回参数
     * @param cls      返回的Object对象的class类型
     * @param callBack 回调接口
     * @param <T>
     */
    public static <T> void okPost(String url, Map<String, String> param, final Class<T> cls,
                                  final IOkCallBack callBack) {
        //application/json是Http协议中的ContentType，charset=utf-8是Http协议中的编码格式
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        //设置POST的请求参数
        String formatParam = formatParam(param);
        RequestBody requestBody = RequestBody.create(mediaType, formatParam);
        //创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final T object = GsonTool.parseJson2Object(response.body().string(), cls);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(object);
                    }
                });
            }
        });

    }

    private static String formatParam(Map<String, String> param) {
        JSONObject jsonObject = new JSONObject();
        Set<String> keySet = param.keySet();
        for (String key : keySet) {
            try {
                jsonObject.put(key, param.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

}

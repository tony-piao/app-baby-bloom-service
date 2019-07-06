package com.babybloom.web.helper;

import com.babybloom.web.constant.CommonErrorId;
import com.babybloom.web.exception.ApplicationException;
import okhttp3.*;

import java.io.IOException;

/**
 * httpClient 工具类,采用okhttp3的包
 *
 * @author ZZ
 */
public class HttpHelper {

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (!response.isSuccessful()) {
                throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, "http错误代码 " + response.code());
            }
            return response.body().string();
        } catch (Exception e) {
            throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, e.getMessage());
        }
    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param data
     * @param contentType
     * @return
     */
    public static String httpPost(String url, String data, String contentType) {
        String result = null;
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse(contentType), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, "http错误代码 " + response.code());
            }
            result = response.body().string();
        } catch (IOException e) {
            throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, e.getMessage());
        }
        return result;
    }
}

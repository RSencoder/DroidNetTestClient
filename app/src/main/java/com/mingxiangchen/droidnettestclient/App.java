package com.mingxiangchen.droidnettestclient;

import android.app.Application;
import android.content.Context;

import java.net.InetAddress;
import java.net.UnknownHostException;

import okhttp3.OkHttpClient;

public class App extends Application {
    private static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        //固定地址方法
//        DroidNetSocketFactory factory = null;
//        try {
//            factory = new DroidNetSocketFactory
//                    (InetAddress.getByName("10.5.70.184"), 65432);
//            client = new OkHttpClient.Builder()
//                    .socketFactory(factory)
//                    .build();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        //系统自动分配地址和端口
        client = new OkHttpClient();
    }

    public static OkHttpClient getClient() {
        return client;
    }
}

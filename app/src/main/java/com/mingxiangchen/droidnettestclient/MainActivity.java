package com.mingxiangchen.droidnettestclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtResponseContent;
    private String TAG = "DroidNetTestClient";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText requestContent = findViewById(R.id.edit_request_content);
        final EditText serverIPAddr = findViewById(R.id.edit_ipaddr);
        Button btnSend = findViewById(R.id.btn_send);
        txtResponseContent = findViewById(R.id.text_response_content);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest(requestContent.getText().toString(), serverIPAddr.getText().toString());
            }
        });
    }

    private void sendRequest(final String requestContent, final String serverIP) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("requestContent", requestContent)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://" + serverIP + ":8123")
                            .post(requestBody)
                            .build();
                    Response response = App.getClient().newCall(request).execute();
                    if (response.isSuccessful()) {
                        showResponse(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String responseContent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResponseContent.setText(responseContent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getClient().dispatcher().executorService().shutdown();
        App.getClient().connectionPool().evictAll();
//        client.cache().close();
    }
}

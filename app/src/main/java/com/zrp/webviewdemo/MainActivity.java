package com.zrp.webviewdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zrp.webviewdemo.web.CommonWebActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到网页容器页面，通过intent传值的方式使其更加灵活，外部不需要关系容器内部的逻辑
                Intent intent = new Intent(MainActivity.this, CommonWebActivity.class);
                intent.putExtra("url", "file:///android_asset/html.html");
                intent.putExtra("param", "这是需要传给网页的数据");
                startActivity(intent);
            }
        });

        initScheme(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initScheme(intent);
    }

    private void initScheme(Intent intent) {
        Log.d(TAG, "initScheme: ---DataString--->" + intent.getDataString());
        Uri uri = intent.getData();
        if (uri != null && "zrp_test.net".equals(uri.getHost())) {
            Log.d(TAG, "initScheme: ---data--->" + uri.getQueryParameter("data"));
        }
    }
}

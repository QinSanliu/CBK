package com.qgz.qinsa.cbk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.qgz.qinsa.cbk.R;
import com.qgz.qinsa.cbk.bean.NewsEntity;

public class DetailsActivity extends AppCompatActivity {

    private WebView webView;
    private TextView title_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        NewsEntity entity = (NewsEntity) intent.getSerializableExtra("mList");
        String wap_content = intent.getStringExtra("wap_content");
        Log.d("TAG", "onCreate: "+wap_content);
        initView();
        webView = (WebView) findViewById(R.id.webView_details);
        webView.loadDataWithBaseURL(null,wap_content,"text/html","utf-8",null);

        title_details.setText(entity.getTitle());

    }

    private void initView() {
        title_details = (TextView) findViewById(R.id.title_details);
    }

    public void detailButtons(View view) {
        switch (view.getId()){
            case R.id.contentback:
                onBackPressed();
                Toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
                break;
            case R.id.contentshare:
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.collectcontent:
                Toast.makeText(this,"收藏",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

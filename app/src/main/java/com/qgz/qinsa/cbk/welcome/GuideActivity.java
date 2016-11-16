package com.qgz.qinsa.cbk.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.qgz.qinsa.cbk.MainActivity;
import com.qgz.qinsa.cbk.R;

public class GuideActivity extends AppCompatActivity {

    private ImageView slide3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();


    }

    private void initView() {
        slide3 = (ImageView) findViewById(R.id.slide3);
        slide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

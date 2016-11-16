package com.qgz.qinsa.cbk.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qgz.qinsa.cbk.MainActivity;
import com.qgz.qinsa.cbk.R;

public class FlashActivity extends AppCompatActivity {

    SharedPreferences preferences;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        preferences = getSharedPreferences("first_info",MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirst",true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirst) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(),GuideActivity.class);
                    startActivity(intent);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isFirst",false);
                    editor.commit();
                }else {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
                finish();
            }
        },1000);


    }
}

package com.qgz.qinsa.cbk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by qinsa on 2016/11/15.
 */

public class NetWorkUtils {
    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return false;
        }
        switch (networkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                Toast.makeText(context,"WIFI已连接",Toast.LENGTH_SHORT).show();

                return true;
            case ConnectivityManager.TYPE_MOBILE:
                Toast.makeText(context,"移动网络",Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }
}

package com.qgz.qinsa.cbk.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by qinsa on 2016/11/15.
 */

public class MyLruCache extends LruCache<String,Bitmap> {

    public MyLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight();
    }
}

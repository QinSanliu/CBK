package com.qgz.qinsa.cbk.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgz.qinsa.cbk.R;
import com.qgz.qinsa.cbk.bean.NewsEntity;
import com.qgz.qinsa.cbk.cache.MyLruCache;
import com.qgz.qinsa.cbk.utils.HttpUtils;
import com.qgz.qinsa.cbk.utils.SDCardUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinsa on 2016/11/14.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<NewsEntity> datas = new ArrayList<>();
    private Handler mHandler;
    private MyLruCache myLruCache;

    public ListViewAdapter(Context context, Handler mHandler) {
        this.context = context;
        this.mHandler = mHandler;

        int maxSize = (int) Runtime.getRuntime().maxMemory();
        myLruCache = new MyLruCache(maxSize);
    }

    public NewsEntity getEntity(int position){
        return datas.get(position);
    }

    public void setDatas(List<NewsEntity> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listview,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(datas.get(position).getTitle());
        holder.source.setText(datas.get(position).getSource());
        holder.nickname.setText(datas.get(position).getNickname());
        holder.create_time.setText(datas.get(position).getCreate_time());

        holder.image.setImageResource(R.mipmap.empty);
        holder.image.setTag(datas.get(position).getWap_thumb());
        final String imageUrl = datas.get(position).getWap_thumb();


//        Bitmap cacheBitmap = getCache(datas.get(position).getWap_thumb());
//        if (cacheBitmap != null) {
//            holder.image.setImageBitmap(cacheBitmap);
//        }else


        //开启子线程下载图片数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = HttpUtils.getBitmapFromNetwork(datas.get(position).getWap_thumb());

                //下载完成获取图片数据，通过handler传回主线程更新UI
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (holder.image.getTag().equals(imageUrl)){

                            holder.image.setImageBitmap(bitmap);

                            //缓存到内存

                        }

                    }
                });
            }
        }).start();



        return view;
    }

    private Bitmap getCache(String img){
        img = img.replaceAll("/","");
        Bitmap bitmap = myLruCache.get(img);
        if (bitmap!=null) {
            return bitmap;
        }else {
            String root = context.getExternalCacheDir().getAbsolutePath();
            String fileName = root+ File.separator+img;
            byte[] bytes = SDCardUtils.getbyteFromFile(fileName);
            if (bytes != null) {
                Bitmap bitmapSD = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

                myLruCache.put(img,bitmapSD);

                return bitmapSD;
            }
        }
        return null;
    }

    public void addAll(List<NewsEntity> newsEntityList){
        datas.addAll(newsEntityList);
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView image;
        TextView title,source,nickname,create_time;

        public ViewHolder(View view) {
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.source = (TextView) view.findViewById(R.id.source);
            this.nickname = (TextView) view.findViewById(R.id.nickname);
            this.create_time = (TextView) view.findViewById(R.id.create_time);
        }
    }
}

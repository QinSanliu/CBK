package com.qgz.qinsa.cbk.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qinsa on 2016/11/14.
 */
public class HttpUtils {


    public static String downloadData(String urlString){
        InputStream is=null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            if(connection.getResponseCode()==200){
                Log.i("1608","连接成功");
                is=connection.getInputStream();
                byte[] buffer=new byte[1024];
                int len=-1;
                while(true){
                    len=is.read(buffer);
                    if(len==-1){
                        break;
                    }
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                buffer=null;
                return baos.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        Log.i("1608","111");
        return "";
    }

    public static Bitmap getBitmapFromNetwork(String requestUrl) {
        InputStream is = null;
        URL url = null;
        try {
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                // 输入流解析为Bitmap对象
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static String downloadDataPOST(String urlString,int type,int page){
        InputStream is=null;
        OutputStream os = null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            // post请求的参数
            String data = "type=" + type + "&page=" + page+ "&rows=" + 15;
            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            connection.connect();
            if(connection.getResponseCode()==200){
                Log.i("1606","连接成功");
                is=connection.getInputStream();
                byte[] buffer=new byte[1024];
                int len=-1;
                while(true){
                    len=is.read(buffer);
                    if(len==-1){
                        break;
                    }
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                buffer=null;
                return baos.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        Log.i("1606","111");
        return "";
    }

    public static String downloadDataWeb(String urlString,int id){
        InputStream is=null;
        OutputStream os = null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            // post请求的参数
            String data = "id=" + id;
            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            connection.connect();
            if(connection.getResponseCode()==200){
                Log.i("1608","连接成功");
                is=connection.getInputStream();
                byte[] buffer=new byte[1024];
                int len = -1;
                while(true){
                    len=is.read(buffer);
                    if(len==-1){
                        break;
                    }
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                buffer=null;
                return baos.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        Log.i("1608","111");
        return "";
    }

    public static String searchDataPOST(String urlString,int page,String search){
        InputStream is=null;
        OutputStream os = null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            // post请求的参数
            String data = "page=" + page + "&rows=" + 10+ "&search=" + search;
            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            connection.connect();
            if(connection.getResponseCode()==200){
                Log.i("1606","连接成功");
                is=connection.getInputStream();
                byte[] buffer=new byte[1024];
                int len=-1;
                while(true){
                    len=is.read(buffer);
                    if(len==-1){
                        break;
                    }
                    baos.write(buffer, 0, len);
                }
                baos.flush();
                buffer=null;
                return baos.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        Log.i("1606","111");
        return "";
    }

    public static void getByteFromUrl(final String path, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                ByteArrayOutputStream baos = null;
                try {
                    URL url = new URL(path);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setConnectTimeout(5000);

                    if (conn.getResponseCode() == 200) {
                        is = conn.getInputStream();
                        baos = new ByteArrayOutputStream();

                        int len = 0;

                        byte[] buf = new byte[1024*8];

                        while((len = is.read(buf))!=-1) {
                            baos.write(buf,0,len);
                        }

                        byte[] data = baos.toByteArray();

                        //需要通过Handler发送数据给主线程

                        Message msg = Message.obtain();
                        msg.what = HandlerWhat.WHAT;
                        msg.obj = data;
                        handler.sendMessage(msg);

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (baos != null) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static byte[] getByteFromUrl(String path){
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = 0;
                byte[] bytes = new byte[1024*8];

                if ((len = is.read(bytes)) != -1) {
                    baos.write(bytes,0,len);
                }

                return baos.toByteArray();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

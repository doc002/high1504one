package com.example.administrator.mygift.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Songkai on 2016/3/8.
 */
public class ImageLoader {

    private static ExecutorService mExecutorService;

    static {
        mExecutorService = Executors.newFixedThreadPool(2);
    }

    public static void laod(Context context, String url, ImageView imageView) {
        Bitmap cacheBitmap = MemoryCacheTool.getCache(url);
        if(cacheBitmap == null) {
            imageView.setTag(url);
            DiskCacheTool.init(context);
            mExecutorService.execute(new ImagThread(url, imageView));
        }else{
            imageView.setImageBitmap(cacheBitmap);
        }
    }

    static Handler mHandler = new Handler();

    static class ImagThread implements Runnable {
        private String urlParam;
        private ImageView imageView;

        public ImagThread(String urlParam, ImageView imageView) {
            this.urlParam = urlParam;
            this.imageView = imageView;
            imageView.setImageBitmap(null);
        }


        private void setImageView(final Bitmap bitmap){
            if (mHandler !=null){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imageView!=null){
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        }

        @Override
        public void run() {
            final Bitmap cacheBitmap = DiskCacheTool.getCacheBitmap(urlParam);
            if(cacheBitmap!=null){
                MemoryCacheTool.putCache(urlParam,cacheBitmap);
                setImageView(cacheBitmap);
            }else {
                connServerBitmap();
            }
        }

        /**
         * 在网络上请求数据
         */
        private void connServerBitmap() {
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            try {
                URL url = new URL(urlParam);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer)) != -1) {
                    byteStream.write(buffer, 0, len);
                }
                inputStream.close();
                byte[] byteArray = byteStream.toByteArray();
                BitmapFactory.Options options = new BitmapFactory.Options();
                //不会真正的将Bitmap对象加载到内存中
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                int height = options.outHeight;
                int width = options.outWidth;
                //计算图片的大小，并决定压缩比例
                int ratio1 = width/300;
                int ratio2 = height/300;
                int ratio = Math.max(ratio1,ratio2);
//              如 options.inSampleSize = 2;表示将图片压缩为原来的1/2；
                if(ratio<1){
                    options.inSampleSize = 1;
                }else{
                    options.inSampleSize = ratio;
                }

                options.inJustDecodeBounds = false;
                final Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length,options);
                if (bitmap != null) {
                    DiskCacheTool.writeDiskCache(urlParam,bitmap);
                    setImageView(bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (byteStream != null) {
                    try {
                        byteStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

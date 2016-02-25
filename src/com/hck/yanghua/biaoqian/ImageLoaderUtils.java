package com.hck.yanghua.biaoqian;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * UIL 工具类
 * Created by sky on 15/7/26.
 */
public class ImageLoaderUtils {

    /**
     * display local image
     * @param uri
     * @param imageView
     * @param options
     */
    public static void displayLocalImage(String uri, ImageView imageView, DisplayImageOptions options) {
       // ImageLoader.getInstance().displayImage("file://" + uri, new ImageViewAware(imageView), options, null, null);
    	ImageLoader.getInstance().displayImage("drawable://" + uri, imageView,options);
    }

    /**
     * display Drawable image
     * @param uri
     * @param imageView
     * @param options
     */
    public static void displayDrawableImage(String uri, ImageView imageView, DisplayImageOptions options) {
        //ImageLoader.getInstance().displayImage("drawable://" + uri, imageView, options, null, null);
        ImageLoader.getInstance().displayImage("drawable://" + uri, imageView,options);
    }



}

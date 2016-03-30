package com.bartoszlach.bitbetgo;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by bartoszlach on 17.03.2016.
 */
public class ImagesHandle {

    public static void loadImage(String uri, ImageView imageView){
        MainActivity.imageLoader.displayImage(MainActivity.SERVER_ADDRESS +"api/getLogo/" +uri, imageView);
    }
}

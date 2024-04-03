package com.shiva.ananta.models;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ReelLoader {

    // Load reel using Glide with caching and optimizations
    public static void loadReel(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.ic_delete)
                .diskCacheStrategy(DiskCacheStrategy.ALL); // Cache both original & resized image

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


    // Preload reel using Glide
    public static void preloadReel(Context context, String url) {
        Glide.with(context)
                .load(url)
                .preload();
    }
}

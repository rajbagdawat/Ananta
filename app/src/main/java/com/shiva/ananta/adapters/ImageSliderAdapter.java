package com.shiva.ananta.adapters;

import static com.shiva.ananta.R.drawable.icon_fav_filled;
import static com.shiva.ananta.adapters.AnantaPostAdapter.likecount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;
import com.shiva.ananta.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {
    private Context context;
//    private int[] imageResources;
//    private String[] imageResources;
//    public ImageSliderAdapter(Context context, int[] imageResources) {
//        this.context = context;
//        this.imageResources = imageResources;
//    }
     AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;
    CheckBox LikecheckBox;
    static int likecount = 0;
    TextView Liketextview;
    ArrayList<String> imageResources;
    static boolean state = false;

    public ImageSliderAdapter(Context context,  ArrayList<String> imageResources) {
        this.context = context;
        this.imageResources = imageResources;
    }


    ImageView heartimg;

    @Override
    public int getCount() {
        return imageResources.size();
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_imagesrecycleview, container, false);
        View view1 = inflater.inflate(R.layout.anantacomponent, container, false);

        ImageView imageView = view.findViewById(R.id.OnclickImageRecycele);

        Picasso.get().load(imageResources.get(position)).placeholder(R.drawable.ramayann).into(imageView);
//       imageView.setImageURI(Uri.parse(imageResources.get(position)));

        heartimg = view1.findViewById(R.id.heart_img);
        final Drawable drawable = heartimg.getDrawable();
        LikecheckBox = view1.findViewById(R.id.anatalikepostimageView);
        Liketextview = view1.findViewById(R.id.anatalikeposttextView);

        imageView.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {

            }

            @SuppressLint("NewApi")
            @Override
            public void onDoubleClick(View view) {
             state = true;
             AnantaPostAdapter anantaPostAdapter = new AnantaPostAdapter(true);
//             anantaPostAdapter.calldubletap();
            }

        }));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

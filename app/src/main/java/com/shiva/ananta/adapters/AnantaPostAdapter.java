package com.shiva.ananta.adapters;

import static com.shiva.ananta.R.drawable.anantaposticon_fav_unfilled;
import static com.shiva.ananta.R.drawable.icon_fav_filled;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.viewpager.widget.ViewPager;

import com.shiva.ananta.R;
import com.shiva.ananta.models.AnantaPostGS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class AnantaPostAdapter extends RecyclerView.Adapter<AnantaPostAdapter.ViewHolder> {

    ArrayList<AnantaPostGS> anantaPostGS;
    Context context;
    public LinearLayout dotsLayout;
   static int count;
    static int likecount = 0;
    boolean checked = false;
    boolean state_tap;

    public AnantaPostAdapter(Context context ,ArrayList<AnantaPostGS> anantaPostGS){
      this.context = context;
      this.anantaPostGS = anantaPostGS;
    }
    public AnantaPostAdapter(boolean state_tap){
        this.state_tap = state_tap;
    }

    @NonNull
    @Override
    public AnantaPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anantacomponent,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnantaPostAdapter.ViewHolder holder, int position) {
        AnantaPostGS anantaPostGS1 = anantaPostGS.get(position);
        Picasso.get().load(anantaPostGS1.getProfileimg()).placeholder(R.drawable.ananta1).into(holder.storyprofile);
        holder.storyname.setText(anantaPostGS1.getUsername());
        holder.topic.setText("प्रश्न :- "+anantaPostGS1.getTopic());


        ImageSliderAdapter imageSlider_Adapter = new ImageSliderAdapter(context, anantaPostGS1.getArrayList());
        holder.viewPager.setAdapter(imageSlider_Adapter);

        addDotsIndicator(0);
        count = anantaPostGS1.getArrayList().size();

        holder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                addDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



        holder.LikecheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            anantaPostGS1.setChecked(isChecked);
            if(!checked){
                likecount++;
            }
            if (isChecked) {
                holder.LikecheckBox.setButtonDrawable(icon_fav_filled);
                holder.Liketextview.setText(String.valueOf(likecount));
            }else {
                holder.LikecheckBox.setButtonDrawable(anantaposticon_fav_unfilled);
                likecount--;
                holder.Liketextview.setText(String.valueOf(likecount));
            }
        });



    }

    public void addDotsIndicator(int position) {
        dotsLayout.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(context);
            dot.setImageResource(R.drawable.dot_inactive);
            dot.setPadding(8, 0, 8, 0);
            dotsLayout.addView(dot);
            if (i == position) {
                dot.setImageResource(R.drawable.dot_active);
            }
        }
    }


    @Override
    public int getItemCount() {
        return anantaPostGS.size();
    }

    ImageView heartimg;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView storyprofile;
        ImageView storyactivering,storyinactivering;
        TextView storyname,topic;
        ViewPager viewPager;
        AnimatedVectorDrawableCompat avd;
        AnimatedVectorDrawable avd2;
        CheckBox LikecheckBox;
        TextView Liketextview;
        FrameLayout layout;

        @SuppressLint("NewApi")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storyprofile = itemView.findViewById(R.id.storyprofile);
            storyactivering = itemView.findViewById(R.id.storyactivering);
            storyinactivering = itemView.findViewById(R.id.storyinactivering);
            storyname = itemView.findViewById(R.id.anantaporfilestoryname);
            topic = itemView.findViewById(R.id.anantaporfilestorytopic);
            viewPager = itemView.findViewById(R.id.anantacomponentphoto_viewpager);

            dotsLayout = itemView.findViewById(R.id.dotsContainer);
            LikecheckBox = itemView.findViewById(R.id.anatalikepostimageView);
            Liketextview = itemView.findViewById(R.id.anatalikeposttextView);
            layout = itemView.findViewById(R.id.layoutcomponent);
            storyprofile.setOnClickListener(this);


            heartimg = itemView.findViewById(R.id.heart_img);
            final Drawable drawable = heartimg.getDrawable();
            viewPager.setClickable(true);

//            layout.setOnClickListener(new DoubleClick(new DoubleClickListener() {
//                @SuppressLint({"NewApi", "ClickableViewAccessibility"})
//                @Override
//                public void onSingleClick(View view) {
//
//                    Log.d("touched","touched");
//                }
//                @SuppressLint("ResourceType")
//                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                @Override
//                public void onDoubleClick(View view) {
//                    Log.d("touched","touched");
//                    heartimg.setAlpha(1f);
//                    if(drawable instanceof AnimatedVectorDrawableCompat){
//                        avd = (AnimatedVectorDrawableCompat) drawable;
//                        avd.start();
//                    } else if (drawable instanceof AnimatedVectorDrawable) {
//                        avd2 = (AnimatedVectorDrawable) drawable;
//                        avd2.start();
//                    }
//
//                    LikecheckBox.setButtonDrawable(icon_fav_filled);
//                    Liketextview.setText(String.valueOf(likecount));
//
//                }
//            }));

            if(state_tap){
                Log.d("stategh","touched");
                heartimg.setAlpha(1f);
                if(drawable instanceof AnimatedVectorDrawableCompat){
                    avd = (AnimatedVectorDrawableCompat) drawable;
                    avd.start();
                } else if (drawable instanceof AnimatedVectorDrawable) {
                    avd2 = (AnimatedVectorDrawable) drawable;
                    avd2.start();
                }

                LikecheckBox.setButtonDrawable(icon_fav_filled);
                Liketextview.setText(String.valueOf(likecount));
            }
        }



        @Override
        public void onClick(View v) {
                storyactivering.setVisibility(View.GONE);
                storyinactivering.setVisibility(View.VISIBLE);

        }
    }


}

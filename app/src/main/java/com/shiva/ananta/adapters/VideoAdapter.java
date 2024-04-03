package com.shiva.ananta.adapters;

import static com.shiva.ananta.R.drawable.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;
import com.shiva.ananta.R;
import com.shiva.ananta.models.VideoGS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    ArrayList<VideoGS> videoGSArrayList;
//    List<VideoGS> videoGSArrayList;
    static int count;
    int num = 0;

    boolean flag = false;
    MediaPlayer mediaPlayer;
    @SuppressLint("StaticFieldLeak")
   static Context context ;
  boolean checked = false;
  boolean checkedlikes = false;
    public VideoAdapter(ArrayList<VideoGS> videoGSArrayList) {
        this.videoGSArrayList = videoGSArrayList;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_component,parent,false);
        return new VideoViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, @SuppressLint("RecyclerView") int position) {
      VideoGS videoGS = videoGSArrayList.get(position);

       Log.d("spadfdnshot",videoGSArrayList.get(position).getVideoTitle());
      holder.title.setText(videoGSArrayList.get(position).getVideoTitle());
      holder.desc.setText(videoGSArrayList.get(position).getVideoDesc());
      Picasso.get().load(videoGSArrayList.get(position).getUploaderprofile()).placeholder(ananta1).into(holder.uploaderprofile);
      holder.videoView.setVideoPath(videoGSArrayList.get(position).getVideoUrl());
      holder.liketext.setText(videoGSArrayList.get(position).getVideoLike());
      holder.commenttext.setText(videoGSArrayList.get(position).getVideoComment());
      holder.sharetext.setText(videoGSArrayList.get(position).getVideoShare());
      context = holder.videoView.getContext();
      holder.Audiooff.setVisibility(View.GONE);
      holder.Audioon.setVisibility(View.GONE);
      holder.loadingbar.setVisibility(View.GONE);

      count = Integer.parseInt(videoGSArrayList.get(position).getVideoLike());
      holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
          @Override
          public void onPrepared(MediaPlayer mp) {
              mp.start();
              float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
              float screenRatio = holder.videoView.getWidth() / (float) holder.videoView.getHeight();
              float sclae = videoRatio / screenRatio;

              if (sclae >= 1.15f){
                  holder.videoView.setScaleX(sclae);
              }else {
                  holder.videoView.setScaleY(1.24f/sclae);
              }
              mediaPlayer = mp;
          }
      });


      holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
          @Override
          public void onCompletion(MediaPlayer mp) {
              mp.start();
          }
      });


        holder.LikecheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            FirebaseApp.initializeApp(context);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Videos");
            Map<String, Object> updates = new HashMap<>();
            videoGS.setChecked(isChecked);
//               if(!checked){
//                count++;
//               }
                if (isChecked) {
                        count++;
//                        updates.put("videoLikes", count);
//                        databaseReference.child("video"+position).updateChildren(updates);
                        holder.LikecheckBox.setButtonDrawable(icon_fav_filled);
//                      holder.liketext.setText(videoGSArrayList.get(position).getVideoLike());
//                      notifyDataSetChanged();
                        holder.liketext.setText(String.valueOf(count));
                }else {
                        count--;
//                        updates.put("videoLikes", count);
//                        databaseReference.child("video"+position).updateChildren(updates);
                        holder.LikecheckBox.setButtonDrawable(icon_fav_unfilled);
//                      holder.liketext.setText(videoGSArrayList.get(position).getVideoLike());
//                      notifyDataSetChanged();
                        holder.liketext.setText(String.valueOf(count));
                }
        });

    }


    @Override
    public int getItemCount() {
        return videoGSArrayList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView title,desc,liketext,commenttext,sharetext;
        AnimatedVectorDrawableCompat avd;
        AnimatedVectorDrawable avd2;
        ImageView heartimg,Audiooff,Audioon;
        CheckBox LikecheckBox;
        ImageView uploaderprofile;
        FrameLayout layout;
        View loadingbar;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_View);
            title = itemView.findViewById(R.id.videoTitle);
            desc = itemView.findViewById(R.id.videoDesc);
            heartimg = itemView.findViewById(R.id.heart_img);
            videoView = itemView.findViewById(R.id.video_View);
            LikecheckBox = itemView.findViewById(R.id.checkBox);
            Audioon = itemView.findViewById(R.id.audioon);
            Audiooff = itemView.findViewById(R.id.audiooff);
            liketext = itemView.findViewById(R.id.liketextview);
            commenttext = itemView.findViewById(R.id.commenttextview);
            sharetext = itemView.findViewById(R.id.sharetextview);
            uploaderprofile = itemView.findViewById(R.id.imageId);
            loadingbar = itemView.findViewById(R.id.loadingreelbar);
            loadingbar.setVisibility(View.VISIBLE);

            heartimg = itemView.findViewById(R.id.heart_img);
            final Drawable drawable = heartimg.getDrawable();
            itemView.setOnClickListener(new DoubleClick(new DoubleClickListener() {
                @SuppressLint({"NewApi", "ClickableViewAccessibility"})
                @Override
                public void onSingleClick(View view) {
                    num++;

                    if (num % 2 == 0) {
                        checked = true;
                        soundOnOff(checked);
                    }
                    else {
                        checked = false;
                        soundOnOff(checked);
                    }
                }



                @SuppressLint({"ResourceType", "NotifyDataSetChanged"})
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onDoubleClick(View view) {
                    heartimg.setAlpha(1f);
                    if(drawable instanceof AnimatedVectorDrawableCompat){
                        avd = (AnimatedVectorDrawableCompat) drawable;
                        avd.start();
                    } else if (drawable instanceof AnimatedVectorDrawable) {
                        avd2 = (AnimatedVectorDrawable) drawable;
                        avd2.start();
                    }
                    checked= true;
                    count++;
//                    FirebaseApp.initializeApp(context);
//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Videos");
//                    Map<String, Object> updates = new HashMap<>();
//                    updates.put("videoLikes", count);
//                    databaseReference.child("video"+getAbsoluteAdapterPosition()).updateChildren(updates);
//                    liketext.setText(videoGSArrayList.get(getAbsoluteAdapterPosition()).getVideoLike());
//                    notifyDataSetChanged();
                    LikecheckBox.setButtonDrawable(icon_fav_filled);
                    liketext.setText(String.valueOf(count));

                }
            }));
        }

        private void soundOnOff(boolean checked) {
           try {
            if(checked){
                Animation animation1 = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.audiofadeout_anim);
                Audioon.setAnimation(animation1);
                Audiooff.setVisibility(View.GONE);
                mediaPlayer.setVolume(1.0f, 1.0f);
            }else {
                Audiooff.setVisibility(View.VISIBLE);
                Audioon.setVisibility(View.GONE);
                mediaPlayer.setVolume(0, 0);
            }
           }
           catch (Exception e){
               Log.d("exception",e.toString());
           }

        }


    }


}

package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.adapters.VideoAdapter;
import com.shiva.ananta.models.SharedPreferanceManager;
import com.shiva.ananta.models.VideoGS;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ReelsFragment extends Fragment {
    ViewPager2 viewPager2;
    ArrayList<VideoGS> arrayList;
    VideoAdapter videoAdapter;
    private int originalStatusBarColor;
    private int originalSystemUiVisibility;
    String videoimage= "";
    View loadingreelsbar;
    public ReelsFragment() {
    }
    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();
    ImageView  heartimg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            originalStatusBarColor = getActivity().getWindow().getStatusBarColor();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            originalSystemUiVisibility = requireActivity().getWindow().getDecorView().getSystemUiVisibility();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view  = inflater.inflate(R.layout.fragment_reels, container, false);
     View view1  = inflater.inflate(R.layout.video_component, container, false);
        originalStatusBarColor = getActivity().getWindow().getStatusBarColor();
        originalSystemUiVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        View loadingbar = view.findViewById(R.id.loadingbar);
        loadingbar.setVisibility(View.VISIBLE);
        changeStatusBarColorAndIcon(ContextCompat.getColor(requireContext(), R.color.black));

        ImageView imageView = view1.findViewById(R.id.image_view);
        arrayList = new ArrayList<>();
        viewPager2 = view.findViewById(R.id.view_pager2);


        ArrayList<VideoGS> videoGSArrayList = SharedPreferanceManager.getVideos(getContext());
        if(videoGSArrayList!=null){
            videoAdapter = new VideoAdapter(videoGSArrayList);
            viewPager2.setAdapter(videoAdapter);
            videoAdapter.notifyDataSetChanged();
            loadingbar.setVisibility(View.GONE);
//            for (VideoGS video : arrayList) {
//                ReelLoader.loadReel(getContext(), video.getVideoUrl(),imageView);
//            }
            Log.d("fdsv",videoGSArrayList.toString()+" getdata from shared");
        }else {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Videos");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            videoimage = dataSnapshot.child("videoimage").getValue().toString();
                            arrayList.add(new VideoGS(
//                            uriArrayList.get(i),
                                    dataSnapshot.child("videoUrl").getValue().toString(),
                                    dataSnapshot.child("videoTitle").getValue().toString(),
                                    dataSnapshot.child("videoDesc").getValue().toString(),
                                    dataSnapshot.child("uploaderProfile").getValue().toString(),
                                    dataSnapshot.child("videoLikes").getValue().toString(),
                                    dataSnapshot.child("videoComment").getValue().toString(),
                                    dataSnapshot.child("videoShare").getValue().toString(),
                                    dataSnapshot.child("videoUrl").getValue().toString()));
                        }
                    }catch (Exception e){

                    }
                    videoAdapter = new VideoAdapter(arrayList);
                    viewPager2.setAdapter(videoAdapter);
                    videoAdapter.notifyDataSetChanged();
                    loadingbar.setVisibility(View.GONE);
//                    for (VideoGS video : arrayList) {
//                        ReelLoader.loadReel(getContext(), video.getVideoUrl(),imageView);
//                    }

                    Log.d("fdsv",arrayList.toString()+" getdata from firebase");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return view;
    }

    private void changeStatusBarColorAndIcon(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = requireActivity().getWindow().getDecorView();
            int flags = decorView.getSystemUiVisibility();
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // Remove the flag to make the status bar icons dark
            decorView.setSystemUiVisibility(flags);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        changeStatusBarColorAndIcon(originalStatusBarColor);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(originalSystemUiVisibility);
    }

}
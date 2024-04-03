package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shiva.ananta.adapters.NotifcationMain_Adapter;

public class NotificationFragment extends Fragment {
    public NotificationFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    Context context;
    NotifcationMain_Adapter notifcation_main_adapter;

    ImageView Likedicon,notification;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_notification, container, false);


        recyclerView = view.findViewById(R.id.notification_Recycleview_Main);
        notifcation_main_adapter = new NotifcationMain_Adapter(context);
        recyclerView.setAdapter(notifcation_main_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));


        Likedicon = view.findViewById(R.id.Likedicon);
        Likedicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWishlistlikedActivity.class);
                startActivity(intent);
            }
        });
        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               NotificationFragment notificationFragment = new NotificationFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, notificationFragment).commit();
            }
        });

    return view;
    }
}
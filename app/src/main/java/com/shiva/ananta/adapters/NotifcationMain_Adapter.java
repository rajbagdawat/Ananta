package com.shiva.ananta.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shiva.ananta.R;

public class NotifcationMain_Adapter extends RecyclerView.Adapter<NotifcationMain_Adapter.ViewHolder> {


    Context context;

    public NotifcationMain_Adapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public NotifcationMain_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationmain_layout,parent,false);
        return new NotifcationMain_Adapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotifcationMain_Adapter.ViewHolder holder, int position) {

      holder.Notificationday.setText("Today");

      holder.notification_message_adapter = new NotificationMessage_Adapter(context);
      holder.recyclerView.setAdapter(holder.notification_message_adapter);
      holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Notificationday;
        RecyclerView recyclerView;
        NotificationMessage_Adapter notification_message_adapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Notificationday = itemView.findViewById(R.id.Whichday);
            recyclerView = itemView.findViewById(R.id.notification_Recycleview_message);
        }
    }
}

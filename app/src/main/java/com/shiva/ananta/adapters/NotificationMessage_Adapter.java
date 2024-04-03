package com.shiva.ananta.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shiva.ananta.R;

public class NotificationMessage_Adapter extends RecyclerView.Adapter<NotificationMessage_Adapter.ViewHolder> {
    Context mcontext;

    public NotificationMessage_Adapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public NotificationMessage_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_anantacardview, parent, false);
        return new NotificationMessage_Adapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationMessage_Adapter.ViewHolder holder, int position) {
        holder.Unread_dot.setImageResource(R.drawable.red);
        holder.Message.setText("UUkv,jkv i iio hvvh iv; ir;i hviwrhi hvihvo irvhih irhv;irvri dk vlsiuhvsidh vsh vs,v,fv svhsuuvsvu hsvuhslvusvjs");
        holder.datetime.setText("04:45 PM");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Unread_dot;
        TextView Message,datetime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unread_dot = itemView.findViewById(R.id.notification_unread_dot);
            Message = itemView.findViewById(R.id.notification_message);
            datetime = itemView.findViewById(R.id.notification_time_date);
        }
    }
}

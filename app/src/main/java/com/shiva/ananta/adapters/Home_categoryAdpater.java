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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home_categoryAdpater extends RecyclerView.Adapter<Home_categoryAdpater.ViewHolder> {

    Context mcontext;
    ArrayList<HomeCategory_GS> arrayList;
    HomeCategory_GS homeCategory_gs;
    public Home_categoryAdpater(Context mcontext, ArrayList<HomeCategory_GS> arrayList) {
        this.mcontext = mcontext;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.category_image, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        homeCategory_gs = arrayList.get(position);
        holder.HomePopularproduct_text.setText(homeCategory_gs.getTextView());
        Picasso.get().load(homeCategory_gs.imageView).into(holder.HomePopularproduct_Image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView HomePopularproduct_Image;
        TextView HomePopularproduct_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HomePopularproduct_Image = itemView.findViewById(R.id.home_category_image);
            HomePopularproduct_text = itemView.findViewById(R.id.home_category_text);

        }
    }
}

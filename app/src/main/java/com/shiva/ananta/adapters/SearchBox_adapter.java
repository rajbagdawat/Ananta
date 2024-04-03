package com.shiva.ananta.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shiva.ananta.R;

import java.util.ArrayList;

public class SearchBox_adapter extends RecyclerView.Adapter<SearchBox_adapter.ViewHolder> {
    Context mcontext;
  ArrayList<String> arrayListsearch;

    public SearchBox_adapter(Context mcontext, ArrayList<String> arrayListsearch) {
        this.mcontext = mcontext;
        this.arrayListsearch = arrayListsearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.recentproduct_demo, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       int pos = holder.getAbsoluteAdapterPosition();
        if(!arrayListsearch.isEmpty()){
            holder.Recenttext.setText(arrayListsearch.get(pos));
            try {
                holder.CloseimageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(arrayListsearch.size() >= pos){
                            arrayListsearch.remove(pos);
                            notifyItemRemoved(pos);
                          Log.d("dwdwad", pos+" array "+arrayListsearch.toString());

                            notifyItemRangeChanged(pos, arrayListsearch.size() - pos);
                        }
                    }
                });
            }
            catch (IndexOutOfBoundsException e){
                System.out.println(e);
            }
        }

    }

    @Override
    public int getItemCount() {
        return arrayListsearch.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView SearchList;
        TextView Recenttext;
        ImageView CloseimageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchList = itemView.findViewById(R.id.searchList);
            Recenttext = itemView.findViewById(R.id.recenttext);
            CloseimageView = itemView.findViewById(R.id.closeimageView5);
        }
    }
}

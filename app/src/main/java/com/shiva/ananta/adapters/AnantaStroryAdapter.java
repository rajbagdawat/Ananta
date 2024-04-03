package com.shiva.ananta.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shiva.ananta.R;
import com.shiva.ananta.StoryActivity;
import com.shiva.ananta.models.AnantaStoryGS;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnantaStroryAdapter extends RecyclerView.Adapter<AnantaStroryAdapter.ViewHolder> {

    ArrayList<AnantaStoryGS> anantaStoryGS;
    Context context;
    AnantaStoryGS anantaStoryGS1;


   public  AnantaStroryAdapter(Context context ,  ArrayList<AnantaStoryGS> anantaStoryGS){
       this.context = context;
        this.anantaStoryGS = anantaStoryGS;
    }
    @NonNull
    @Override
    public AnantaStroryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anantastory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnantaStroryAdapter.ViewHolder holder, int position) {
     anantaStoryGS1 = anantaStoryGS.get(position);
    holder.storyname.setText(anantaStoryGS1.getProfilename());


    }

    @Override
    public int getItemCount() {
        return anantaStoryGS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView storyprofile;
        ImageView storyactivering,storyinactivering;
        TextView storyname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storyprofile = itemView.findViewById(R.id.storyprofile);
            storyactivering = itemView.findViewById(R.id.storyactivering);
            storyinactivering = itemView.findViewById(R.id.storyinactivering);
            storyname = itemView.findViewById(R.id.storyname);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            storyactivering.setVisibility(View.GONE);
            storyinactivering.setVisibility(View.VISIBLE);
            Intent intent = new Intent(context, StoryActivity.class);
            intent.putExtra("name",anantaStoryGS.get(getAbsoluteAdapterPosition()).getProfilename());
            context.startActivity(intent);
        }
    }
}

package com.shiva.ananta.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shiva.ananta.LibraryBook_DetailsFragment;
import com.shiva.ananta.R;
import com.shiva.ananta.models.EBookcomponent_AdapterGS;
import com.shiva.ananta.models.SharedPreferanceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EBookcomponent_Adpater extends RecyclerView.Adapter<EBookcomponent_Adpater.ViewHolder> {

    Context mcontext;
    ArrayList<EBookcomponent_AdapterGS> eBookcomponent_adapterGS;
    public EBookcomponent_Adpater(Context mcontext,  ArrayList<EBookcomponent_AdapterGS> eBookcomponent_adapterGS) {
        this.mcontext = mcontext;
        this.eBookcomponent_adapterGS = eBookcomponent_adapterGS;
    }
    @NonNull
    @Override
    public EBookcomponent_Adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.ebook_component, parent, false);
        return new EBookcomponent_Adpater.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EBookcomponent_Adpater.ViewHolder holder, int position) {
      EBookcomponent_AdapterGS eBookcomponent_adapterGS1 = eBookcomponent_adapterGS.get(position);
      Picasso.get().load(eBookcomponent_adapterGS1.getEBook_Images()).into(holder.EBook_Images);
      holder.EBook_Name.setText(eBookcomponent_adapterGS1.getEBook_Name());
      holder.EBook_Name_Type.setText(eBookcomponent_adapterGS1.getEBook_Name_Type());
      holder.EBook_description.setText(eBookcomponent_adapterGS1.getEBook_description());

      ArrayList<String> getWishlist = SharedPreferanceManager.getWishlist(mcontext);

        ArrayList<String> list = SharedPreferanceManager.getWishlist(mcontext); // Load existing wishlist

        holder.EBook_likeicon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String description = eBookcomponent_adapterGS.get(position).getEBook_description();

                if (isChecked) {
                    if (!list.contains(description)) {
                        list.add(description);
                        Toast.makeText(mcontext, "Item added to the wishlist!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Do nothing if item is already in the wishlist
                    }
                } else {
                    if (list.contains(description)) {
                        list.remove(description);
                        Toast.makeText(mcontext, "Item removed from the wishlist!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Do nothing if item is not in the wishlist
                    }
                }

                SharedPreferanceManager.saveWishlist(mcontext, list); // Save updated wishlist
            }
        });


        if(getWishlist!=null){
            for (int i = 0; i < getWishlist.size(); i++) {
                if(getWishlist.get(i).equals(eBookcomponent_adapterGS.get(position).getEBook_description())) {
                    holder.EBook_likeicon.setButtonDrawable(R.drawable.icon_fav_filled);
                }
            }
        }

    }




    @Override
    public int getItemCount() {
        return eBookcomponent_adapterGS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView EBook_Name ,EBook_description,EBook_Name_Type;
        ImageView EBook_Images;
        CheckBox EBook_likeicon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            EBook_Images = itemView.findViewById(R.id.eBook_Images);
            EBook_Name = itemView.findViewById(R.id.eBook_Name);
            EBook_Name_Type = itemView.findViewById(R.id.eBook_Name_Type);
            EBook_description = itemView.findViewById(R.id.eBook_description);
            EBook_likeicon = itemView.findViewById(R.id.eBook_likeicon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                        LibraryBook_DetailsFragment fragment = new LibraryBook_DetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("bookid",eBookcomponent_adapterGS.get(position).getEBook_description());
                        fragment.setArguments(bundle);

                        FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
            }
        }
    }
}

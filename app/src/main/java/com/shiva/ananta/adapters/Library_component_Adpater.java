package com.shiva.ananta.adapters;

import static com.shiva.ananta.R.drawable.icon_fav_filled;
import static com.shiva.ananta.R.drawable.icon_fav_unfilled;

import android.content.Context;
import android.content.Intent;
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
import com.shiva.ananta.LikedFragment;
import com.shiva.ananta.NotificationFragment;
import com.shiva.ananta.R;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;
import com.squareup.picasso.Picasso;

import java.time.temporal.Temporal;
import java.util.ArrayList;


public class Library_component_Adpater extends RecyclerView.Adapter<Library_component_Adpater.ViewHolder> {
    ArrayList<Library_component_GS> library_component_gs;
    Library_component_GS library_component_gs1;

    Context context;

    public Library_component_Adpater(Context context, ArrayList<Library_component_GS> library_component_gs) {
        this.context = context;
        this.library_component_gs = library_component_gs;

    }

    @NonNull
    @Override
    public Library_component_Adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_component, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Library_component_Adpater.ViewHolder holder, int position) {
        library_component_gs1 = library_component_gs.get(position);
        Picasso.get().load(library_component_gs1.getLibraryBookImage()).into(holder.LibraryBookImage);
        holder.LibraryBookName.setText(library_component_gs1.getLibraryBookName());
        holder.LibraryAuthorName.setText(library_component_gs1.getLibraryAuthorName());
        holder.Library_BookDis.setText(library_component_gs1.getLibrary_BookDis());


        holder.Librarybook_ReadeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryBook_DetailsFragment fragment = new LibraryBook_DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("bookid", library_component_gs.get(position).getLibrary_Bookid());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        holder.EBook_likeicon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.clear();
                if (buttonView.isChecked()) {
                    if (!list.contains(library_component_gs.get(position).getLibrary_Bookid())) {
                        list.add(library_component_gs.get(position).getLibrary_Bookid());
                        Toast.makeText(context, "Item added to the wishlist!", Toast.LENGTH_SHORT).show();
                        SharedPreferanceManager.saveWishlist(context, list);
                    }else {
                        list.remove(library_component_gs.get(position).getLibrary_Bookid());
                        SharedPreferanceManager.saveWishlist(context, list);
                    }
                } else {
                    list.remove(library_component_gs.get(position).getLibrary_Bookid());
                    Toast.makeText(context, "Item removed to the wishlist!", Toast.LENGTH_SHORT).show();
                    SharedPreferanceManager.saveWishlist(context, list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return library_component_gs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView LibraryBookImage;
        TextView LibraryBookName;
        TextView LibraryAuthorName;
        TextView Library_BookDis;
        TextView Librarybook_ReadeNow;
        CheckBox EBook_likeicon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LibraryBookImage = itemView.findViewById(R.id.Librarybook_image);
            LibraryBookName = itemView.findViewById(R.id.Librarybook_name);
            LibraryAuthorName = itemView.findViewById(R.id.Librarybook_authorename);
            Library_BookDis = itemView.findViewById(R.id.Librarybook_description);
            EBook_likeicon = itemView.findViewById(R.id.eBook_likeicon);
            Librarybook_ReadeNow = itemView.findViewById(R.id.Librarybook_readeNow);
        }
    }
}

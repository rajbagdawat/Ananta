package com.shiva.ananta.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shiva.ananta.LibraryBook_DetailsFragment;
import com.shiva.ananta.LibraryFragment;
import com.shiva.ananta.MainActivity;
import com.shiva.ananta.MyWishlistlikedActivity;
import com.shiva.ananta.R;
import com.shiva.ananta.models.Library_component_GS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyWishList_Adpater extends RecyclerView.Adapter<MyWishList_Adpater.ViewHolder> {
    Context mcontext;
    Context context;
    ArrayList<Library_component_GS> library_component_gs;
    public MyWishList_Adpater(Context mcontext, ArrayList<Library_component_GS> library_component_gs) {
        this.mcontext = mcontext;
        this.library_component_gs = library_component_gs;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.libraryliked_component, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Library_component_GS library_component_gs1 = library_component_gs.get(position);
        Picasso.get().load(library_component_gs1.getLibraryBookImage()).into(holder.LibraryBookImage);
        holder.LibraryBookName.setText(library_component_gs1.getLibraryBookName());
        holder.LibraryAuthorName.setText(library_component_gs1.getLibraryAuthorName());
        holder.Library_BookDis.setText(library_component_gs1.getLibrary_BookDis());

        holder.wishlistLibrarybook_readeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("OPEN_USER_FRAGMENT", true);
                context.startActivity(intent);
//                switchToFragment();
            }
        });



    }
    private void switchToFragment() {
        @SuppressLint("UnsafeOptInUsageError")
        LibraryFragment fragment = new LibraryFragment();
        FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment); // Replace R.id.your_fragment_container with your actual container id
        fragmentTransaction.addToBackStack(null); // This line is optional, it adds the transaction to the back stack.
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        return library_component_gs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        ImageButton MyCartMenuicon;
        ImageView LibraryBookImage;
        TextView LibraryBookName;
        TextView LibraryAuthorName;
        TextView Library_BookDis;
        TextView wishlistLibrarybook_readeNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MyCartMenuicon = itemView.findViewById(R.id.mywishlist_settingicon);

            LibraryBookImage = itemView.findViewById(R.id.wishlistLibrarybook_image);
            LibraryBookName = itemView.findViewById(R.id.wishlistLibrarybook_name);
            LibraryAuthorName = itemView.findViewById(R.id.wishlistLibrarybook_authorename);
            Library_BookDis = itemView.findViewById(R.id.wishlistLibrarybook_description);
            wishlistLibrarybook_readeNow = itemView.findViewById(R.id.wishlistLibrarybook_readeNow);
            MyCartMenuicon.setOnClickListener(this);

//            wishlistLibrarybook_readeNow.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
//            OpenLibraryDetail(v);
        }

//        private void OpenLibraryDetail(View v) {
////            int position = getAdapterPosition();
////            if (position != RecyclerView.NO_POSITION) {
////                LibraryBook_DetailsFragment fragment = new LibraryBook_DetailsFragment();
////                Bundle bundle = new Bundle();
////                bundle.putString("key", "your_data");
////                fragment.setArguments(bundle);
//
//                Intent intent = new Intent(this, HomePageFragments.class);
//                intent.putExtra("OPEN_USER_FRAGMENT", true);
//                startActivity(intent);
//                finish();
//
////                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.framelayout, fragment);
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
//            }
//        }

        @SuppressLint("NewApi")
        private void showPopupMenu(View v) {
            context = v.getContext();

            Context settheme = new ContextThemeWrapper(context,R.style.Menutheme);
            PopupMenu popupMen = new PopupMenu(settheme,v);
            popupMen.inflate(R.menu.mywishlist);
            popupMen.setForceShowIcon(true);
            popupMen.setOnMenuItemClickListener(this);
            popupMen.show();
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_share :
                    Toast.makeText(MyCartMenuicon.getContext(), "share",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_delete:
                    Toast.makeText(MyCartMenuicon.getContext(), "deleted",Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }

        }
    }
}

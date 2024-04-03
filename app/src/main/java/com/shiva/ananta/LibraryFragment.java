package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.adapters.Library_component_Adpater;
import com.shiva.ananta.adapters.SearchBox_adapter;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class LibraryFragment extends Fragment {

    SearchView HomeSearchBar;
    ImageView Likedicon, notification;
    Context context;
    RecyclerView RecentSearchrecycle;
    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();

    SearchBox_adapter searchBox_adapter;
    public static ArrayList<String> arrayListsearch = new ArrayList<>();
     ArrayList<Library_component_GS> library_component_gs = new ArrayList<>();

    public LibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        RecentSearchrecycle = view.findViewById(R.id.searchList);
        HomeSearchBar = view.findViewById(R.id.homeSearchBar);
        context = getContext();
        View loadingbarnodata = view.findViewById(R.id.loadingbarnodata);
        View loadingbar = view.findViewById(R.id.loadingbar1);

        HomeSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!arrayListsearch.contains(query)) {
                    arrayListsearch.add(query);
                    searchBox_adapter = new SearchBox_adapter(context, arrayListsearch);
                    RecentSearchrecycle.setAdapter(searchBox_adapter);
                    RecentSearchrecycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
        HomeSearchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                RecentSearchrecycle.setVisibility(View.GONE);
                return false;
            }
        });


        ArrayList<Library_component_GS> listPopularBooks = SharedPreferanceManager.getLibraryBooks(context);
        if(listPopularBooks!=null){
            Library_component_Adpater library_component_adpater = new Library_component_Adpater(context, listPopularBooks);
            RecyclerView librarycomponent_recycle = view.findViewById(R.id.librarybooks_recycle);
            librarycomponent_recycle.setAdapter(library_component_adpater);
            librarycomponent_recycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            loadingbar.setVisibility(View.GONE);

        }else {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            database.getReference().child("Ananta").child("Librarybooks").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot != null) {

                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            String bookImage = dataSnapshot.child("bookimage").getValue(String.class);
                                            String bookName = dataSnapshot.child("bookname").getValue(String.class);
                                            String bookAuthorName = dataSnapshot.child("bookauhtorname").getValue(String.class);
                                            String bookMiniDescription = dataSnapshot.child("bookminidescription").getValue(String.class);
                                            String bookKeyword = dataSnapshot.child("bookkeyword").getValue(String.class);

                                            // Create a new Library_component_GS object and add it to your list
                                            library_component_gs.add(new Library_component_GS(
                                                    bookImage,
                                                    bookName,
                                                    bookAuthorName,
                                                    bookMiniDescription,
                                                    bookKeyword
                                            ));
                                            Library_component_Adpater library_component_adpater = new Library_component_Adpater(context, library_component_gs);
                                            RecyclerView librarycomponent_recycle = view.findViewById(R.id.librarybooks_recycle);
                                            librarycomponent_recycle.setAdapter(library_component_adpater);
                                            librarycomponent_recycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                                            loadingbar.setVisibility(View.GONE);

                                        }

                                    } else {
                                        loadingbarnodata.setVisibility(View.VISIBLE);
                                        loadingbar.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    });
                }
            };

            timer.schedule(timerTask, 1000);
//        loadingbar.setVisibility(View.GONE);
        }
        return view;
    }

    private void search(String query) {
        if (query.isEmpty()) {
            RecentSearchrecycle.setVisibility(View.GONE);
        } else {
            RecentSearchrecycle.setVisibility(View.VISIBLE);
        }
    }
}
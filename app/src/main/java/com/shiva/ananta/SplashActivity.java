package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.adapters.HomeCategory_GS;
import com.shiva.ananta.models.EBookcomponent_AdapterGS;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;

import java.util.ArrayList;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginPage.class);
            startActivity(intent);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ArrayList<HomeCategory_GS> arrayListcategory = new ArrayList<>();
        database.getReference().child("Ananta").child("Bookscategory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot != null) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        String bookName = dataSnapshot.child("bookname").getValue(String.class);
                        String bookImage = dataSnapshot.child("bookimage").getValue(String.class);
                        arrayListcategory.add(new HomeCategory_GS(bookName, bookImage));

                    }
                    SharedPreferanceManager.saveHomeCategory(context, arrayListcategory);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        database.getReference().child("Ananta").child("PopularBooks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot != null) {
                    ArrayList<EBookcomponent_AdapterGS> list = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        list.add(new EBookcomponent_AdapterGS(dataSnapshot.child("bookname").getValue(String.class),
                                dataSnapshot.child("bookauhtorname").getValue(String.class),
                                dataSnapshot.child("bookminidescription").getValue(String.class),
                                dataSnapshot.child("bookimage").getValue(String.class),
                                dataSnapshot.child("bookkeyword").getValue(String.class)));
                    }
                    SharedPreferanceManager.savePopularBooks(context, list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ArrayList<Library_component_GS> library_component_gs = new ArrayList<>();
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

                        library_component_gs.add(new Library_component_GS(
                                bookImage,
                                bookName,
                                bookAuthorName,
                                bookMiniDescription,
                                bookKeyword
                        ));
                        SharedPreferanceManager.saveLibraryBooks(context, library_component_gs);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        try {

//            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Ananta").child("LibraryBooks");
//            databaseReference2.addValueEventListener(new ValueEventListener() {
//                @SuppressLint("NotifyDataSetChanged")
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    ArrayList<Library_component_GS> listLibraryBooks = new ArrayList<>();
////                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////                        listLibraryBooks.add(new Library_component_GS(dataSnapshot.child("bookimage").getValue().toString(),
////                                dataSnapshot.child("bookname").getValue().toString(),
////                                dataSnapshot.child("bookauhtorname").getValue().toString(),
////                                dataSnapshot.child("bookdescription").getValue().toString(),
////                                dataSnapshot.child("bookkeyword").getValue().toString()));
////                    }
////                    SharedPreferanceManager.saveLibraryBooks(context, listLibraryBooks);
//
//                    ArrayList<LibrarySharedPreferancedata> listLibraryBooks = new ArrayList<>();
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        listLibraryBooks.add(new LibrarySharedPreferancedata(
//                                dataSnapshot.child("bookimage").getValue().toString(),
//                                dataSnapshot.child("bookname").getValue().toString(),
//                                dataSnapshot.child("bookauhtorname").getValue().toString(),
//                                dataSnapshot.child("bookdescription").getValue().toString(),
//                                dataSnapshot.child("bookId").getValue().toString(),
//                                dataSnapshot.child("bookPagecount").getValue().toString(),
//                                dataSnapshot.child("bookURL").getValue().toString(),
//                                dataSnapshot.child("bookVersion").getValue().toString(),
//                                dataSnapshot.child("bookkeyword").getValue().toString(),
//                                dataSnapshot.child("bookminidescription").getValue().toString(),
//                                dataSnapshot.child("bookrating").getValue().toString()
//                        ));
//                    }
//                    SharedPreferanceManager.saveLibraryBooksdata(context, listLibraryBooks);
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }catch (Exception e){
//
//        }


        new Handler().postDelayed(new Runnable() {
            @SuppressLint("UnsafeOptInUsageError")
            @Override
            public void run() {
                SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);
                Intent intent;
                if (check) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginPage.class);
                }
                startActivities(new Intent[]{intent});
                finish();
            }
        }, 1000);
    }
}
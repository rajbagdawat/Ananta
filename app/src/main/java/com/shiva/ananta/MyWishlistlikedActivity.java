package com.shiva.ananta;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.adapters.MyWishList_Adpater;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;

import java.util.ArrayList;

public class MyWishlistlikedActivity extends AppCompatActivity {

    static ArrayList<Library_component_GS> library_component_gs = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlistliked);
        context = getApplicationContext();
        View loadingbar = findViewById(R.id.loadingbar);
        View loadingbarnodata = findViewById(R.id.loadingbarnodata);
//        library_component_gs.add(new Library_component_GS("https://tse2.mm.bing.net/th?id=OIP.BB9XLfjjgmGMi0L_avw6FAHaKi&pid=Api&P=0&h=180","रामायण","रचयिता महर्षि वाल्मीकि जी","इस महाकाव्य के ऐतिहासिक विकास और संरचनागत परतों को जानने के लिए कई प्रयास किए गए हैं।","jhb"));
//        library_component_gs.add(new Library_component_GS("https://tse4.mm.bing.net/th?id=OIP.EMLB3kLl9r4YzFylIVGs2AHaJ3&pid=Api&P=0&h=180","महाभारत","रचयिता महर्षि वेदव्यास जी","यह काव्यग्रंथ भारत का अनुपम धार्मिक, पौराणिक, ऐतिहासिक और दार्शनिक ग्रंथ हैं।","cs"));
//        library_component_gs.add(new Library_component_GS("https://m.media-amazon.com/images/I/613WYGLXNEL._SY466_.jpg","श्रीमद्भगवद्गीता","रचयिता महर्षि वेदव्यास जी","यह महाभारत के भीष्मपर्व का अंग है। गीता में 18 अध्याय और 700 श्लोक हैं।","scs"));
//        library_component_gs.add(new Library_component_GS("https://tse2.mm.bing.net/th?id=OIP.c20IxMYud5jP0DI-mKE8dwHaIq&pid=Api&P=0&h=180","शिव पुराण","रचयिता महर्षि वेदव्यास जी","शिव पुराण में भगवान शिव के विविध रूपों, अवतारों, ज्योतिर्लिंगों, भक्तों और भक्ति का विशद् वर्णन किया गया है।"));
//        library_component_gs.add(new Library_component_GS("https://tse1.mm.bing.net/th?id=OIP.asfrahJ-ECUDjbFxWOwqLgHaHa&pid=Api&P=0&h=180","विज्ञान भैरव तंत्र","रचयिता नन्द लाल दशोरा जी ","शिव के उत्तर में केवल विधियाँ हैं। सबसे पुरानी, सबसे प्राचीन विधियाँ।"));


        ArrayList<String> wishlistSet = SharedPreferanceManager.getWishlist(context);
        library_component_gs.clear();
        if (wishlistSet != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            loadingbar.setVisibility(View.VISIBLE);
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

                            if (wishlistSet.contains(bookKeyword)) {
                                Log.d("fcdsv","wishlistSet :" +wishlistSet.toString());
                                if (!library_component_gs.contains(bookKeyword)) {
                                    library_component_gs.add(new Library_component_GS(
                                            bookImage,
                                            bookName,
                                            bookAuthorName,
                                            bookMiniDescription,
                                            bookKeyword
                                    ));
                                }
                                for (int i = 0; i < library_component_gs.size(); i++) {

                                    Log.d("fcdsv","library_component_gs :"+library_component_gs.get(i).getLibrary_Bookid().toString());
                                }
                            }

                            RecyclerView librarycomponent_recycle = findViewById(R.id.librarybooksliked_recycle);
                            MyWishList_Adpater myWishList_adpater = new MyWishList_Adpater(context, library_component_gs);
                            librarycomponent_recycle.setAdapter(myWishList_adpater);
                            librarycomponent_recycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                            loadingbar.setVisibility(View.GONE);

                        }

                    } else {
                        loadingbar.setVisibility(View.GONE);
                        loadingbarnodata.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            if(wishlistSet.size()==0){
                loadingbarnodata.setVisibility(View.VISIBLE);
            }

        } else {
            loadingbarnodata.setVisibility(View.VISIBLE);
        }


    }

    public void back(View view) {
        onBackPressed();
    }
}
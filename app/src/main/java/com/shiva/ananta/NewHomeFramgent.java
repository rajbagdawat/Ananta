package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.adapters.EBookcomponent_Adpater;
import com.shiva.ananta.adapters.HomeCategory_GS;
import com.shiva.ananta.adapters.Home_categoryAdpater;
import com.shiva.ananta.adapters.Library_component_Adpater;
import com.shiva.ananta.adapters.VideoAdapter;
import com.shiva.ananta.models.EBookcomponent_AdapterGS;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;
import com.shiva.ananta.models.VideoGS;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class NewHomeFramgent extends Fragment {
    Context context;
    ImageView Likedicon,notification,pdfdemo;
    public NewHomeFramgent() {
        // Required empty public constructor
    }
    private Timer timer;
    String videoimage= "";
    private TimerTask timerTask;
    private final Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_new_home_framgent, container, false);
        View loadingbar1 = view.findViewById(R.id.loadingbar1);
        View loadingbar2 = view.findViewById(R.id.loadingbar2);


        @SuppressLint("MissingInflatedId") ImageSlider imageSlider = view.findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("http://getinfolist.com/wp-content/uploads/2015/01/maxresdefault-4-1024x576.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse2.mm.bing.net/th?id=OIP.7TmprDt1AXvkDguJ7XkpUAHaDa&pid=Api&P=0&h=180", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse1.mm.bing.net/th?id=OIP.O_FfUsJixAbLEly8PtBHdwHaDJ&pid=Api&P=0&h=180", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse4.mm.bing.net/th?id=OIP.BueTEFRblPyDzmbT12aIwgHaDt&pid=Api&P=0&h=180 ", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://kids.kiddle.co/images/e/e5/1500-1200_BCE_Rigveda%2C_manuscript_page_sample_ii%2C_Sanskrit%2C_Devanagari.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse4.mm.bing.net/th?id=OIP.PEc4VIlKII8JbPoo4iEC6gHaFH&pid=Api&P=0&h=180 ", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        context = getActivity();

        ArrayList<HomeCategory_GS> arrayListcategory = SharedPreferanceManager.getHomeCategory(context);
        if(arrayListcategory!=null){
            Home_categoryAdpater home_categoryAdpater = new Home_categoryAdpater(getContext(), arrayListcategory);
            RecyclerView Homecategory_recycle = view.findViewById(R.id.newcategory);
            Homecategory_recycle.setAdapter(home_categoryAdpater);
            Homecategory_recycle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        }else {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingbar1.setVisibility(View.VISIBLE);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            ArrayList<EBookcomponent_AdapterGS> list = new ArrayList<>();
                            database.getReference().child("Ananta").child("Bookscategory").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot != null) {

                                        ArrayList<HomeCategory_GS> arrayListcategory = new ArrayList<>();
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            String bookName = dataSnapshot.child("bookname").getValue(String.class);
                                            String bookImage = dataSnapshot.child("bookimage").getValue(String.class);
                                            String bookAuthorName = dataSnapshot.child("bookauhtorname").getValue(String.class);
                                            String bookMiniDescription = dataSnapshot.child("bookminidescription").getValue(String.class);
                                            String bookKeyword = dataSnapshot.child("bookkeyword").getValue(String.class);


                                            arrayListcategory.add(new HomeCategory_GS(bookName, bookImage));
                                            Home_categoryAdpater home_categoryAdpater = new Home_categoryAdpater(getContext(), arrayListcategory);
                                            RecyclerView Homecategory_recycle = view.findViewById(R.id.newcategory);
                                            Homecategory_recycle.setAdapter(home_categoryAdpater);
                                            Homecategory_recycle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                                        }
                                        loadingbar1.setVisibility(View.VISIBLE);

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

            timer.schedule(timerTask, 0000);
            loadingbar1.setVisibility(View.VISIBLE);
        }


        ArrayList<EBookcomponent_AdapterGS> listPopularBooks = SharedPreferanceManager.getPopularBooks(context);
        if(listPopularBooks!=null){
            EBookcomponent_Adpater eBookcomponent_adpaterlistPopularBooks = new EBookcomponent_Adpater(context,listPopularBooks);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            RecyclerView Homepopularbook_recycle = view.findViewById(R.id.homepopularbook_recycle);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
            Homepopularbook_recycle.setAdapter(eBookcomponent_adpaterlistPopularBooks);
            Homepopularbook_recycle.setLayoutManager(gridLayoutManager);
        }else {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingbar2.setVisibility(View.VISIBLE);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            ArrayList<EBookcomponent_AdapterGS> listPopularBooks = new ArrayList<>();
                            database.getReference().child("Ananta").child("Librarybooks").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot != null) {

                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            String bookName = dataSnapshot.child("bookname").getValue(String.class);
                                            String bookImage = dataSnapshot.child("bookimage").getValue(String.class);
                                            String bookAuthorName = dataSnapshot.child("bookauhtorname").getValue(String.class);
                                            String bookMiniDescription = dataSnapshot.child("bookminidescription").getValue(String.class);
                                            String bookKeyword = dataSnapshot.child("bookkeyword").getValue(String.class);

                                            // Create a new Library_component_GS object and add it to your list
                                            listPopularBooks.add(new EBookcomponent_AdapterGS(
                                                    bookName,
                                                    bookAuthorName,
                                                    bookMiniDescription,
                                                    bookImage,
                                                    bookKeyword
                                            ));
                                            EBookcomponent_Adpater eBookcomponent_adpaterlistPopularBooks = new EBookcomponent_Adpater(context, listPopularBooks);
                                            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                                            RecyclerView Homepopularbook_recycle = view.findViewById(R.id.homepopularbook_recycle);
                                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                                            Homepopularbook_recycle.setAdapter(eBookcomponent_adpaterlistPopularBooks);
                                            Homepopularbook_recycle.setLayoutManager(gridLayoutManager);
                                            loadingbar2.setVisibility(View.GONE);

                                        }

                                    } else {
                                        loadingbar2.setVisibility(View.GONE);
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

            timer.schedule(timerTask, 0000);
            loadingbar2.setVisibility(View.VISIBLE);

        }


        ArrayList<VideoGS> arrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Videos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        videoimage = dataSnapshot.child("videoimage").getValue().toString();
                        arrayList.add(new VideoGS(
//                            uriArrayList.get(i),
                                dataSnapshot.child("videoUrl").getValue().toString(),
                                dataSnapshot.child("videoTitle").getValue().toString(),
                                dataSnapshot.child("videoDesc").getValue().toString(),
                                dataSnapshot.child("uploaderProfile").getValue().toString(),
                                dataSnapshot.child("videoLikes").getValue().toString(),
                                dataSnapshot.child("videoComment").getValue().toString(),
                                dataSnapshot.child("videoShare").getValue().toString(),
                                dataSnapshot.child("videoUrl").getValue().toString()));
                    }
                }catch (Exception e){

                }
                SharedPreferanceManager.saveVideos(context,arrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment notificationFragment = new NotificationFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, notificationFragment).commit();
            }
        });


        Likedicon = view.findViewById(R.id.Likedicon);
        Likedicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWishlistlikedActivity.class);
                startActivity(intent);
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
            auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
//                        Toast.makeText(getContext(), user.getUid().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        FirebaseApp.initializeApp(context);

//        ArrayList<String> bookkeyword = new ArrayList<>();
//        ArrayList<String> bookid = new ArrayList<>();
//        bookkeyword.add("Ramayan");
//        bookkeyword.add("Mahabharat");
//        bookkeyword.add("Bhagwat geeta");
//        bookkeyword.add("Shiv Puran");
//        bookkeyword.add("Vigyan Bhairav Tantra");
//        bookkeyword.add("Sunderkand");
//        bookid.add("LibraryBooksbook1");
//        bookid.add("LibraryBooksbook2");
//        bookid.add("LibraryBooksbook3");
//        bookid.add("LibraryBooksbook4");
//        bookid.add("LibraryBooksbook5");
//        bookid.add("LibraryBooksbook6");


//        ArrayList<String> bookurl = new ArrayList<>();
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2F%E0%A4%B5%E0%A4%BE%E0%A4%B2%E0%A5%8D%E0%A4%AE%E0%A5%80%E0%A4%95%E0%A4%BF-%E0%A4%B0%E0%A4%BE%E0%A4%AE%E0%A4%BE%E0%A4%AF%E0%A4%A3-srimad-valmiki-ramayana.pdf?alt=media&token=c5e99c96-358c-4a47-b199-9ccec9886cee");
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FMahabharata%20Volume%201.pdf?alt=media&token=ed2d3198-761e-4b3e-bd60-80628302e5a7&_gl=1*qu22ws*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTM0MjQxOC40NC4xLjE2OTkzNDQzNjguMi4wLjA.");
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fbhagwat-geeta.pdf?alt=media&token=82964508-47de-4b7f-a64b-96f8d49b43ee");
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fshivpuran.pdf?alt=media&token=17e7a789-4542-4369-ad38-b4576af65b97");
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FVigyanBhairavTantra.pdf?alt=media&token=136f0bab-006e-4106-89eb-9a95d42c3f3b");
//        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fsundar%20kand.pdf?alt=media&token=ad91466f-d5f5-4ddf-9844-e10021f3c7fb");

//        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Librarybooks");
//        for (int i = 0, s =1; i < eBookcomponent_adapterGS1.size(); i++,s++) {
//            for (int j = 0; j < 4 ; j++) {
//
//                databaseReference1.child(""+s)
//                        .child("bookname").setValue(eBookcomponent_adapterGS1.get(i).getEBook_Name());
//                databaseReference1.child("" + s)
//                        .child("bookauhtorname").setValue(eBookcomponent_adapterGS1.get(i).getEBook_Name_Type());
//                databaseReference1.child("" + s)
//                        .child("bookminidescription").setValue(eBookcomponent_adapterGS1.get(i).getEBook_minidescription());
//                databaseReference1.child("" + s)
//                        .child("bookimage").setValue(eBookcomponent_adapterGS1.get(i).getEBook_Images());
//                databaseReference1.child("" + s)
//                        .child("bookrating").setValue("4.5");
//                databaseReference1.child("" + s)
//                        .child("bookkeyword").setValue(bookkeyword.get(i));
//                databaseReference1.child("" + s)
//                        .child("bookVersion").setValue("Hindi Version");
//                databaseReference1.child("" + s)
//                        .child("bookPagecount").setValue("60");
//                databaseReference1.child("" + s)
//                        .child("bookdescription").setValue(eBookcomponent_adapterGS1.get(i).getEBook_description());
//                databaseReference1.child("" + s)
//                        .child("bookurl").setValue(bookurl.get(i));
//            }
//        }


//        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Bookscategory");
//        for (int i = 0, s =1; i < eBookcomponent_adapterGS.size(); i++,s++) {
//            for (int j = 0; j < 4 ; j++) {
//
//                databaseReference1.child("book" + s)
//                        .child("bookname").setValue(eBookcomponent_adapterGS.get(i).getEBook_Name());
//                databaseReference1.child("book" + s)
//                        .child("booksubcategory").setValue(eBookcomponent_adapterGS.get(i).getEBook_Name_Type());
//                databaseReference1.child("book" + s)
//                        .child("bookminidescription").setValue(eBookcomponent_adapterGS.get(i).getEBook_minidescription());
//                databaseReference1.child("book" + s)
//                        .child("bookimage").setValue(eBookcomponent_adapterGS.get(i).getEBook_Images());
//                databaseReference1.child("book" + s)
//                        .child("bookrating").setValue("4.5");
//                databaseReference1.child("book" + s)
//                        .child("bookkeyword").setValue(bookkeyword.get(i));
//                databaseReference1.child("book" + s)
//                        .child("bookVersion").setValue("Hindi Version");
//                databaseReference1.child("book" + s)
//                        .child("bookPagecount").setValue("560");
//                databaseReference1.child("book" + s)
//                        .child("bookdescription").setValue(eBookcomponent_adapterGS.get(i).getEBook_description());
//            }
//        }

        return view;
    }
}
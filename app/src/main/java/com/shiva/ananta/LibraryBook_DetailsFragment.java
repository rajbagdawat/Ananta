package com.shiva.ananta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shiva.ananta.models.Library_component_GS;
import com.shiva.ananta.models.SharedPreferanceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LibraryBook_DetailsFragment extends Fragment {

    public LibraryBook_DetailsFragment() {
        // Required empty public constructor
    }

    ImageView Likedicon, notification, Librarybookdetail_image;
    static String bookname;
    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();
    static String bookId;
    TextView Librarybookdetail_descriptionbottom, Librarybookdetail_name, Librarybookdetail_authorename, Librarybookdetail_description, Librarybookdetail_pagecount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_book__details, container, false);

        View loadingbar = view.findViewById(R.id.loadingbar);
        loadingbar.setVisibility(View.VISIBLE);
        LinearLayout layout1 = view.findViewById(R.id.layout1);
        LinearLayout layout2 = view.findViewById(R.id.layout2);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        ArrayList<Library_component_GS> library_component_gs = SharedPreferanceManager.getLibraryBooks(getContext());
        TextView Librarybook_readeNow = view.findViewById(R.id.Librarybook_readeNow);
        Librarybookdetail_descriptionbottom = view.findViewById(R.id.Librarybookdetail_descriptionbottom);
        Librarybookdetail_image = view.findViewById(R.id.Librarybookdetail_image);
        Librarybookdetail_name = view.findViewById(R.id.Librarybookdetail_name);
        Librarybookdetail_authorename = view.findViewById(R.id.Librarybookdetail_authorename);
        Librarybookdetail_description = view.findViewById(R.id.Librarybookdetail_description);
        Librarybookdetail_pagecount = view.findViewById(R.id.Librarybookdetail_pagecount);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String bookId = bundle.getString("bookid");
            if (bookId != null) {
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
                                                String bookdescription = dataSnapshot.child("bookdescription").getValue(String.class);
                                                String bookminidescription = dataSnapshot.child("bookminidescription").getValue(String.class);
                                                String bookKeyword = dataSnapshot.child("bookkeyword").getValue(String.class);

                                                if (bookKeyword.equals(bookId)) {
                                                    Librarybookdetail_descriptionbottom.setText(bookdescription);
                                                    Picasso.get().load(bookImage).placeholder(R.drawable.vedas).into(Librarybookdetail_image);
                                                    Librarybookdetail_name.setText(bookName);
                                                    Librarybookdetail_description.setText(bookminidescription);
                                                    Librarybookdetail_authorename.setText(bookAuthorName);
                                                    bookname = bookKeyword;
                                                }else {
                                                    Log.d("nhv","mjhv");
                                                }

                                            }
                                            layout1.setVisibility(View.VISIBLE);
                                            layout2.setVisibility(View.VISIBLE);
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
                }

                ;

                timer.schedule(timerTask, 1000);

            }
        }

        Librarybook_readeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReadPdfActivity.class);
                intent.putExtra("bookKeyword", bookname);
                startActivity(intent);
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

        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment notificationFragment = new NotificationFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, notificationFragment).commit();
            }
        });


        return view;
    }
}
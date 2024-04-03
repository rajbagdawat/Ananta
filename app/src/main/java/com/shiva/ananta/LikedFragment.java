package com.shiva.ananta;

import static com.shiva.ananta.LibraryFragment.arrayListsearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shiva.ananta.adapters.Library_component_Adpater;
import com.shiva.ananta.models.Library_component_GS;

import java.util.ArrayList;

public class LikedFragment extends Fragment {

    public static   ArrayList<Library_component_GS> library_component_gs = new ArrayList<>();
    Context context;
    ImageView Likedicon,notification;
    public LikedFragment(Context context , ArrayList<Library_component_GS> library_component_gs) {
        // Required empty public constructor
        this.context = context;
        this.library_component_gs = library_component_gs;
    }
    public LikedFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_liked, container, false);


//        Library_component_GS library_component_gs1 = new Library_component_GS(getContext(),arrayListsearch);

//        library_component_gs.add(new Library_component_GS("https://tse2.mm.bing.net/th?id=OIP.BB9XLfjjgmGMi0L_avw6FAHaKi&pid=Api&P=0&h=180","रामायण","रचयिता महर्षि वाल्मीकि जी","इस महाकाव्य के ऐतिहासिक विकास और संरचनागत परतों को जानने के लिए कई प्रयास किए गए हैं।"));
//        library_component_gs.add(new Library_component_GS("https://tse4.mm.bing.net/th?id=OIP.EMLB3kLl9r4YzFylIVGs2AHaJ3&pid=Api&P=0&h=180","महाभारत","रचयिता महर्षि वेदव्यास जी","यह काव्यग्रंथ भारत का अनुपम धार्मिक, पौराणिक, ऐतिहासिक और दार्शनिक ग्रंथ हैं।"
//        ));
//        library_component_gs.add(new Library_component_GS("https://m.media-amazon.com/images/I/613WYGLXNEL._SY466_.jpg","श्रीमद्भगवद्गीता","रचयिता महर्षि वेदव्यास जी","यह महाभारत के भीष्मपर्व का अंग है। गीता में 18 अध्याय और 700 श्लोक हैं।"));
//        library_component_gs.add(new Library_component_GS("https://tse2.mm.bing.net/th?id=OIP.c20IxMYud5jP0DI-mKE8dwHaIq&pid=Api&P=0&h=180","शिव पुराण","रचयिता महर्षि वेदव्यास जी","शिव पुराण में भगवान शिव के विविध रूपों, अवतारों, ज्योतिर्लिंगों, भक्तों और भक्ति का विशद् वर्णन किया गया है।"));
//        library_component_gs.add(new Library_component_GS("https://tse1.mm.bing.net/th?id=OIP.asfrahJ-ECUDjbFxWOwqLgHaHa&pid=Api&P=0&h=180","विज्ञान भैरव तंत्र","रचयिता नन्द लाल दशोरा जी ","शिव के उत्तर में केवल विधियाँ हैं। सबसे पुरानी, सबसे प्राचीन विधियाँ।"));
//
//        Library_component_Adpater library_component_adpater = new Library_component_Adpater(context,library_component_gs);
//
//     RecyclerView librarycomponent_recycle = view.findViewById(R.id.librarybooksliked_recycle);
//        librarycomponent_recycle.setAdapter(library_component_adpater);
//        librarycomponent_recycle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

//        Likedicon = view.findViewById(R.id.Likedicon);
//        Likedicon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LikedFragment likedFragment = new LikedFragment();
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.framelayout, likedFragment).commit();
//            }
//        });
//
//        notification = view.findViewById(R.id.notification);
//        notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NotificationFragment notificationFragment = new NotificationFragment();
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.framelayout, notificationFragment).commit();
//            }
//        });


        return view;
    }

}
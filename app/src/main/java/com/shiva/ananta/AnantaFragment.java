package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.shiva.ananta.adapters.AnantaPostAdapter;
import com.shiva.ananta.adapters.AnantaStroryAdapter;
import com.shiva.ananta.adapters.ImageSliderAdapter;
import com.shiva.ananta.models.AnantaPostGS;
import com.shiva.ananta.models.AnantaStoryGS;

import java.util.ArrayList;


public class AnantaFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
//    private int[] imageResources = new int[]{R.drawable.vedas, R.drawable.ramayann, R.drawable.ramayann};

     public AnantaFragment() {
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
      View view = inflater.inflate(R.layout.fragment_ananta, container, false);
      View view1 = inflater.inflate(R.layout.anantacomponent, container, false);



        ArrayList<String> imageResources = new ArrayList<>();

        imageResources.add("http://getinfolist.com/wp-content/uploads/2015/01/maxresdefault-4-1024x576.jpg");
        imageResources.add("https://tse2.mm.bing.net/th?id=OIP.7TmprDt1AXvkDguJ7XkpUAHaDa&pid=Api&P=0&h=180");
        imageResources.add("https://tse1.mm.bing.net/th?id=OIP.O_FfUsJixAbLEly8PtBHdwHaDJ&pid=Api&P=0&h=180");
//        imageResources.add("https://tse4.mm.bing.net/th?id=OIP.BueTEFRblPyDzmbT12aIwgHaDt&pid=Api&P=0&h=180 ");
//        imageResources.add("https://kids.kiddle.co/images/e/e5/1500-1200_BCE_Rigveda%2C_manuscript_page_sample_ii%2C_Sanskrit%2C_Devanagari.jpg");
//        imageResources.add("https://tse4.mm.bing.net/th?id=OIP.PEc4VIlKII8JbPoo4iEC6gHaFH&pid=Api&P=0&h=180 ");


        ArrayList<AnantaStoryGS> anantaStoryGS = new ArrayList<>();
        anantaStoryGS.add(new AnantaStoryGS("","Ananta"));
        anantaStoryGS.add(new AnantaStoryGS("","Shiv"));
        anantaStoryGS.add(new AnantaStoryGS("","Vishnu"));
        anantaStoryGS.add(new AnantaStoryGS("","Krishna"));
        anantaStoryGS.add(new AnantaStoryGS("","Radhe"));
        anantaStoryGS.add(new AnantaStoryGS("","Ganesha"));
        anantaStoryGS.add(new AnantaStoryGS("","Hanuman"));
        anantaStoryGS.add(new AnantaStoryGS("","Maa Lakshmi"));
        anantaStoryGS.add(new AnantaStoryGS("","Maa Kali"));

      AnantaStroryAdapter anantaStroryAdapter = new AnantaStroryAdapter(getContext(),anantaStoryGS);
      @SuppressLint({"MissingInflatedId", "LocalSuppress"})
      RecyclerView recyclerView = view.findViewById(R.id.anantastory_recycle);
      recyclerView.setAdapter(anantaStroryAdapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));




      ArrayList<AnantaPostGS> anantaPostGS = new ArrayList<>();
      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
      anantaPostGS.add(new AnantaPostGS("ukhjn,","Ananta","त" +
              "्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("uukjb","Vishnu","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("ukhjn,","Shiv","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("uukjb","Vishnu","त्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));
//      anantaPostGS.add(new AnantaPostGS("mgnb ","Ananta","्रिदेव में से सबसे शक्तिशाली कौन है ? ",imageResources));

      AnantaPostAdapter anantaPostAdapter = new AnantaPostAdapter(getContext(),anantaPostGS);
      RecyclerView recyclerView1 = view.findViewById(R.id.anantapost_recycle);
      recyclerView1.setAdapter(anantaPostAdapter);
      recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));




        return view;
    }

}
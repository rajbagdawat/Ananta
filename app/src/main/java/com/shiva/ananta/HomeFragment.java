package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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
import com.shiva.ananta.adapters.EBookcomponent_Adpater;
import com.shiva.ananta.models.EBookcomponent_AdapterGS;
import com.shiva.ananta.models.SharedPreferanceManager;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Context context;
    ImageView Likedicon,notification,pdfdemo;
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_home, container, false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        pdfdemo = view.findViewById(R.id.pdfdemo);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("http://getinfolist.com/wp-content/uploads/2015/01/maxresdefault-4-1024x576.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse2.mm.bing.net/th?id=OIP.7TmprDt1AXvkDguJ7XkpUAHaDa&pid=Api&P=0&h=180", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse1.mm.bing.net/th?id=OIP.O_FfUsJixAbLEly8PtBHdwHaDJ&pid=Api&P=0&h=180", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse4.mm.bing.net/th?id=OIP.BueTEFRblPyDzmbT12aIwgHaDt&pid=Api&P=0&h=180 ", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://kids.kiddle.co/images/e/e5/1500-1200_BCE_Rigveda%2C_manuscript_page_sample_ii%2C_Sanskrit%2C_Devanagari.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://tse4.mm.bing.net/th?id=OIP.PEc4VIlKII8JbPoo4iEC6gHaFH&pid=Api&P=0&h=180 ", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

//        ArrayList<EBookcomponent_AdapterGS> eBookcomponent_adapterGS = new ArrayList<>();
//        eBookcomponent_adapterGS.add(new EBookcomponent_AdapterGS("वेद","वेद के चार भाग है।","वेद प्राचीन भारत वर्ष के पवित्र साहित्य हैं जो धरती के प्राचीनतम और आधारभूत धर्मग्रन्थ भी हैं ।",
//                "https://tse4.mm.bing.net/th?id=OIP.oszj9ayISUqcwwEQQeZ7oAAAAA&pid=Api&P=0&h=180","वेद, संस्कृत भाषा में लिखित प्राचीन भारतीय धार्मिक साहित्य है जिसे हिन्दू धर्म की प्रमुख प्रामाणिक ग्रंथों में से एक माना जाता है। वेद भारतीय संस्कृति की प्राचीनतम और प्रमुख धार्मिक ग्रंथों में से चार हैं, जिन्हें चार वेदों के रूप में जाना जाता है। इन चार वेदों का नाम है: रिग्वेद, यजुर्वेद, सामवेद, और अथर्ववेद।\n" +
//                "वेदों में विभिन्न विषयों पर ज्ञान, उपासना, और यज्ञों की विधियां वर्णित हैं। वेदों में भगवान की महिमा, प्रकृति के रहस्य, और मानव जीवन की महत्वपूर्ण बातें दी गई हैं। यह भारतीय संस्कृति, धर्म, और दार्शनिक विचारधारा के आधार रहे हैं और उसके सिद्धांतों, मानवीय तत्त्वों, और आध्यात्मिकता को समझने में मदद करते हैं।\n" +
//                "वेदों में ब्राह्मण, आरण्यक, और उपनिषद जैसे भाग भी हैं जो विभिन्न धार्मिक और दार्शनिक विचारों को समझाते हैं। वेदों का महत्वपूर्ण स्थान हिन्दू धर्म में है और इन्हें प्राचीन भारतीय संस्कृति की अमूल्य धरोहर माना जाता है।"));
//        eBookcomponent_adapterGS.add(new EBookcomponent_AdapterGS("उपनिषद्","उपनिषद् के दस भाग है।","उपनिषद् हिन्दू धर्म के महत्त्वपूर्ण श्रुति धर्मग्रन्थ हैं। ये वैदिक वाङ्मय के अभिन्न भाग हैं। ","https://tse1.mm.bing.net/th?id=OIP.HTmKnnQ6QxpJszgeU8M80wHaHa&pid=Api&P=0&h=180","उपनिषद् वेदान्त दर्शन के महत्वपूर्ण भाग हैं जो वेदों के बाद आते हैं। ये वेदों के अंतिम भाग में शामिल होते हैं और उनकी दार्शनिक और आध्यात्मिक सिद्धांतों को समझाते हैं। उपनिषद् में मानव जीवन, ब्रह्माण्ड, और परमात्मा की अज्ञेय सत्यता पर विचार किया जाता है।\n" +
//                "उपनिषद् ध्यान, उपासना, और आध्यात्मिक उन्नति की प्रक्रिया पर विचार करते हैं। इनमें जीवन के अन्यतम और आध्यात्मिक उद्देश्य की प्राप्ति के लिए विभिन्न उपाय और मार्गों का वर्णन है। उपनिषद् में आत्मा और परमात्मा की एकता, मानव जीवन के उद्देश्य, और ब्रह्म की महिमा का विवरण है।\n" +
//                "इनमें कई महत्वपूर्ण उपदेश और मंत्र हैं जो सत्य की खोज, समाधान, और संतोष की प्राप्ति के मार्ग दर्शाते हैं। ये धर्म, ज्ञान, और मुक्ति की प्राप्ति के लिए मानव जीवन के मार्गदर्शक के रूप में माने जाते हैं और हिन्दू धर्म के महत्वपूर्ण आध्यात्मिक ग्रंथों में से एक हैं।"));
//        eBookcomponent_adapterGS.add(new EBookcomponent_AdapterGS("पुराण","पुराण के अठारह भाग है।","पुराण हिन्दुओं के धर्म-सम्बन्धी आख्यान ग्रन्थ हैं,जिनमें संसार - ऋषियों - राजाओं के वृत्तान्त आदि हैं।","https://tse4.mm.bing.net/th?id=OIP.kpF4q_dQQ1NS_eIp-iBlXQAAAA&pid=Api&P=0&h=180","पुराण भारतीय साहित्य के प्रमुख धार्मिक ग्रंथों में से एक हैं जो संस्कृत भाषा में लिखे गए हैं। ये प्राचीन ग्रंथ हिन्दू धर्म की विविध धार्मिक और ऐतिहासिक कथाओं को समेटते हैं। पुराणों में विभिन्न देवताओं, ऋषियों, कथाओं, और ऐतिहासिक घटनाओं का वर्णन है।\n" +
//                "पुराणों की प्रमुख विशेषता उनमें भगवान के अलग-अलग अवतारों, धर्म की महत्वपूर्ण बातों, और मानव जीवन की उपयोगी शिक्षाओं का विस्तारपूर्ण वर्णन होता है। पुराणों में धर्म, नीति, और सभ्यता की महत्वपूर्ण बातें दी गई हैं जो आध्यात्मिक उन्नति के लिए मार्गदर्शन प्रदान करती हैं।\n" +
//                "पुराणों की संख्या 18 मानी जाती है और इनमें विष्णु पुराण, भागवत पुराण, शिव पुराण, वायु पुराण, ब्रह्म पुराण, मार्कण्डेय पुराण, ब्रह्माण्ड पुराण, लिंग पुराण, वराह पुराण, गरुड़ पुराण, कूर्म पुराण, मत्स्य पुराण, विष्णु धर्मोत्तर पुराण, नारद पुराण, अग्नि पुराण, पद्म पुराण, ब्रह्मवैवर्त पुराण, वामन पुराण शामिल हैं। पुराणों का महत्वपूर्ण स्थान हिन्दू धर्म में है और इन्हें भारतीय संस्कृति और धर्म की महत्वपूर्ण संस्कृति के प्रमुख स्तम्भ माना जाता है।"));

        context = getActivity();

        ArrayList<EBookcomponent_AdapterGS> list = SharedPreferanceManager.getBookscategory(context);
        if(list!=null){
            EBookcomponent_Adpater eBookcomponent_adpater = new EBookcomponent_Adpater(context,list);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            RecyclerView Homecategory_recycle = view.findViewById(R.id.homecategory_recycle);
            Homecategory_recycle.setAdapter(eBookcomponent_adpater);
            Homecategory_recycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));

        }

        ArrayList<EBookcomponent_AdapterGS> listPopularBooks = SharedPreferanceManager.getPopularBooks(context);
        if(listPopularBooks!=null){
            EBookcomponent_Adpater eBookcomponent_adpaterlistPopularBooks = new EBookcomponent_Adpater(context,listPopularBooks);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            RecyclerView Homepopularbook_recycle = view.findViewById(R.id.homepopularbook_recycle);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
            Homepopularbook_recycle.setAdapter(eBookcomponent_adpaterlistPopularBooks);
            Homepopularbook_recycle.setLayoutManager(gridLayoutManager);
        }

//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Bookscategory");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<EBookcomponent_AdapterGS> list =  new ArrayList<>();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren() ){
////
//                    list.add(new EBookcomponent_AdapterGS(dataSnapshot.child("bookname").getValue().toString(),
//                            dataSnapshot.child("booksubcategory").getValue().toString(),
//                            dataSnapshot.child("bookminidescription").getValue().toString(),
//                            dataSnapshot.child("bookimage").getValue().toString(),
//                            dataSnapshot.child("bookdescription").getValue().toString()));
//                }
//                EBookcomponent_Adpater eBookcomponent_adpater = new EBookcomponent_Adpater(context,list);
//                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
//                RecyclerView Homecategory_recycle = view.findViewById(R.id.homecategory_recycle);
//                Homecategory_recycle.setAdapter(eBookcomponent_adpater);
//                Homecategory_recycle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));

//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        ArrayList<EBookcomponent_AdapterGS> eBookcomponent_adapterGS1 = new ArrayList<>();
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("रामायण","रचयिता महर्षि वाल्मीकि जी","इस महाकाव्य के ऐतिहासिक विकास और संरचनागत परतों को जानने के लिए कई प्रयास किए गए हैं।","https://tse2.mm.bing.net/th?id=OIP.BB9XLfjjgmGMi0L_avw6FAHaKi&pid=Api&P=0&h=180","रामायण, भारतीय साहित्य का महाकाव्य है जो संस्कृत भाषा में लिखा गया है। यह एक प्राचीन ऐतिहासिक कथा है जो भगवान राम की जीवनी पर आधारित है। इसमें भगवान राम के जीवन के विभिन्न पहलुओं का वर्णन है जैसे कि उनका जन्म, बचपन, विवाह, वनवास, सीता हरण, लंका दहन, और अयोध्या वापसी।\n" +
                "रामायण में भगवान राम के धर्म, नैतिकता, साहस, और नीति के उदाहरणों का वर्णन है जो मानव जीवन के आदर्श माने जाते हैं। इसमें उनके परिवार, भक्त, और शिष्यों के साथ उनके संवादों का वर्णन है जो जीवन की अनमोल शिक्षाएं प्रदान करते हैं।\n" +
                "रामायण की कहानी में भगवान राम के साथ सीता, लक्ष्मण, और हनुमान जैसे प्रमुख चरित्रों का भी महत्वपूर्ण स्थान है। इसमें धर्म और न्याय की महत्ता, प्रेम और सेवा की महिमा, और सत्य और धैर्य की महत्वपूर्ण उपदेशों का विस्तारपूर्ण वर्णन है। रामायण मानवता, धर्म, और नैतिकता के महत्व को समझाने और समर्थन करने के लिए प्रसिद्ध है।"));
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("महाभारत","रचयिता महर्षि वेदव्यास जी","यह काव्यग्रंथ भारत का अनुपम धार्मिक, पौराणिक, ऐतिहासिक और दार्शनिक ग्रंथ हैं।",
                "https://tse4.mm.bing.net/th?id=OIP.EMLB3kLl9r4YzFylIVGs2AHaJ3&pid=Api&P=0&h=180","महाभारत, भारतीय साहित्य का महाकाव्य है जो संस्कृत भाषा में लिखा गया है। यह एक अत्यंत महत्वपूर्ण और प्रसिद्ध ऐतिहासिक कथा है जिसमें भारतीय धर्म, दर्शन, नैतिकता, और समाज की विविधता का विवरण है। इसमें भारतीय संस्कृति के महानायकों और इतिहास की अनेक प्रमुख घटनाओं का वर्णन है।\n" +
                "महाभारत के अनुसार, यह कथा महाभारत युद्ध के चारों ओर घटित होने वाली परिपूर्ण कथा है, जिसमें पांडवों और कौरवों के बीच हुए संघर्ष का विवरण है। इसमें धर्म, नीति, और उच्च आदर्शों की उपलब्धि के लिए लड़ने वाले व्यक्तियों के चरित्रों का वर्णन है।\n" +
                "महाभारत के भाग भी विभिन्न पर्वों में विभाजित हैं, जैसे कि आदि पर्व, सभा पर्व, वन पर्व, विराट पर्व, भीष्म पर्व, द्रोण पर्व, कर्ण पर्व, शल्य पर्व, सौप्तिक पर्व, स्त्री पर्व, अश्वमेध पर्व, अनुशासन पर्व, मौसल पर्व, क्षत्रिय संबंध पर्व, और संतिपर्व। इसमें महाभारतीय युद्ध की महाकवि ग्रंथि एक अद्भुत सामग्री से भरी है जो भारतीय समाज, धर्म, और संस्कृति की सार्थकता को बताती है।"));
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("श्रीमद्भगवद्गीता","रचयिता महर्षि वेदव्यास जी","यह महाभारत के भीष्मपर्व का अंग है। गीता में 18 अध्याय और 700 श्लोक हैं।","https://m.media-amazon.com/images/I/613WYGLXNEL._SY466_.jpg","श्रीमद्भगवद्गीता, भारतीय धर्म की महत्वपूर्ण पुराणों में से एक है, जिसे महाभारत के भीष्म पर्व में मिलती है। इसमें भगवान श्रीकृष्ण द्वारा अर्जुन को दिए गए उपदेशों का वर्णन है जो कुरुक्षेत्र के युद्ध के पहले हुआ था। यह उपदेश अर्जुन के धर्मसंकट और उसके कर्तव्य की व्याख्या करते हैं और उसे आध्यात्मिक सच्चाई की ओर प्रेरित करते हैं।\n" +
                "भगवद्गीता में जीवन के विभिन्न पहलुओं, धर्म, कर्म, भक्ति, और ज्ञान की महत्वपूर्ण बातें दी गई हैं। इसमें समग्र विश्व के निर्माण का विस्तारपूर्ण वर्णन, जीवन के उद्देश्य, और मोक्ष की प्राप्ति के मार्ग की चर्चा की गई है। भगवद्गीता धर्म, जीवन, समस्याओं, और समाधानों पर गहरा विचार करती है और मानव जीवन के तत्वों को समझाने में सहायक होती है। इसका सारांश है कि धर्मीय जीवन कैसे जीना चाहिए और सही मार्ग कैसे चुनना चाहिए।"));
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("शिव पुराण","रचयिता महर्षि वेदव्यास जी","शिव पुराण में भगवान शिव के विविध रूपों, अवतारों, ज्योतिर्लिंगों, भक्तों और भक्ति का विशद् वर्णन किया गया है।","https://tse2.mm.bing.net/th?id=OIP.c20IxMYud5jP0DI-mKE8dwHaIq&pid=Api&P=0&h=180","शिव पुराण एक प्रमुख हिन्दू धार्मिक ग्रंथ है जो भगवान शिव के विभिन्न रूप, लीलाएं, महत्व, और उनके भक्तों द्वारा की गई उपासना का वर्णन करता है। यह पुराण भागवत पुराण, विष्णु पुराण, और ब्रह्म पुराण की तरह भारतीय पुराणों की एक महत्वपूर्ण समूह में से एक है। इसमें भगवान शिव के बारे में उनकी पूजा, उपासना, और विभिन्न अद्भुत घटनाएं वर्णित हैं।\n" +

                "शिव पुराण में भगवान शिव की उपासना की विधियां, उनकी विशेषताएं, और उनके विभिन्न अवतारों के बारे में विस्तृत वर्णन होता है। इसमें भगवान शिव की महिमा, महात्म्य, और उनके भक्तों के लीलाएं भी विस्तार से दर्शाई गई हैं। यह पुराण भारतीय संस्कृति में शिव की महत्वपूर्ण भूमिका को समझने में मदद करता है और भक्तों को उनकी उपासना में प्रेरित करता है।"));
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("विज्ञान भैरव तंत्र","रचयिता नन्द लाल दशोरा जी ","शिव के उत्तर में केवल विधियाँ हैं। सबसे पुरानी, सबसे प्राचीन विधियाँ।","https://tse1.mm.bing.net/th?id=OIP.asfrahJ-ECUDjbFxWOwqLgHaHa&pid=Api&P=0&h=180","विज्ञान भैरव तंत्र एक प्राचीन तांत्रिक ग्रंथ है जो भारतीय तांत्रिक और योगी परंपरा में प्रमुख स्थान रखता है। इसमें विभिन्न ध्यान तकनीकों, मंत्रों, और उपासना की विधियों का विस्तृत वर्णन है जिनका उपयोग साधकों को आध्यात्मिक उन्नति और सिद्धियों की प्राप्ति में मदद करने के लिए किया जाता है।\n" +

                "विज्ञान भैरव तंत्र में विभिन्न ध्यान और साधना की विधियां दी गई हैं जो मानसिक शांति, सांत्वना, और आध्यात्मिक उन्नति को प्राप्त करने में सहायक होती हैं। यह ग्रंथ आत्म-साक्षात्कार और आत्म-समर्पण की महत्वपूर्ण उपासनाएं प्रस्तुत करता है जो आध्यात्मिक साधना के लिए मार्गदर्शन प्रदान करती हैं। इसमें मानव चेतना और उसकी साकार और निराकार शक्तियों का विस्तृत विवरण दिया गया है। इसे एक मार्गदर्शिका के रूप में समझा जाता है जो आध्यात्मिक साधकों को साधना और मोक्ष की ओर प्रेरित करती है।"));
        eBookcomponent_adapterGS1.add(new EBookcomponent_AdapterGS("सुन्दरकाण्ड","रचयिता महर्षि वाल्मीकि जी ","शसुन्दरकाण्ड पाठ रामायण का एक महत्वपूर्ण अध्याय है,जिसमें हनुमानजी की वीरता,बुद्धि और भक्ति के अनूठे परिचय होते हैं","https://tse3.mm.bing.net/th?id=OIP.SvT8Es34k3JRp1HOyZkkhwAAAA&pid=Api&P=0&h=180","सुन्दरकाण्ड, भारतीय महाकाव्य रामायण का महत्वपूर्ण हिस्सा है और इसे महर्षि वाल्मीकि की रचना माना जाता है। वाल्मीकि ने रामायण की रचना की, जिसमें भगवान राम, उनकी पत्नी सीता, उनके भाई लक्ष्मण और उनके भक्त हनुमान के जीवन और कार्यों का वर्णन किया गया है। सुन्दरकाण्ड विशेष रूप से उस भाग को संदर्भित करता है जिसमें हनुमान लंका की खोज में जाता है, जहां रावण ने सीता का हरण किया था। कहानी में हनुमान के साहस और भक्ति के कार्यों का वर्णन किया गया है। इसे कई लोग पवित्र पाठ्यक्रम के रूप में सम्मान करते हैं और आध्यात्मिक और धार्मिक उद्देश्यों के लिए अक्सर पाठ या जप किया जाता है।"));

//        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Ananta").child("PopularBooks");
//        databaseReference1.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//           ArrayList<EBookcomponent_AdapterGS> list =  new ArrayList<>();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren() ){
////
//                    list.add(new EBookcomponent_AdapterGS(dataSnapshot.child("bookname").getValue().toString(),
//                            dataSnapshot.child("bookauhtorname").getValue().toString(),
//                            dataSnapshot.child("bookminidescription").getValue().toString(),
//                            dataSnapshot.child("bookimage").getValue().toString(),
//                            dataSnapshot.child("bookdescription").getValue().toString()));
//                }
//                EBookcomponent_Adpater eBookcomponent_adpater1 = new EBookcomponent_Adpater(context,list);
//                RecyclerView Homepopularbook_recycle = view.findViewById(R.id.homepopularbook_recycle);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
//                Homepopularbook_recycle.setAdapter(eBookcomponent_adpater1);
//                Homepopularbook_recycle.setLayoutManager(gridLayoutManager);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        Likedicon = view.findViewById(R.id.Likedicon);
        Likedicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWishlistlikedActivity.class);
                startActivity(intent);
            }
        });

//        notification = view.findViewById(R.id.notification);
//        notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NotificationFragment notificationFragment = new NotificationFragment();
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.framelayout, notificationFragment).commit();
//            }
//        });

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
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ananta").child("Videos");
//
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs1.mp4?alt=media&token=afb70017-96d4-42b5-b4d2-7f37d5b08b19&_gl=1*4ygngb*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYzODkuNjAuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fv3.mp4?alt=media&token=b46ccde8-a412-4730-8029-d3d8291823aa&_gl=1*1xcppvo*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYyNTMuMzQuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs13.mp4?alt=media&token=91555dc0-8294-4d1d-9491-f7a5be44c65e&_gl=1*hb18sn*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYwOTMuNTEuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs2.mp4?alt=media&token=c843a955-db19-4c85-b4d7-05ba801dcd4c&_gl=1*12bef2z*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYxMTUuMjkuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs3.mp4?alt=media&token=5b61b8b8-965b-44f7-afd2-7b3d80b41abd&_gl=1*ypgy7k*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYxMzIuMTIuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fv5.mp4?alt=media&token=285e6467-7f8d-43b0-9385-49d448288862&_gl=1*itasi7*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYyODUuMi4wLjA.");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs4.mp4?alt=media&token=2bd7a7cf-32b9-43e1-a393-1f2bb2348810&_gl=1*xp1eye*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYxNTUuNjAuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs5.mp4?alt=media&token=45a946f7-e81a-484e-af62-ceb92bc398d0&_gl=1*x3kqm2*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYxODAuMzUuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fs6.mp4?alt=media&token=38fcf326-f7c3-45c6-a2d9-4350f50bf7f0&_gl=1*1y8afbm*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYyMDQuMTEuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fv2.mp4?alt=media&token=8ce17b0a-feb3-4dbb-8575-ec11341a5bc8&_gl=1*1at3eik*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYyMjcuNjAuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fv4.mp4?alt=media&token=6b265082-451c-4448-bf23-df014bb3d0fc&_gl=1*94m52w*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYyNzIuMTUuMC4w");
//        arrayList.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/Videos%2Fv7.mp4?alt=media&token=6c4cadce-a538-4889-98c1-ca3f28b2b9f5&_gl=1*1tkw1l0*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5ODE1NDI3OC4zMi4xLjE2OTgxNTYzMjQuMjUuMC4w");
//
//        for (int i = 0; i < arrayList.size() ; i++) {
//        databaseReference.child("video"+i)
//                .child("uploaderProfile").setValue("ddvdv");
//        databaseReference.child("video"+i)
//                .child("videoDesc").setValue("Demo discription");
//        databaseReference.child("video"+i)
//                .child("videoTitle").setValue("Ananta");
//        databaseReference.child("video"+i)
//                .child("videoUrl").setValue(arrayList.get(i));
//        databaseReference.child("video"+i)
//                .child("videoLikes").setValue("1");
//        databaseReference.child("video"+i)
//                .child("videoComment").setValue("1");
//        databaseReference.child("video"+i)
//                .child("videoShare").setValue("1");
//        }

//        for (int i = 0; i < arrayList.size() ; i++) {
//        Map<String, Object> updates = new HashMap<>();
//        updates.put("videoLikes", "151");
////        updates.put("phoneNumber", replacePhoneNumber);
//        databaseReference.child("video"+i).updateChildren(updates);}

//
//        ArrayList<String> bookkeyword = new ArrayList<>();
//
//        bookkeyword.add("Vedas");
//        bookkeyword.add("Upanishad");
//        bookkeyword.add("Purana");

        ArrayList<String> bookkeyword = new ArrayList<>();
        ArrayList<String> bookid = new ArrayList<>();
        bookkeyword.add("Ramayan");
        bookkeyword.add("Mahabharat");
        bookkeyword.add("Bhagwat geeta");
        bookkeyword.add("Shiv Puran");
        bookkeyword.add("Vigyan Bhairav Tantra");
        bookkeyword.add("Sunderkand");
        bookid.add("LibraryBooksbook1");
        bookid.add("LibraryBooksbook2");
        bookid.add("LibraryBooksbook3");
        bookid.add("LibraryBooksbook4");
        bookid.add("LibraryBooksbook5");
        bookid.add("LibraryBooksbook6");


        ArrayList<String> bookurl = new ArrayList<>();
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2F%E0%A4%B5%E0%A4%BE%E0%A4%B2%E0%A5%8D%E0%A4%AE%E0%A5%80%E0%A4%95%E0%A4%BF-%E0%A4%B0%E0%A4%BE%E0%A4%AE%E0%A4%BE%E0%A4%AF%E0%A4%A3-srimad-valmiki-ramayana.pdf?alt=media&token=c5e99c96-358c-4a47-b199-9ccec9886cee");
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FMahabharata%20Volume%201.pdf?alt=media&token=ed2d3198-761e-4b3e-bd60-80628302e5a7&_gl=1*qu22ws*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTM0MjQxOC40NC4xLjE2OTkzNDQzNjguMi4wLjA.");
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fbhagwat-geeta.pdf?alt=media&token=82964508-47de-4b7f-a64b-96f8d49b43ee");
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fshivpuran.pdf?alt=media&token=17e7a789-4542-4369-ad38-b4576af65b97");
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FVigyanBhairavTantra.pdf?alt=media&token=136f0bab-006e-4106-89eb-9a95d42c3f3b");
        bookurl.add("https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fsundar%20kand.pdf?alt=media&token=ad91466f-d5f5-4ddf-9844-e10021f3c7fb");

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
//             getting data directly from main Activity
//                if (getActivity() != null && getActivity() instanceof MainActivity) {
//                    ((MainActivity)getActivity()).setArrayList(videoGSArrayList);
//                }

        return view;
    }
}
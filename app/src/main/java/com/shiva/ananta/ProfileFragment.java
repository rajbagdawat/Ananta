package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shiva.ananta.Admin.AddData;


public class ProfileFragment extends Fragment {

    LinearLayout Logout,add;
    ImageView Likedicon,notification;
    ImageView Myprofilepic,Uploadpic;
    static ActivityResultLauncher<Intent> activityResultLauncher;
    AlertDialog dialog;
    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view  = inflater.inflate(R.layout.fragment_profile,container,false);


        Uploadpic = view.findViewById(R.id.uploadpic);
        Myprofilepic = view.findViewById(R.id.imageId);
        Logout = view.findViewById(R.id.logout);
        add = view.findViewById(R.id.add);
        TextView MyNumber = view.findViewById(R.id.MyNumber);

//        login = view.findViewById(R.id.login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String phoneNumber = user.getPhoneNumber();
            MyNumber.setText(phoneNumber);
            if(phoneNumber.equals("+919691375725")){
                add.setVisibility(View.VISIBLE);
            }
        }else {
            MyNumber.setVisibility(View.GONE);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddData.class);
                startActivity(intent);
            }
        });

        Uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.aleartdialog);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Logout.setVisibility(View.VISIBLE);
        } else {
            Logout.setVisibility(View.GONE);
        }


            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = builder.setTitle("Logout !")
                            .setMessage("Are you sure you want to logout ?")
                            .setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    onStop();
                                    mAuth.signOut();
//                                    SharedPreferanceManager.clearHomeCategory(getContext());
                                    Intent intent = new Intent(getContext(), LoginPage.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create();
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onShow(DialogInterface arg0) {
                            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.black);
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                        }
                    });
                    dialog.show();
                }
            });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getData() != null) {
                    final Uri tfile = result.getData().getData();
                    Myprofilepic.setImageURI(tfile);
                    Toast.makeText(getContext(), " Profile pic uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Likedicon = view.findViewById(R.id.Likedicon);
//        Likedicon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyWishlistlikedActivity.class);
//                startActivity(intent);
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
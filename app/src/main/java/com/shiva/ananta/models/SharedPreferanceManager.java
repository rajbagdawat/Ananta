package com.shiva.ananta.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shiva.ananta.adapters.HomeCategory_GS;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferanceManager {
    private static final String SHARED_PREFKEYBookscategory = "Books_Category_Pref";
    private static final String RESPONSE_KEYBookscategory = "Books_Category_Response";

    private static final String SHARED_PREFKEYPopularBooks = "PopularBooks_Pref";
    private static final String SHARED_PREFKEYHomeCategory = "SHARED_PREFKEYHomeCategory";
    private static final String SHARED_PREFKEYVideos = "SHARED_PREFKEYVideos";
    private static final String RESPONSE_KEYPopularBooks = "PopularBooks_Response";
    private static final String SHARED_PREFKEYLibraryBooks = "LibraryBooks_Pref";
    private static final String RESPONSE_KEYLibraryBooks = "LibraryBooks_Response";
    private static final String SHARED_PREFKEYLibraryBooksdata = "LibraryBooksdata_Pref";
    private static final String RESPONSE_KEYLibraryBooksdata = "LibraryBooksdata_Response";
    private static final String RESPONSE_KEYHomeCategory = "RESPONSE_KEYHomeCategory";
    private static final String RESPONSE_KEYVideos = "RESPONSE_KEYVideos";


    public static void saveBookscategory(Context context, ArrayList<EBookcomponent_AdapterGS> eBookcomponentAdapterGS) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYBookscategory, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(eBookcomponentAdapterGS);
        editor.putString(RESPONSE_KEYBookscategory, json);
        editor.apply();
    }

    public static ArrayList<EBookcomponent_AdapterGS> getBookscategory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYBookscategory, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYBookscategory, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<EBookcomponent_AdapterGS>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearBookscategory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYBookscategory, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void savePopularBooks(Context context, ArrayList<EBookcomponent_AdapterGS> eBookcomponentAdapterGS) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYPopularBooks, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(eBookcomponentAdapterGS);
        editor.putString(RESPONSE_KEYPopularBooks, json);
        editor.apply();
    }

    public static ArrayList<EBookcomponent_AdapterGS> getPopularBooks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYPopularBooks, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYPopularBooks, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<EBookcomponent_AdapterGS>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearPopularBooks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYPopularBooks, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    public static void saveLibraryBooks(Context context, ArrayList<Library_component_GS> library_component_gs) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooks, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(library_component_gs);
        editor.putString(RESPONSE_KEYLibraryBooks, json);
        editor.apply();
    }

    public static  ArrayList<Library_component_GS> getLibraryBooks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooks, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYLibraryBooks, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Library_component_GS>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearLibraryBooks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooks, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void saveLibraryBooksdata(Context context, ArrayList<LibrarySharedPreferancedata> librarySharedPreferancedataArrayList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooksdata, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(librarySharedPreferancedataArrayList);
        editor.putString(RESPONSE_KEYLibraryBooksdata, json);
        editor.apply();
    }

    public static  ArrayList<LibrarySharedPreferancedata> getLibraryBooksdata(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooksdata, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYLibraryBooksdata, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<LibrarySharedPreferancedata>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearLibraryBooksdata(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYLibraryBooksdata, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void saveHomeCategory(Context context, ArrayList<HomeCategory_GS> homeCategoryList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYHomeCategory, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(homeCategoryList);
        editor.putString(RESPONSE_KEYHomeCategory, json);
        editor.apply();
    }

    public static ArrayList<HomeCategory_GS> getHomeCategory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYHomeCategory, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYHomeCategory, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HomeCategory_GS>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearHomeCategory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYHomeCategory, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }



    public static void saveVideos(Context context, ArrayList<VideoGS> videoList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYVideos, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(videoList);
        editor.putString(RESPONSE_KEYVideos, json);
        editor.apply();
    }

    public static ArrayList<VideoGS> getVideos(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYVideos, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEYVideos, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<VideoGS>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearVideos(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEYVideos, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private static final String SHARED_PREFKEY_WISHLIST = "wishlist_shared_pref";
    private static final String RESPONSE_KEY_WISHLIST = "wishlist_response";

    public static void saveWishlist(Context context, ArrayList<String> wishlist) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEY_WISHLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(wishlist);
        editor.putString(RESPONSE_KEY_WISHLIST, json);
        editor.apply();
    }

    public static ArrayList<String> getWishlist(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEY_WISHLIST, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(RESPONSE_KEY_WISHLIST, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearWishlist(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFKEY_WISHLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}

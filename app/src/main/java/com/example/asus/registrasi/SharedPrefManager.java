package com.example.asus.registrasi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 9/26/2017.
 */

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_NIK = "keynik";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_TELEPON = "keytelepon";
    //private static final String KEY_FOTO = "keyfoto";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NIK, user.getNik());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_ALAMAT, user.getAlamat());
        editor.putString(KEY_TELEPON, user.getTelepon());
        editor.putString(KEY_USERNAME, user.getUsername());
       // editor.putString(KEY_FOTO, user.getFoto());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NIK, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getString(KEY_TELEPON, null),
                sharedPreferences.getString(KEY_USERNAME, null)
                //sharedPreferences.getString(KEY_FOTO, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}

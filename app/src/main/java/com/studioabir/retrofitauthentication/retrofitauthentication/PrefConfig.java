package com.studioabir.retrofitauthentication.retrofitauthentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {

    // This is a simple class for configure Sharedpreference for Session Login

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    // Method for wRITE lOGIN STATUS
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    // Method for rEAD lOGIN STATUS
    public boolean ReadLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);

    }

    // Method for Save user data into SPREF is user login
    public void writeName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.commit();
    }

    // Method for Fetch user name from SPREF
    public String readName()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_user_name),"User");

    }

    // Method for Display Message Toast
    public void displayToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


}

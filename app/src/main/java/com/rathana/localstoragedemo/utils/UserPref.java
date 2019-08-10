package com.rathana.localstoragedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rathana.localstoragedemo.model.User;

public class UserPref {

    static final String PREF_NAME="user_pref";
    static final String user_name="name";
    static final String user_password="password";

    public static void write(Context context, User user){
        SharedPreferences.Editor editor=getPref(context).edit();
        if(user!=null){
            editor.putString(user_name,user.getName());
            editor.putString(user_password,user.getPassword());
            editor.apply();
        }else{
            editor.clear().apply();
        }
    }

    public static void logout(Context context){
        write(context,null);
    }

    public static boolean isLogin(Context context){
        SharedPreferences pref=getPref(context);
        if(pref.getString(user_name,null)==null &&
           pref.getString(user_password,null)==null){
            return false;
        }else return true;
    }

    public static User read(Context context){
        SharedPreferences preferences=getPref(context);
        return new User(
                preferences.getString(user_name,""),
                preferences.getString(user_password,""));
    }

    private static SharedPreferences getPref(Context context){
        return context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }


}

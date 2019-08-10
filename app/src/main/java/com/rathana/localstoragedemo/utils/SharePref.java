package com.rathana.localstoragedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    public static SharedPreferences getSharePrefs(Context context, String fileName){
        return context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }
}

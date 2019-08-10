package com.rathana.localstoragedemo.utils;

import android.os.Environment;

public class CheckStorageState {

    public static boolean isExternalStorageWritable(){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadable(){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
           Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)
        ){
            return true;
        }
        return false;
    }

}

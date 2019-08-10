package com.rathana.localstoragedemo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.localstoragedemo.utils.CheckPermissionHelper;
import com.rathana.localstoragedemo.utils.CheckStorageState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileActivity extends AppCompatActivity {

    static final  String fileName="file1.doc";
    TextView result;
    String text="Write data to internal storage";
    String text2="Write data to external storage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        result=findViewById(R.id.result);
    }


    ///External storage block
    ///private File

    public void onWriteExternalStoragePrivateFile(View v){
        writeToExternalStoragePrivateFile();
    }

    public void onWriteExternalStoragePublicFile(View v){
        writeToExternalStoragePublicFile();
    }

    public void writeToExternalStoragePrivateFile(){

       if(CheckStorageState.isExternalStorageWritable()){

            File file = new File(getExternalFilesDir(
                    Environment.DIRECTORY_DOWNLOADS),
                    "docs");

            try {
                if(!file.mkdirs()){
                    file.mkdir();
            }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "cannot create file", Toast.LENGTH_SHORT).show();
            }

            try (OutputStream ous= new FileOutputStream(file.getAbsolutePath()+"/"+fileName)){

                ous.write(text2.getBytes());
                Toast.makeText(this,"write success",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.toString());
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

       }
    }


    public void writeToExternalStoragePublicFile(){

        if(CheckStorageState.isExternalStorageWritable()){

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS),
                    "docs");

            try {
                if(!file.mkdirs()){
                    file.mkdir();
                }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "cannot create file", Toast.LENGTH_SHORT).show();
            }

            try (OutputStream ous= new FileOutputStream(file.getAbsolutePath()+"/file2.txt")){

                ous.write(text2.getBytes());
                Toast.makeText(this,"write success",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.toString());
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void onReadExternalStorage(View view){
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),
                "docs");

        if(CheckStorageState.isExternalStorageReadable()){
            try(InputStream ins=new FileInputStream(
                    file.getAbsolutePath()+"/"+fileName

            )){
                String data="";
                int ch;
                while ((ch=ins.read())!=-1){
                    data=data+ (char)ch;
                }
                result.setText(data);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "file name "+fileName+ " is not exist", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void delete(View view){
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),
                "docs/"+fileName);
        file.delete();
        Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
    }

    ///internal storage block
    public void writeInternalStorage(View view){
        writeToInternalStorage(fileName);
    }
    public void readInternalStorage(View view){
        String text=readFromInternalStorage(fileName);
        result.setText(text);

    }

    public void removeInternalStorage(View view){
        deleteFile(fileName);
        Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
    }

    private void writeToInternalStorage(String filename){
        //1 File =new File();
        //2 openFileOutput();
        //3 File.createTempFile()

        //OutputStream ous=null;


        try( OutputStream ous=openFileOutput(filename,Context.MODE_PRIVATE);
             OutputStream ous2 =new FileOutputStream(getFilesDir()+"/file2.txt",true);
             OutputStream ous3 =new FileOutputStream(getCacheDir()+"/file1.txt",true);){

            ous.write(text.getBytes());
            ous2.write(text.getBytes());
            ous3.write(text.getBytes());
            Toast.makeText(this, "Write success", Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException fileNO){
            fileNO.fillInStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }
//        finally {
//            try {
//                ous.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    private String readFromInternalStorage(String fileName){
        try(InputStream ins=openFileInput(fileName)){
            String data="";
            int c;
            while ( (c=ins.read()) !=-1 ){
                data=data+ (char)c;
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "file name "+ fileName+" is not exist.", Toast.LENGTH_SHORT).show();
        }

        return "";
    }

    //request write external storage permission

    @Override
    protected void onStart() {
        super.onStart();
        CheckPermissionHelper.checkExternalStorage(this);
    }
}

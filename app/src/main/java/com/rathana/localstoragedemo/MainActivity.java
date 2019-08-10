package com.rathana.localstoragedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.localstoragedemo.utils.SharePref;
import com.rathana.localstoragedemo.utils.UserPref;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText productName,price;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price=findViewById(R.id.tvPrice);
        productName=findViewById(R.id.tvProductName);
        tvResult=findViewById(R.id.tvResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!UserPref.isLogin(this)){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    public void writeProduct(View view){
        Product product=new Product();
        product.setName(productName.getText().toString());
        product.setPrice(Double.parseDouble(price.getText().toString()));
        //SharedPreferences.Editor editor=writePref().edit();
        SharedPreferences.Editor editor= SharePref.getSharePrefs(
                this,
                "product_pref")
                .edit();

        editor.putString("name",product.getName());
        editor.putFloat("price",(float) product.getPrice());
        //save
        //write().commit(); //save on UI Thread
        editor.apply();//save on background Thread
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }
    public void readProduct(View view){
        SharedPreferences pref=SharePref.getSharePrefs(this,"product_pref");
//        String name= writePref().getString("name","");
//        float price =writePref().getFloat("price",0f);
        String name = pref.getString("name","");
        float price = pref.getFloat("price",0f);
        tvResult.setText( "Name :" + name +"\nPrice : "+price);
    }

    public void deleteProduct(View view){
//        SharedPreferences.Editor editor=writePref().edit();
//        editor.clear();
//        editor.apply();
        SharePref.getSharePrefs(this,"product_pref").edit().clear().apply();
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
    }

    private SharedPreferences writePref(){
        //SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        return getPreferences(Context.MODE_PRIVATE);
    }

    private SharedPreferences sharedPrefs(String fileName){
        return  getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }

    public void logout(View view){
        UserPref.logout(this);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}














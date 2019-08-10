package com.rathana.localstoragedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.localstoragedemo.data.Database;
import com.rathana.localstoragedemo.model.User;
import com.rathana.localstoragedemo.utils.UserPref;

public class LoginActivity extends AppCompatActivity {

    EditText userName, password;

    Button btnLogin;
    TextView btnGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName=findViewById(R.id.tvName);
        password=findViewById(R.id.tvPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnGoToRegister=findViewById(R.id.goRegister);


        btnLogin.setOnClickListener(v->{
            User user=new User(userName.getText().toString(),password.getText().toString());
            User u = Database.getUserRepo().authenticate(user);
            if(u==null)
                Toast.makeText(this, "Login Fail!", Toast.LENGTH_SHORT).show();
            else{
                UserPref.write(this,u);
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }

        });

        btnGoToRegister.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class));
            finish();
        });

    }
}

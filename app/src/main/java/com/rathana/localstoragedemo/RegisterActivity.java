package com.rathana.localstoragedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rathana.localstoragedemo.data.Database;
import com.rathana.localstoragedemo.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText userName, password;

    Button btnRegister;
    TextView btnGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName=findViewById(R.id.tvName);
        password=findViewById(R.id.tvPassword);
        btnRegister=findViewById(R.id.btnRegister);
        btnGoToLogin=findViewById(R.id.goLogin);


        btnRegister.setOnClickListener(v->{
            User user=new User(
                    userName.getText().toString(),
                    password.getText().toString());

            Database.getUserRepo().create(user);
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        });

        btnGoToLogin.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        });

    }
}

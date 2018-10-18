package com.example.user.dpkb_jateng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login_admin extends AppCompatActivity {
    private EditText username,pass;
    private Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        username=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        signin=(Button)findViewById(R.id.sign_in_adm);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase("Admin") && pass.getText().toString().equalsIgnoreCase("Admin")){
                    Intent in = new Intent(login_admin.this, Admin.class);
                    startActivity(in);
                }
            }
        });

    }
}

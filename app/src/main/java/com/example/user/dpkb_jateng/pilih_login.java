package com.example.user.dpkb_jateng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pilih_login extends AppCompatActivity {
    private Button log_adm,log_usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_login);
        log_adm=(Button)findViewById(R.id.login_admin);
        log_usr=(Button)findViewById(R.id.login_user);


        log_adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(pilih_login.this,login_admin.class);
                startActivity(in);
                finish();
            }
        });
        log_usr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(pilih_login.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}

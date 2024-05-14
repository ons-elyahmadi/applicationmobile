package com.example.test.ADMIN;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.users.LoginActivity;
import com.example.test.users.reclamationQr.QR;
import com.example.test.users.reclamationmail.EmailActivity;
import com.example.test.users.reclamationtel.SMSActivity;

public class Welcome extends AppCompatActivity {
    private Button Adminlogin , Userlogin ;
    private TextView  About;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        Adminlogin = findViewById(R.id.Admin);
        Userlogin =  findViewById(R.id.user);
        About = findViewById(R.id.About);


        Adminlogin.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Welcome.this, LoginAdmin.class);
                startActivity(intent);
            }
        });
        Userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Welcome.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, About.class));
            }
        });
    }
}
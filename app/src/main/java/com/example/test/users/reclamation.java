package com.example.test.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.ADMIN.About;
import com.example.test.ADMIN.LoginAdmin;
import com.example.test.ADMIN.Welcome;
import com.example.test.R;
import com.example.test.users.reclamationQr.barecode;
import com.example.test.users.reclamationmail.EmailActivity;
import com.example.test.users.reclamationtel.SMSActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class reclamation  extends AppCompatActivity {
    private Button reclamationmail , reclamationsms ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reclamation);
        reclamationsms = findViewById(R.id.reclamationsms);
        reclamationmail = findViewById(R.id.reclamationmail);


        reclamationsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reclamation.this, SMSActivity.class);
                startActivity(intent);
            }
        });
        reclamationmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reclamation.this, EmailActivity.class);
                startActivity(intent);
            }
        });

    }
}
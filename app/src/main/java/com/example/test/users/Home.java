package com.example.test.users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.users.reclamationQr.barecode;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private Button Profile ;
    private String nameUser, emailUser, usernameUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Profile = findViewById(R.id.Editprofile);


        Intent intent = getIntent();
        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_search  ) {
                startActivity(new Intent(getApplicationContext(), HomeCategorieUsers.class));

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else if ( item.getItemId() == R.id.bottom_settings) {
                Intent  intent1 = new Intent(getApplicationContext(), barecode.class);
                // Transférer les données utilisateur


                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                Intent navigationIntent = new Intent(getApplicationContext(), reclamation.class);
                addUserDataToIntent(navigationIntent);
                startActivity(navigationIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else {
                return false;
            }
        });

        Profile.setOnClickListener(view -> {
            Intent profileIntent = new Intent(Home.this, MainActivity.class);
            addUserDataToIntent(profileIntent);
            startActivity(profileIntent);
        });


    }

    private void addUserDataToIntent(Intent intent) {
        intent.putExtra("name", nameUser);
        intent.putExtra("email", emailUser);
        intent.putExtra("username", usernameUser);
        intent.putExtra("password", passwordUser);
    }
}

package com.example.test.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Adapter.AdapterCategorie;
import com.example.test.R;
import com.example.test.models.Categorie;
import com.example.test.users.reclamationQr.barecode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeCategorieUsers extends AppCompatActivity {
    FloatingActionButton fab;
    FloatingActionButton deleteFab;
    private GridView gridView;
    private ArrayList<Categorie> dataList;
    private AdapterCategorie adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_categorie_users);

        gridView = findViewById(R.id.gridView);
        dataList = new ArrayList<>();
        adapter = new AdapterCategorie(this, dataList);
        gridView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_search) {
                return true;
            } else if (item.getItemId() == R.id.bottom_profile  ) {
                startActivity(new Intent(getApplicationContext(), reclamation.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if ( item.getItemId() == R.id.bottom_settings) {
                Intent intent = new Intent(getApplicationContext(), barecode.class);
                // Transférer les données utilisateur


                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Categorie dataClass = dataSnapshot.getValue(Categorie.class);
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'objet Categorie correspondant à la position cliquée
                Categorie selectedCategorie = dataList.get(position);

                // Ici, vous pouvez passer des données supplémentaires à l'activité suivante
                // Par exemple, vous pouvez passer l'ID de la catégorie sélectionnée
                String categorieId = selectedCategorie.getCaption();
                Intent intent = new Intent(HomeCategorieUsers.this, ProdutsUsers.class);
                intent.putExtra("categorie_id", categorieId);

                // Passer également les données utilisateur
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("username", getIntent().getStringExtra("username"));
                intent.putExtra("password", getIntent().getStringExtra("password"));

                // Démarrer la nouvelle activité
                startActivity(intent);
            }
        });
    }
}

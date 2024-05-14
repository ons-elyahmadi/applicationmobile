package com.example.test.ADMIN;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Adapter.AdapterCategorie;
import com.example.test.R;
import com.example.test.models.Categorie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeCategorie extends AppCompatActivity {
    FloatingActionButton fab;
    FloatingActionButton deleteFab;
    private GridView gridView;
    private ArrayList<Categorie> dataList;
    private AdapterCategorie adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_categorie);
        fab = findViewById(R.id.fab);
        deleteFab = findViewById(R.id.delete);
        gridView = findViewById(R.id.gridView);
        dataList = new ArrayList<>();
        adapter = new AdapterCategorie(this, dataList);
        gridView.setAdapter(adapter);

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeCategorie.this, Categoryupload.class);
                startActivity(intent);
                finish();
            }
        });

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeCategorie.this);
                builder.setTitle("Supprimer une catégorie");
                final EditText input = new EditText(HomeCategorie.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String categoryName = input.getText().toString().trim();
                        adapter.deleteCategoryByName(categoryName);
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
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
                Intent intent = new Intent(HomeCategorie.this, Produts.class);
                intent.putExtra("categorie_id", categorieId);

                // Démarrer la nouvelle activité
                startActivity(intent);
            }
        });
    }
}

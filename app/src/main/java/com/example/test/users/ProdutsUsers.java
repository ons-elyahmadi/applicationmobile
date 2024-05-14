package com.example.test.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Adapter.MyAdapterUser;
import com.example.test.R;
import com.example.test.models.ProductClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProdutsUsers  extends AppCompatActivity {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<ProductClass> dataList;
    MyAdapterUser adapter;
    SearchView searchView;
    String categorieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produts_users);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProdutsUsers.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder( ProdutsUsers.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        dataList = new ArrayList<>();
        adapter = new MyAdapterUser( ProdutsUsers.this, dataList);
        recyclerView.setAdapter(adapter);

        // Récupérer l'ID de la catégorie sélectionnée depuis l'intent
        categorieId = getIntent().getStringExtra("categorie_id");

        databaseReference = FirebaseDatabase.getInstance().getReference("PRODUCTS");

        // Requête pour récupérer les produits de la catégorie spécifiée
        Query query = databaseReference.orderByChild("dataLang").equalTo(categorieId);

        dialog.show();
        eventListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    ProductClass productClass = itemSnapshot.getValue(ProductClass.class);
                    productClass.setKey(itemSnapshot.getKey());
                    dataList.add(productClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });


    }

    public void searchList(String text){
        ArrayList<ProductClass> searchList = new ArrayList<>();
        for (ProductClass productClass : dataList){
            if (productClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(productClass);
            }
        }
        adapter.searchDataList(searchList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventListener != null && databaseReference != null) {
            databaseReference.removeEventListener(eventListener);
        }
    }
}

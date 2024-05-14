package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.models.Categorie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterCategorie extends BaseAdapter {
    private ArrayList<Categorie> dataList;
    private Context context;
    LayoutInflater layoutInflater;

    public AdapterCategorie(Context context, ArrayList<Categorie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }
        ImageView gridImage = view.findViewById(R.id.gridImage);
        TextView gridCaption = view.findViewById(R.id.gridCaption);
        Glide.with(context).load(dataList.get(i).getImageURL()).into(gridImage);
        gridCaption.setText(dataList.get(i).getCaption());
        return view;
    }

    public void deleteCategoryByName(final String categoryName) {
        Iterator<Categorie> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            final Categorie categorie = iterator.next();
            if (categorie.getCaption().equals(categoryName)) {
                iterator.remove();
                // Supprimer la catégorie de Firebase
                DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference("Categories").child(categorie.getCaption());
                categoryRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataSetChanged();
                            Toast.makeText(context, "Catégorie " + categoryName + " supprimée avec succès", Toast.LENGTH_SHORT).show();
                        } else {
                            // Gérer les erreurs
                            Toast.makeText(context, "Échec de la suppression de la catégorie " + categoryName, Toast.LENGTH_SHORT).show();
                            // Réajouter la catégorie si la suppression a échoué pour maintenir la cohérence avec Firebase
                            dataList.add(categorie);
                            notifyDataSetChanged();
                        }
                    }
                });
                break;
            }
        }
    }

}

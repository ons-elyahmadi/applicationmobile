package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.R;
import com.example.test.models.ProductClass;
import com.example.test.users.DetailsProduct;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterUser extends RecyclerView.Adapter<MyViewHolderUser> {

    private Context context;
    private List<ProductClass> dataList;

    public  MyAdapterUser(Context context, List<ProductClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUser holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        holder.recLang.setText(dataList.get(position).getDataLang());
        double price = dataList.get(position).getDataPrice();
        String formattedPrice = String.format("%.2f", price); // Format avec deux décimales
        holder.recPrice.setText(formattedPrice);

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsProduct.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Language", dataList.get(holder.getAdapterPosition()).getDataLang());
                intent.putExtra("Price", dataList.get(holder.getAdapterPosition()).getDataPrice());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<ProductClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolderUser extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle, recDesc, recLang, recPrice;
    CardView recCard;

    public MyViewHolderUser(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
        recPrice = itemView.findViewById(R.id.recPrice);
    }
}

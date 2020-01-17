package com.example.aplicacindeprueba.promociones;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacindeprueba.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPromociones extends RecyclerView.Adapter{

    private Context context;
    private ArrayList<Pojo> list;

    public AdapterPromociones(Context context, ArrayList<Pojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_capital,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Pojo pojo = list.get(position);
        Holder h=(Holder) holder;
        h.txt.setText(pojo.getName());

        if (!pojo.getUrl().equals("")){
            Picasso.get().load(pojo.getUrl()).into(h.imageView);
        }


        h.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Info.class);
                intent.putExtra("img", pojo.getUrl());
                intent.putExtra("titulo",pojo.getName());
                intent.putExtra("desc",pojo.getDesc());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgItem);
            txt=itemView.findViewById(R.id.txtItem);
        }
    }
}
package com.example.aplicacindeprueba.promociones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aplicacindeprueba.R;
import com.example.aplicacindeprueba.mapas.Map;

import java.util.ArrayList;

public class Promociones extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
RecyclerView.LayoutManager  layoutManager;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        recyclerView=findViewById(R.id.rcvPromociones);
        toolbar=findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);



        layoutManager= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterPromociones(this,Promo());
        recyclerView.setAdapter(adapter);

    }


    private ArrayList<Pojo> Promo(){
        ArrayList <Pojo> prom=new ArrayList<>();

        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197245/app/imagen_pizza.png","Papa John`s","Ofertas válidas por un tiempo limitado en todos los Papa John’s participantes. Los precios pueden variar. No válidas con otros cupones o descuentos. Algunas ofertas requieren la compra de múltiples pizzas. Algunas ofertas pueden estar disponibles únicamente en línea. No extra queso ni triple ingredientes. Algunos ingredientes pueden ser excluidos de las promociones o requerir un cargo adicional. Ingredientes adicionales limitados para asegurar calidad de horneado. Área de entrega limitada. Cobro por entrega puede aplicar y no ser parte del descuento. Compra mínima puede ser requerida para entrega. El cliente es responsable de pagar los impuestos aplicables."));
        prom.add(new Pojo("","Idea interior",""));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197237/app/promo_burguer_king.png","BurgerKing","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197239/app/promo_benavides.png","Farmacia Benavides","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197236/app/promoTizoncito.png","El tizoncito","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197253/app/promo_chilis.png","Chili´s","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197239/app/promo_zona_fitness.png","Zona Fitness","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197236/app/promo_cinepolis.png","Cinepolis","Descripccion"));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197238/app/promo_wingstop.png","imagen",""));
        prom.add(new Pojo("https://res.cloudinary.com/do6zmqghb/image/upload/v1579197239/app/promoItaliannis.png","imagen",""));

        return prom;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();

            if (id==R.id.itemMapa){
                Intent intent1=new Intent(this, Map.class);
                startActivity(intent1);
            }



        return true;
    }
}

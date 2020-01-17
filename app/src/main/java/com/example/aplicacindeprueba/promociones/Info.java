package com.example.aplicacindeprueba.promociones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicacindeprueba.R;
import com.squareup.picasso.Picasso;

public class Info extends AppCompatActivity implements View.OnClickListener {

    TextView title,description;
    ImageView img;
    ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        title=findViewById(R.id.tituloInfo);
        description=findViewById(R.id.descInfo);
        img=findViewById(R.id.imgInfo);
        atras=findViewById(R.id.iconoAtras);
        atras.setOnClickListener(this);

        Bundle datos = this.getIntent().getExtras();
        String url=datos.getString("img");
        String titulo=datos.getString("titulo");
        String desc=datos.getString("desc");

        if (!url.equals("")){  Picasso.get().load(url).into(img);}


        title.setText(titulo);
        description.setText(desc);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iconoAtras:
                Intent intent=new Intent(this,Promociones.class);
                startActivity(intent);
                break;

        }
    }
}

package com.example.aplicacindeprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aplicacindeprueba.io.ApiAdapter;
import com.example.aplicacindeprueba.io.model.UserRequest;
import com.example.aplicacindeprueba.io.response.LogingResponse;
import com.example.aplicacindeprueba.login.Login;
import com.example.aplicacindeprueba.promociones.Promociones;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread inicio= new Thread(){
          public void run(){
              try {
                  sleep(5000);
                  SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
                  String prefEmail=preferences.getString("email","vacoio");
                  String prefPass=preferences.getString("pass","vacoio");
                  if (!prefEmail.equals("vacoio") && !prefPass.equals("vacoio")){
                     obtener(prefEmail,prefPass);
                  }else {
                      Intent i=new Intent(getApplicationContext(), Login.class);
                      startActivity(i);
                  }



              }catch (Exception e){
                  Log.e("Exception",e.toString());
              }
          }
        };

        inicio.start();
    }

    public void obtener(final String email, final String pass){
        UserRequest userRequest=new UserRequest(email,pass);
        Call<LogingResponse> call= ApiAdapter.getApiService().login(userRequest);
        call.enqueue(new Callback<LogingResponse>() {
            @Override
            public void onResponse(Call<LogingResponse> call, Response<LogingResponse> response) {
                if (response.isSuccessful()){

                    SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("email",email);
                    editor.putString("pass",pass);
                    editor.commit();

                    Intent intent=new Intent(Splash.this, Promociones.class);
                    startActivity(intent);
                }else{
                    Intent i=new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<LogingResponse> call, Throwable t) {
                Log.e("Error",String.valueOf(t));
            }
        });

    }

}

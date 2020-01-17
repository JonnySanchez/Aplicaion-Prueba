package com.example.aplicacindeprueba.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacindeprueba.QR;
import com.example.aplicacindeprueba.R;
import com.example.aplicacindeprueba.io.ApiAdapter;
import com.example.aplicacindeprueba.io.model.UserRequest;
import com.example.aplicacindeprueba.io.response.LogingResponse;
import com.example.aplicacindeprueba.mapas.Map;
import com.example.aplicacindeprueba.promociones.Promociones;
import com.example.aplicacindeprueba.scaner.ScanerQR;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements View.OnClickListener {
Button btnQR;
Button btnIngresar;
Button btnRegistrarte;
EditText email,pass;


    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        btnQR=findViewById(R.id.btn_qr);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnRegistrarte=findViewById(R.id.btnRegistrarte);
        email=findViewById(R.id.edtEmail);
        pass=findViewById(R.id.edtPass);

        btnQR.setOnClickListener(this);
        btnIngresar.setOnClickListener(this);
        btnRegistrarte.setOnClickListener(this);
        String pwd="";
        try {
            Bundle datos = this.getIntent().getExtras();
             pwd=datos.getString("pass");
        }catch (Exception e){

        }

        if (!pwd.equals(null)){
            pass.setText(pwd);
        }


    }


   public void obtener(final String email, final String pass){
       UserRequest userRequest=new UserRequest(email,pass);
       Call<LogingResponse> call=ApiAdapter.getApiService().login(userRequest);
       call.enqueue(new Callback<LogingResponse>() {
           @Override
           public void onResponse(Call<LogingResponse> call, Response<LogingResponse> response) {
               if (response.isSuccessful()){

                   SharedPreferences preferences=getSharedPreferences("login", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor=preferences.edit();
                   editor.putString("email",email);
                   editor.putString("pass",pass);
                   editor.commit();

                   Intent intent=new Intent(Login.this,Promociones.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(Login.this,"Usuario o contraseñla invalido",Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<LogingResponse> call, Throwable t) {
               Log.e("Error",String.valueOf(t));
           }
       });

   }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_qr:
                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT > 22) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA))
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                }
                Intent qr= new Intent(this, ScanerQR.class);
                startActivity(qr);
                break;
            case R.id.btnIngresar:
                iniciarSesion(email.getText().toString(),pass.getText().toString());

                break;

            case R.id.btnRegistrarte:

                    break;
        }

    }


    public void iniciarSesion(String email,String pass){
        boolean validar=validarEmail(email);
        if (validar==true && !pass.equals(null)){
                obtener(email,pass);
        }else {
            Toast.makeText(this,"Usuario o contraseñla invalido",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Log.e("email", String.valueOf(pattern.matcher(email).matches()));
        return pattern.matcher(email).matches();

    }

}

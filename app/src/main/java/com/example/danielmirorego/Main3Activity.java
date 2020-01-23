package com.example.danielmirorego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private TextView txtUrl, txtTlfn;
    private Button btnAtras, btnFinApp;
    private static final int LLAMADA_TELEFONO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtUrl = findViewById(R.id.txtUrl);
        txtTlfn = findViewById(R.id.txtTlfn);
        btnAtras = findViewById(R.id.btnAtras);
        btnFinApp = findViewById(R.id.btnFinApp);

        txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ingemecanica.com/tutoriales/areas.html"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                } else {
                    Toast.makeText(Main3Activity.this,"No se pudo abrir el enlace", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtTlfn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: (+34)986110011"));
                        startActivity(i);
                    } else {
                        requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                    }
                } else {
                    i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: (+34)986110011"));
                    startActivity(i);
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFinApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LLAMADA_TELEFONO && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Permiso concedido
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: (+34)986110011"));
            startActivity(i);
        } else {
            Toast.makeText(Main3Activity.this, "No se ha podido realizar la llamada", Toast.LENGTH_SHORT).show();
        }
    }
}

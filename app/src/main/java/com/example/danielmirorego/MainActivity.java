package com.example.danielmirorego;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnInicio, btnFin;
    private TextView txtBienvenido, txtResult;
    private static final int CODIGO_LLAMADA = 1, CODIGO_LLAMADA_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicio = findViewById(R.id.btnInicio);
        btnFin = findViewById(R.id.btnFin);
        txtBienvenido = findViewById(R.id.txtBienvenido);
        txtResult = findViewById(R.id.txtResult);

        btnInicio.setOnClickListener(escuchador);

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtBienvenido.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (txtBienvenido.getText().toString().equals(getString(R.string.bienvenido))) {
                    Intent i = new Intent(MainActivity.this, Main3Activity.class);
                    startActivityForResult(i, CODIGO_LLAMADA_2);
                    return false;
                }
                return false;
            }
        });

    }

    private View.OnClickListener escuchador = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(i, CODIGO_LLAMADA);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA && resultCode == RESULT_OK){
            float result = data.getExtras().getFloat("resultado");
            String figura = data.getExtras().getString("figura");
            String opcion = data.getExtras().getString("opcion");
            txtBienvenido.setText(figura + " - " + opcion);
            txtResult.setVisibility(View.VISIBLE);
            txtResult.setText("" + result);
        } else if (requestCode == CODIGO_LLAMADA_2 && resultCode == RESULT_OK){
            finish();
        }
    }
}

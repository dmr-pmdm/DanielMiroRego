package com.example.danielmirorego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private RadioGroup rgOpcion, rgFigura;
    private RadioButton rbArea, rbPeri, rbCuadrado, rbCirculo;
    private Button btnContinua, btnResult;
    private EditText edtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNum = findViewById(R.id.edtNum);
        rgOpcion = findViewById(R.id.rgOpcion);
        rgFigura = findViewById(R.id.rgFigura);
        rbArea = findViewById(R.id.rbArea);
        rbPeri = findViewById(R.id.rbPeri);
        rbCuadrado = findViewById(R.id.rbCuadrado);
        rbCirculo = findViewById(R.id.rbCirculo);
        btnContinua = findViewById(R.id.btnContinua);
        btnResult = findViewById(R.id.btnResult);

        rbCuadrado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hint;
                if (isChecked) {
                    hint = getString(R.string.lado);
                } else {
                    hint = getString(R.string.radio);
                }
                edtNum.setHint(hint);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNum.getText().toString().isEmpty()) {
                    String figura = "", opcion = "";
                    float num = Float.parseFloat(edtNum.getText().toString());
                    float result = 0;
                    if (rbArea.isChecked() && rbCuadrado.isChecked()){
                        result = num*num;
                        figura = getString(R.string.cuadrado);
                        opcion = getString(R.string.area);
                    }else if (rbPeri.isChecked() && rbCuadrado.isChecked()){
                        result = 4*num;
                        figura = getString(R.string.cuadrado);
                        opcion = getString(R.string.perimetro);
                    }else if (rbArea.isChecked() && rbCirculo.isChecked()){
                        result = (float) (3.1416*(num*num));
                        figura = getString(R.string.circulo);
                        opcion = getString(R.string.area);
                    }else if (rbPeri.isChecked() && rbCirculo.isChecked()){
                        result = (float) (2*3.1416*num);
                        figura = getString(R.string.circulo);
                        opcion = getString(R.string.opcion);
                    }
                    Bundle b = new Bundle();
                    b.putFloat("resultado", result);
                    b.putString("figura", figura);
                    b.putString("opcion", opcion);
                    Intent i = new Intent();
                    i.putExtras(b);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(Main2Activity.this, getString(R.string.aviso), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickContinua(View v) {
        String hint = "";
        btnContinua.setVisibility(View.GONE);
        edtNum.setVisibility(View.VISIBLE);
        btnResult.setVisibility(View.VISIBLE);
        if (rbCuadrado.isChecked()){
            hint = getString(R.string.lado);
        } else {
            hint = getString(R.string.radio);
        }
        edtNum.setHint(hint);
    }

}

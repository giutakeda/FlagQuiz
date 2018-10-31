package com.example.giuliano.flaquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tv_resultado = (TextView)findViewById(R.id.resultado);

        Intent intent = getIntent();

        int acerto = intent.getIntExtra("acerto", 0);
        int total = intent.getIntExtra("total", 0);

        String resultado = intent.getStringExtra("nome") + ", o seu acerto foi de " + acerto + " / " + total + "%";

        tv_resultado.setText(resultado);


    }

    public void reiniciar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

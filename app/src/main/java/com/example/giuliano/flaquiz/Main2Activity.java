package com.example.giuliano.flaquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.giuliano.flaquiz.MainActivity.getResourceId;

public class Main2Activity extends AppCompatActivity {

    ImageView imagePais;
    Button btn1, btn2, btn3, btn4;
    TextView tv_resultado, tv_erros;

    // Lista de todos os paises
    List listaPaises = new ArrayList();
    List listaResource = new ArrayList();

    // Lista de paises ja excluindo os respondidos
    List listaPaises2 = new ArrayList();
    List listaResource2 = new ArrayList();

    List listaRespondida = new ArrayList();

    private String nome;
    private int resposta;
    private int total = 0;
    private int erros = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");


        tv_resultado = (TextView) findViewById(R.id.resultado);
        tv_erros = (TextView) findViewById(R.id.textView_erros);
        btn1 = (Button) findViewById(R.id.button_opt1);
        btn2 = (Button) findViewById(R.id.button_opt2);
        btn3 = (Button) findViewById(R.id.button_opt3);
        btn4 = (Button) findViewById(R.id.button_opt4);

        AdminSQLiteOpenHelper sqlite = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = sqlite.getWritableDatabase();

        Cursor cursor = db.rawQuery("select path, pais, codPais from paises ", null);

        int i = 0;

        while (cursor.moveToNext()) {
            listaResource.add(i, cursor.getString(0));
            listaPaises.add(i, cursor.getString(1));

            listaResource2.add(i, cursor.getString(0));
            listaPaises2.add(i, cursor.getString(1));
            i++;
        }
        db.close();
        startGame();
    }

    private void startGame(){

        if(listaPaises2.size() < 5){
            Toast.makeText(this, listaPaises2.size(), Toast.LENGTH_LONG).show();
        }

        if(listaPaises2.size() == 0){
            Intent intent = new Intent(this, ResultadoActivity.class);
            intent.putExtra("nome", nome);
            intent.putExtra("acerto", total);
            intent.putExtra("total", listaPaises.size());
            startActivity(intent);
        }

        tv_resultado.setText(nome + ": " + total + "/" + listaPaises.size());
        tv_erros.setText("Erros: "+ erros);

        List listaRespostas = new ArrayList();
        List listaOrdenacao = Arrays.asList("", "", "", "");

        imagePais = (ImageView) findViewById(R.id.iv_pais);

        Random random = new Random();
        boolean findFlg = false;
        int n;
        String resource = "";
        while (findFlg == false) {
            n = random.nextInt(listaResource2.size() - 1);
            if (!listaRespondida.contains(listaPaises2.get(n))) {
                resource = (String) listaResource2.get(n);

                listaRespostas.add(listaPaises2.get(n));
                listaRespondida.add(listaPaises2.get(n));

                listaResource2.remove(n);
                listaPaises2.remove(n);
                findFlg = true;
            }
        }

        // Obter as respostas
        boolean endFlg = false;
        while (endFlg == false) {
            int m = random.nextInt(listaResource.size() - 1);
            if (!listaRespostas.contains(listaPaises.get(m))) {
                listaRespostas.add(listaPaises.get(m));
            }
            if (listaRespostas.size() == 4) {
                endFlg = true;
            }
        }

        endFlg = false;
        int i = 0;
        while (endFlg == false) {
            int m = random.nextInt(4);
            if (listaOrdenacao.get(m).equals("")) {
                listaOrdenacao.set(m, listaRespostas.get(i).toString());
                if (i == 0) {
                    resposta = m + 1;
                }
                i++;
            }
            if (i == 4) {
                endFlg = true;
            }
        }

        btn1.setText(listaOrdenacao.get(0).toString());
        btn2.setText(listaOrdenacao.get(1).toString());
        btn3.setText(listaOrdenacao.get(2).toString());
        btn4.setText(listaOrdenacao.get(3).toString());

        int resourceId = getResourceId(this, resource, "drawable", getPackageName());
        imagePais.setImageResource(resourceId);
    }

    public void btn1(View v) {
        if (resposta == 1) {
            Toast.makeText(this, "Acertou!!", Toast.LENGTH_SHORT).show();
            total++;
        } else {
            Toast.makeText(this, "Errou!!", Toast.LENGTH_SHORT).show();
            erros++;
        }
        startGame();
    }

    public void btn2(View v) {
        if (resposta == 2) {
            Toast.makeText(this, "Acertou!!", Toast.LENGTH_SHORT).show();
            total++;
        } else {
            Toast.makeText(this, "Errou!!", Toast.LENGTH_SHORT).show();
            erros++;
        }
        startGame();
    }

    public void btn3(View v) {
        if (resposta == 3) {
            Toast.makeText(this, "Acertou!!", Toast.LENGTH_SHORT).show();
            total++;
        } else {
            Toast.makeText(this, "Errou!!", Toast.LENGTH_SHORT).show();
            erros++;
        }

        startGame();
    }

    public void btn4(View v) {
        if (resposta == 4) {
            Toast.makeText(this, "Acertou!!", Toast.LENGTH_SHORT).show();
            total++;
        } else {
            Toast.makeText(this, "Errou!!", Toast.LENGTH_SHORT).show();
            erros++;
        }
        startGame();
    }
}

package com.example.cesmac.marlonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaConsulta extends AppCompatActivity implements View.OnClickListener{

    public Button consultarBt;
    public Trabalho t;
    public RepositorioTrabalho rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        consultarBt = (Button)findViewById(R.id.botao_consultar_trabalho);
        consultarBt.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.botao_consultar_trabalho:
                EditText campo_search = (EditText)findViewById(R.id.search_text);

                String search = campo_search.getText().toString();
                rt = new RepositorioTrabalho(this);
                rt.buscarTrabalhoPorTitulo(search);
            break;
        }
    }
}

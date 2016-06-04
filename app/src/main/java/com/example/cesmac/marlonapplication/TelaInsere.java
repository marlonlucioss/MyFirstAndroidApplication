package com.example.cesmac.marlonapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaInsere extends AppCompatActivity implements View.OnClickListener{

    public Button insereBt;
    public Trabalho t;
    public RepositorioTrabalho rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inserir);

        insereBt = (Button)findViewById(R.id.botao_inserir_trabalho);
        insereBt.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.botao_inserir_trabalho:
                EditText campo_titulo = (EditText)findViewById(R.id.campo_titulo);
                EditText campo_disciplina = (EditText)findViewById(R.id.campo_disciplina);
                EditText campo_dificuldade = (EditText)findViewById(R.id.campo_dificuldade);

                String titulo = campo_titulo.getText().toString();
                String disciplina = campo_disciplina.getText().toString();
                String dificuldade = campo_dificuldade.getText().toString();

                t = new Trabalho();
                t.titulo = titulo;
                t.disciplina = disciplina;
                t.dificuldade = dificuldade;

                rt = new RepositorioTrabalho(this);
                rt.inserir(t);

                Intent chamaTelaInicial = new Intent(this,TelaInicial.class);
                startActivity(chamaTelaInicial);
            break;
        }
    }


}

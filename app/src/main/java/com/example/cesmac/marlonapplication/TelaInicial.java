package com.example.cesmac.marlonapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener{

    public Button inserirBt;
    public Button consultarBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inserirBt = (Button)findViewById(R.id.botao_inserir);
        inserirBt.setOnClickListener(this);

        consultarBt = (Button)findViewById(R.id.botao_consultar);
        consultarBt.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.botao_inserir:
                Intent chamaTelaInserir = new Intent(this,TelaInsere.class);
                startActivity(chamaTelaInserir);
            break;
            case R.id.botao_consultar:
                Intent chamaTelaConsultar = new Intent(this,TelaConsulta.class);
                startActivity(chamaTelaConsultar);
            break;
        }
    }
}

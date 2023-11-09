package com.example.ejemploclase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.ejemploclase.R;

public class MainActivity extends AppCompatActivity {

    Button botonListav1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonListav1 = (Button) this.findViewById(R.id.ListaCompraV1);

        botonListav1.setOnClickListener(view -> abrirEjemploListav1());
    }

    private void abrirEjemploListav1() {
        Intent intent = new Intent(this,ListaCompraActivity.class);
        this.startActivity(intent);
    }
}
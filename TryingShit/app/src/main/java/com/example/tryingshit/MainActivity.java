package com.example.tryingshit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton1 = (Button) findViewById(R.id.saludar);
        boton1.setOnClickListener(view -> escribe());

    }

    private void escribe(){
        TextView nuevo = (TextView) findViewById(R.id.entradaTexto);
        TextView salida = (TextView) findViewById(R.id.salidaTexto);
        salida.setText(nuevo.getText());
    }
}
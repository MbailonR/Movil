package com.example.tutorial;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeText(this,"Creando", Toast.LENGTH_SHORT).show();

    }

    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        makeText(this,"Resumiendo", Toast.LENGTH_SHORT).show();
    }

    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_main);
        makeText(this,"Pausando", Toast.LENGTH_SHORT).show();
    }
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        makeText(this,"Empezando(Start)", Toast.LENGTH_SHORT).show();
    }
    protected void onStop() {
        super.onStop();
        setContentView(R.layout.activity_main);
        makeText(this,"Parando", Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.activity_main);
        makeText(this,"Destruyendo", Toast.LENGTH_SHORT).show();
    }
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        makeText(this,"Reempezando", Toast.LENGTH_SHORT).show();
    }
}
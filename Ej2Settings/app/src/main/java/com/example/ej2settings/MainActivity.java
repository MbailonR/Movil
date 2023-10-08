package com.example.ej2settings;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private DatosPersonales datosPersonales;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        //Creao EditTexts para guardar los datos de escritura que me entren
            final EditText edNombre = (EditText)  this.findViewById(R.id.entradaNombre);
            final EditText edEmail = (EditText) this.findViewById(R.id.entradsEmail);
            final EditText edEdad = (EditText) this.findViewById(R.id.entradaEdad);
            final EditText edPubli = (EditText) this.findViewById(R.id.entradaPubli);


            //Les añado un Listener
            edNombre.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                //Este es el unico que me interesa en este caso porque solo quiero guardar lo que está escrito
                @Override
                public void afterTextChanged(Editable editable) {
                    MainActivity.this.datosPersonales.setNombre( edNombre.getText().toString() );
                }
            });
            edEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    MainActivity.this.datosPersonales.setEmail(edEmail.getText().toString());
                }
            });


            edEdad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    MainActivity.this.datosPersonales.setEdad(edEdad.getText().toString());
                }
            });


            edPubli.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    MainActivity.this.datosPersonales.setPubli(edPubli.getText().toString());
                }
            });

        }
        @Override
        protected void onResume() {
            super.onResume();
            //Instancio el SharedPrederences
            SharedPreferences prefs = this.getPreferences(MODE_PRIVATE);

            //??¿¿
            String nombre = prefs.getString( "nombre", "" );
            String mail = prefs.getString( "email", "" );
            String edad = prefs.getString( "edad", "" );
            String publi = prefs.getString("publi", "");
            this.datosPersonales = new DatosPersonales( nombre, mail, edad, publi );

            //Vuelvo a crear y settear los datos para que cuando vuelva el usuario sigan estando ahí
            final EditText edNombre = (EditText) this.findViewById( R.id.entradaNombre );
            final EditText edEmail = (EditText) this.findViewById( R.id.entradsEmail);
            final EditText edEdad = (EditText) this.findViewById( R.id.entradaEdad );
            final EditText edPubli = (EditText) this.findViewById( R.id.entradaPubli );

            edNombre.setText( this.datosPersonales.getNombre() );
            edEdad.setText( this.datosPersonales.getEdad() );
            edEmail.setText( this.datosPersonales.getEmail() );
            edPubli.setText(this.datosPersonales.getPubli());
    }



        @Override
        protected void onPause() {
            super.onPause();
            SharedPreferences.Editor edit = this.getPreferences(MODE_PRIVATE).edit();
            edit.putString( "info", this.datosPersonales.toString() );
            edit.apply();

        }



    }
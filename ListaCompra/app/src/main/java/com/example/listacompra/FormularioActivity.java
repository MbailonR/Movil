package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listacompra.DB.DBHelper;
import com.example.listacompra.Item.Producto;

import java.io.Serializable;
import java.util.Objects;

public class FormularioActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText edNombre =(EditText) this.findViewById(R.id.nombreP);
        final EditText edCantidad = (EditText) this.findViewById(R.id.Cantidad);
        final EditText edPrecio = (EditText) this.findViewById(R.id.Precio);


        final Button btMeter = (Button) this.findViewById(R.id.btMeter);
        final Button btCancelar = (Button) this.findViewById(R.id.btCancelar);

        Intent datosEnviados = this.getIntent();

        edNombre.setText(Objects.requireNonNull(datosEnviados.getExtras()).getString(("Nombre")));
        try{
            edPrecio.setText(Double.toString(datosEnviados.getExtras().getDouble("Precio")));
        }catch(Exception ex){
            Toast nuevo = new Toast(this.getApplicationContext());
            nuevo.setText("Error de conversion");
            nuevo.show();
        }
        edCantidad.setText(Integer.toString(datosEnviados.getExtras().getInt("Cantidad")));


        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormularioActivity.this.setResult(Activity.RESULT_CANCELED);
                FormularioActivity.this.finish();
            }
        });


        btMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DBHelper dbh= new DBHelper(FormularioActivity.this);
                    Intent datosDevuelta = new Intent();
                    datosDevuelta.putExtra("Nombre", edNombre.getText().toString());
                    datosDevuelta.putExtra("Cantidad", edCantidad.getText().toString());
                    datosDevuelta.putExtra("Precio", edPrecio.getText().toString());
                    datosDevuelta.putExtra("pos", FormularioActivity.this.getIntent().getExtras().getInt("pos"));
                    dbh.addProducto(edNombre.getText().toString().trim(),edCantidad.getText().toString().trim(),edPrecio.getText().toString().trim());
                    FormularioActivity.this.setResult(Activity.RESULT_OK, datosDevuelta); //Envías el intent de vuelta
                    FormularioActivity.this.finish();

                } catch (Exception ex) {
                    Toast.makeText(FormularioActivity.this, "AWWWWSHIT", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btMeter.setEnabled(false);

        edNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btMeter.setEnabled(edNombre.getText().toString().trim().length() > 0);
            }
        });


        edCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    Integer.parseInt(edCantidad.getText().toString());
                    btMeter.setEnabled(true);
                }catch(Exception ex){
                    System.err.println("fail");
                }
            }
        });


        edPrecio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    Double.parseDouble(edPrecio.getText().toString());
                    btMeter.setEnabled(true);
                }catch(Exception ex){
                    System.err.println("f");
                }
            }
        });

    }
}
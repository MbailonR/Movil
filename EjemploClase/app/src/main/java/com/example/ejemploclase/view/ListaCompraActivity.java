package com.example.ejemploclase.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejemploclase.R;
import com.example.ejemploclase.model.Item;

import java.util.ArrayList;

public class ListaCompraActivity extends AppCompatActivity {


    protected static  final int CODIGOADICIONITE = 10;
    protected static final int CODIGO_SALIDA = 100;
    private ArrayAdapter<Item> adaptadorItem;
    private ArrayList<Item> lista;
    @Override
    protected void onCreate(Bundle onSavedIntance){
        super.onCreate(onSavedIntance);
        setContentView(R.layout.activity_lista_compra_v1);
        ListView listview = (ListView) this.findViewById(R.id.lvLista);
        Button botonInsertar = (Button) this.findViewById(R.id.btInserta);
        this.lista = new ArrayList();
        this.adaptadorItem = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, this.lista);
        listview.setAdapter(adaptadorItem);

        botonInsertar.setOnClickListener(view -> onAdd());
        listview.setOnLongClickListener(view -> onEdit());
    }

    private boolean onEdit(){
        return true;
    }
    private void onAdd(){
        Intent subActividad = new Intent(this,ItemEditionActivity.class);
        subActividad.putExtra("nombre","");
        subActividad.putExtra("cantidad",1);
        startActivityForResult(subActividad,CODIGOADICIONITE);
    }
    protected void onActivity(){
        
    }



}

package com.example.listacompra;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.listacompra.DB.DBHelper;
import com.example.listacompra.Item.Producto;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends Activity implements Serializable {
    /**
     * Called when the activity is first created.
     */

    public ArrayAdapter<Producto> itemsAdapter;

    public final int CODIGO_ENTRADA= 10;

    public DBHelper myDB;

    ArrayList<String> pNombre, pCantidad, pPrecio;

    public ArrayList<Producto> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        this.items = new ArrayList<Producto>();
        myDB = new DBHelper(MainActivity.this);


        Button btAdd = (Button) this.findViewById( R.id.btAdd );
        ListView lvItems = (ListView) this.findViewById( R.id.lvItems );

        lvItems.setLongClickable( true );

        this.itemsAdapter = new ArrayAdapter<Producto>(
                this.getApplicationContext(),
                android.R.layout.simple_selectable_list_item,
                this.items
        );
        lvItems.setAdapter( this.itemsAdapter );
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if ( pos >= 0 ) {
                    MainActivity.this.updateStatusEliminar(MainActivity.this.items.get(pos));
                    MainActivity.this.items.remove( pos );
                    MainActivity.this.itemsAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto x = (Producto) adapterView.getItemAtPosition(i);
                edit(x, i);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onAdd();
            }
        });

    }








    private void edit(Producto cambiar, int posicion) {
        Intent intent = new Intent(this, FormularioActivity.class);
        intent.putExtra("Nombre",cambiar.getNombre());
        intent.putExtra("Cantidad",cambiar.getCantidad());
        intent.putExtra("Precio",cambiar.getPrecio());
        intent.putExtra("pos",posicion);
        MainActivity.this.itemsAdapter.remove(cambiar);
        MainActivity.this.updateStatusEliminar(cambiar);
        startActivityForResult(intent, CODIGO_ENTRADA );
    }

    private void onAdd() {
        Intent intent = new Intent(this, FormularioActivity.class);
        intent.putExtra("Nombre","");
        intent.putExtra("Cantidad",1);
        intent.putExtra("Precio",0.0);
        startActivityForResult(intent, CODIGO_ENTRADA );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_ENTRADA && resultCode == Activity.RESULT_OK) {
                String nombre = data.getStringExtra("Nombre");
                String Cantidad = data.getStringExtra("Cantidad");
                String Precio = data.getStringExtra("Precio");
                Producto nuevo = new Producto(nombre, Integer.parseInt(Cantidad), Double.parseDouble(Precio));

                MainActivity.this.itemsAdapter.add(nuevo); //Necesario o esto no ase na
                MainActivity.this.updateStatus(nuevo); //Necesario para subir el contador
        }
    }

    private void updateStatus(Producto nuevo) {
        TextView txtNum = (TextView) this.findViewById( R.id.lblNum );
        txtNum.setText( Integer.toString( this.itemsAdapter.getCount() ) );

        TextView txtPrecio =  this.findViewById(R.id.lblPrecio);
        double nuevoNum = nuevo.getPrecio()*nuevo.getCantidad();
        double viejoNum = Double.parseDouble((String) txtPrecio.getText());
        double suma = nuevoNum + viejoNum;
        txtPrecio.setText(Double.toString(suma));
    }
    private void updateStatusEliminar(Producto producto) {
        TextView txtPrecio =  this.findViewById(R.id.lblPrecio);
        double nuevoNum = producto.getPrecio()*producto.getCantidad();
        double viejoNum = Double.parseDouble((String) txtPrecio.getText());
        double suma = nuevoNum - viejoNum;
        txtPrecio.setText(Double.toString(suma));
    }

}
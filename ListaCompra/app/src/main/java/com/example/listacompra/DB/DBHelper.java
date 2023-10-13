package com.example.listacompra.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.listacompra.Item.Producto;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    //Nombre y version, que le tengo que pasar al constructor
    private static final String DATABASE_NAME="Compras.db";
    private static final int DATABASE_VERSION=1;

    //Todas las columnas que va a tener mi db + el nombre de sus tablas
    private static final String TABLE_NAME="misProductos";
    private static final String COLUMN_NOMBRE="producto_nombre";
    private static final String COLUMN_CANTIDAD="producto_cantidad";
    private static final String COLUMN_PRECIO="producto_precio";


    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.beginTransaction();
            String query=
                    "CREATE TABLE "
                            + TABLE_NAME +
                            " ( "
                            + COLUMN_NOMBRE + " string(100) PRIMARY KEY NOT NULL, "
                            + COLUMN_CANTIDAD + " INTEGER, "
                            + COLUMN_PRECIO + " DOUBLE); ";
            db.execSQL(query);
            Toast.makeText(context, "La base de datos se ha creado", Toast.LENGTH_SHORT).show();
            db.setTransactionSuccessful();
        }catch(SQLException ex){
            Log.e("DBHelper.Create",ex.getMessage());
        }finally{
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try{
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.setTransactionSuccessful();
        }catch(SQLException ex){
            Log.e("DBManager.Upgrade",ex.getMessage());
        }finally {
            db.endTransaction();
        }
        onCreate(db);
    }

    public void addProducto(String nombre, String cantidad, String precio){
        SQLiteDatabase db = this.getWritableDatabase(); //primer paso para recupar la db
        ContentValues cv = new ContentValues(); //Este objeto guarda todos los valores de nuestra aplicacion

        cv.put(this.COLUMN_NOMBRE,nombre);
        cv.put(this.COLUMN_CANTIDAD,cantidad);
        cv.put(this.COLUMN_PRECIO,precio);

        //meto los valores a la db con insert. Pillo el resultado para hacer un if por si falla
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(this.context, "Ha fallado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Ha añadido un producto", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //Para asegurarse de que nuestra db NO esté vacía
        if(db != null){
            //El cursor guardará todos los datos de la db directamente
            cursor = db.rawQuery(query, null);
        }
        //Y devuelvo todos los datos. Este método lo voy a usar en MainActivity
        return cursor;
    }

}

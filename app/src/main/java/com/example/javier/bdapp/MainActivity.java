package com.example.javier.bdapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Asignatura> asignaturas = new ArrayList<>();
    private ArrayAdapter<Asignatura> adapter;
    private adminSQLiteOpenHelper admin;
    private EditText et1, et2, cantidadEstudiantes;
    private ListView lt1;
    private Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);
        lt1= (ListView) findViewById(R.id.lt1);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);


        cantidadEstudiantes = (EditText) findViewById(R.id.cantidadEstudiantes);
        asignaturas = new ArrayList<>();
        adapter = new ArrayAdapter<Asignatura>(this, android.R.layout.simple_expandable_list_item_1, asignaturas);
        lt1.setAdapter(adapter);
        admin = new adminSQLiteOpenHelper(this, "Administracion",null,1);
        showall();
    }

    public void add(View view){
        SQLiteDatabase bd = admin.getWritableDatabase();
        int codigo = Integer.parseInt(et1.getText().toString());
        int cantidadEstudiante = Integer.parseInt(et2.getText().toString());
        String nombre = et1.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo",codigo);
        registro.put("nombre",nombre);
        registro.put("cantidad", cantidadEstudiante);

        bd.insert("asignatura",null,registro);
        bd.close();
        Toast.makeText(this, "Asignatura agregada", Toast.LENGTH_LONG).show();
        showall();
    }

    public void del (View view){

        SQLiteDatabase bd = admin.getReadableDatabase();
        String codigo = et1.getText().toString();
        int cant = bd.delete("asignatura","codigo = "+codigo, null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Asignatura eliminada", Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this, "No exite la asignatura con el codigo" + codigo, Toast.LENGTH_SHORT).show();
        }

        showall();
    }

    public void showall (){
        String query = "select * from asignatura";
        SQLiteDatabase bd = admin.getReadableDatabase();
        asignaturas.clear();
        Cursor c = bd.rawQuery(query, null);

        while(c.moveToNext()){
            Asignatura a = new Asignatura();
            a.setCodigo(c.getInt(c.getColumnIndex("codigo")));
            a.setNombre(c.getString(c.getColumnIndex("nombre")));
            a.setCantidadEstudiantes(c.getInt(c.getColumnIndex("cantiadEstudiantes")));
            asignaturas.add(a);
        }

        adapter.notifyDataSetChanged();
    }
}

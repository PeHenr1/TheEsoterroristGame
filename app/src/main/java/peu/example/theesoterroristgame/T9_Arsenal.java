package peu.example.theesoterroristgame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class T9_Arsenal extends AppCompatActivity {
    private ListView listaArmas;

    Cursor cursorArmas;
    AdapterArmas adapterArmas;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t9_arsenal);
        listaArmas = findViewById( R.id.listaArmas );


        // banco de dados...
        bd = openOrCreateDatabase( "theesoterroristbd", MODE_PRIVATE, null );

        // configurando a lista...
        cursorArmas = bd.rawQuery( "SELECT id, nome, dano FROM armas", null );

        // criando o objeto adapter, passando o cursor
        adapterArmas = new AdapterArmas( this, cursorArmas );

        // associando o adapter a lista de artistas
        listaArmas.setAdapter(adapterArmas);





    }

    // CÓDIGO ANTERIOR COM CLASSE, SEM BDD
    /*
    private ArrayList <Arma> armas = new ArrayList<>();
    private AdapterArmas adapter;
    private ListView listaArmas;

    //Cursor cursorArmas;
    //AdapterArmas adapterArmas;
    //SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t9_arsenal);
        listaArmas = findViewById( R.id.listaArmas );


        Arma rifle = new Arma("Rifle", "12", null);
        Arma shotgun = new Arma("Shotgun", "24", null);
        Arma machado = new Arma("Machado", "15", null);
        Arma adaga = new Arma("Adaga", "13", null);
        Arma arcoflecha = new Arma("Arco e Flecha", "10", null);
        Arma glock = new Arma("Glock", "12", null);

        armas.add(rifle);
        armas.add(shotgun);
        armas.add(machado);
        armas.add(adaga);
        armas.add(arcoflecha);
        armas.add(glock);
        //adapter.notifyDataSetChanged();

        AdapterArmas adapter = new AdapterArmas(this, armas);
        // criando adaptador
        adapter = new AdapterArmas( this, armas );
        // associando o adaptador a lista
        listaArmas.setAdapter( adapter );

    }*/

}
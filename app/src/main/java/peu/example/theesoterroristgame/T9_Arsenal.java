package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class T9_Arsenal extends AppCompatActivity {
    private ListView listaArmas;

    Cursor cursor;
    AdapterArmas adapter;
    SQLiteDatabase bd;
    String nomeG;
    int idG = 0;
    int contG = 0;
    int danoNovo = 0;
    // max 5           →                     0  >  1  >  2  >  3   >  4  > 500
    int custo = 0;     // vai encarecendo → 500 > 750 > 900 > 1000 > 1500


    private View viewInfo;
    private ImageView lblArmaImg;
    private TextView lblNomeArma;
    private TextView textView100;
    private TextView textView101;
    private TextView lblDanoArma;
    private TextView lblDanoNovo;
    private Button btnVolt;
    private Button btnMelhorar;

    private TextView lblDinheiroA;
    private ArrayList<Arma> armas = new ArrayList<>();

    /*
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

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t9_arsenal);
        listaArmas = findViewById(R.id.listaArmas);
        lblDinheiroA = findViewById(R.id.lblDinheiroA);

        viewInfo = findViewById(R.id.viewInfo);
        lblArmaImg = findViewById(R.id.lblArmaImg);
        lblNomeArma = findViewById(R.id.lblNomeArma);
        textView100 = findViewById(R.id.textView100);
        textView101 = findViewById(R.id.textView101);
        lblDanoArma = findViewById(R.id.lblDanoArma);
        lblDanoNovo = findViewById(R.id.lblDanoNovo);
        btnVolt = findViewById(R.id.btnVolt);
        btnMelhorar = findViewById(R.id.btnMelhorar);

        btnVolt.setOnClickListener( new EscutadorVolt() );
        btnMelhorar.setOnClickListener( new EscutadorMelhorar() );

        bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
        String cmd;
        //cmd = "CREATE TABLE IF NOT EXISTS armas (nome VARCHAR, dano INTEGER)";
        //bd.execSQL( cmd );

        // configurando a lista...
        cursor = bd.rawQuery("SELECT _rowid_ _id, nome, dano, contM, custo FROM armas", null);

        // criando o objeto adapter, passando o cursor
        adapter = new AdapterArmas(this, cursor);

        // associando o adapter a lista de artistas
        listaArmas.setAdapter(adapter);

        listaArmas.setOnItemClickListener( new EscutadorCliqueComum() );

        //-----------------------------------
        // RECUPERA VALOR DINHEIRO
        int dindin;
        cmd = "SELECT dinheiro FROM usuario";

        Cursor dados = bd.rawQuery( cmd, null);
        while( dados.moveToNext()){
            dindin = dados.getInt( dados.getColumnIndex("dinheiro") );

            String money = String.valueOf(dindin);
            lblDinheiroA.setText( money );
        }
    }

    private class EscutadorCliqueComum implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            listaArmas.setVisibility(View.INVISIBLE);
            viewInfo.setVisibility(View.VISIBLE);
            lblArmaImg.setVisibility(View.VISIBLE);
            lblNomeArma.setVisibility(View.VISIBLE);
            textView100.setVisibility(View.VISIBLE);
            textView101.setVisibility(View.VISIBLE);
            lblDanoArma.setVisibility(View.VISIBLE);
            lblDanoNovo.setVisibility(View.VISIBLE);
            btnVolt.setVisibility(View.VISIBLE);
            btnMelhorar.setVisibility(View.VISIBLE);

            // recupera o cursor, posicionado na linha relativa ao item clicado
            // Cursor c = adapterArtistas.getCursor();
            Cursor c = (Cursor) adapter.getItem(i);
            String danoStr, danoNStr, custoStr;
            int dano;

            dano = ( c.getInt(c.getColumnIndex("dano")) );
            nomeG = ( c.getString(c.getColumnIndex("nome")));
            contG = ( c.getInt(c.getColumnIndex("contM")) );
            custo = ( c.getInt(c.getColumnIndex("custo")) );

            danoStr = String.valueOf(dano);
            custoStr = String.valueOf(custo);
            //RECUPERA ID E CONTM
            //String cmd;
            //cmd = "SELECT id, nome, contM FROM armas";
            //Cursor dados = bd.rawQuery( cmd, null);
            //while( dados.moveToNext()){
            //    nomeG = dados.getString( dados.getColumnIndex("nome") );
            //    if(nomeG.equals(nome)){
            //        idG = dados.getInt( dados.getColumnIndex("id") );
            //        contG = dados.getInt( dados.getColumnIndex("contM") );
            //     }
            // }

            danoNovo = dano + 3;
            danoNStr = String.valueOf(danoNovo);

            lblDanoArma.setText(danoStr);
            lblNomeArma.setText(nomeG);
            lblDanoNovo.setText(danoNStr);

            if(nomeG.equals("Rifle")){
                lblArmaImg.setImageResource(R.drawable.rifle);
            }
            if(nomeG.equals("Shotgun")){
                lblArmaImg.setImageResource(R.drawable.shotgun);
            }
            if(nomeG.equals("Machado")){
                lblArmaImg.setImageResource(R.drawable.machado);
            }
            if(nomeG.equals("Adaga")) {
                lblArmaImg.setImageResource(R.drawable.adaga);
            }
            if(nomeG.equals("ArcoFlecha")){
                lblArmaImg.setImageResource(R.drawable.arcoflecha);
            }
            if(nomeG.equals("Glock")){
                lblArmaImg.setImageResource(R.drawable.glock);
            }
            //Toast.makeText(getApplicationContext(), dano + " " + nome, Toast.LENGTH_SHORT).show();
        }
    }

    private class EscutadorVolt implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            listaArmas.setVisibility(View.VISIBLE);
            viewInfo.setVisibility(View.INVISIBLE);
            lblArmaImg.setVisibility(View.INVISIBLE);
            lblNomeArma.setVisibility(View.INVISIBLE);
            textView100.setVisibility(View.INVISIBLE);
            textView101.setVisibility(View.INVISIBLE);
            lblDanoArma.setVisibility(View.INVISIBLE);
            lblDanoNovo.setVisibility(View.INVISIBLE);
            btnVolt.setVisibility(View.INVISIBLE);
            btnMelhorar.setVisibility(View.INVISIBLE);
        }
    }

    private class EscutadorMelhorar implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            /*int custoMelhoria = 0;
            if(nomeG.equals("Rifle")){
                custoMelhoria = custoMelhoriaRifle;
            }
            if(nomeG.equals("Shotgun")){
                custoMelhoria = custoMelhoriaShotgun;
            }
            if(nomeG.equals("Machado")){
                custoMelhoria = custoMelhoriaMachado;
            }
            if(nomeG.equals("Adaga")) {
                custoMelhoria = custoMelhoriaAdaga;
            }
            if(nomeG.equals("ArcoFlecha")){
                custoMelhoria = custoMelhoriaArco;
            }
            if(nomeG.equals("Glock")){
                custoMelhoria = custoMelhoriaGlock;
            }*/
            AlertDialog.Builder dialogo = new AlertDialog.Builder( T9_Arsenal.this);
            dialogo.setMessage("Custo: R$"+custo+"\n*esse custo aumenta gradativamente*");
            dialogo.setPositiveButton( "Confirmar", new EscutadorSS() );
            dialogo.setNegativeButton( "Voltar", new EscutadorNN() );
            dialogo.setCancelable( false );
            dialogo.create();
            dialogo.show();

        }
    }

    private class EscutadorNN implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    private class EscutadorSS implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {


            bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
            String cmd;
            //RECUPERA DINHEIRO
            int dindin = 0;
            cmd = "SELECT dinheiro FROM usuario";
            Cursor dados = bd.rawQuery( cmd, null);
            while( dados.moveToNext()){
                dindin = dados.getInt( dados.getColumnIndex("dinheiro") );
            }

            if(nomeG.equals("Rifle")){
                if(contG == 0){
                    if(dindin >= custo){
                        dindin = dindin - custo;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        //String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+danoNovo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custo += 250;
                        Toast.makeText(getApplicationContext(), "Custo Novo: "+custo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET custo = " + custo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custo){
                        dindin = dindin - custo;

                        // checagem - ta pegando certo
                        //String din =  String.valueOf(dindin);
                        //Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        bd.execSQL( cmd );

                        //String yo =  String.valueOf(danoNovo);
                        //Toast.makeText(getApplicationContext(), "Dano Novo: "+danoNovo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        bd.execSQL( cmd );

                        custo += 150;
                        //Toast.makeText(getApplicationContext(), "Custo Novo: "+custo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET custo = " + custo + " WHERE id = " + idG;
                        bd.execSQL( cmd );
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custo){
                        dindin = dindin - custo;

                        // checagem - ta pegando certo
                        //String din =  String.valueOf(dindin);
                        //Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        bd.execSQL( cmd );

                        //String yo =  String.valueOf(danoNovo);
                        //Toast.makeText(getApplicationContext(), "Dano Novo: "+danoNovo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        bd.execSQL( cmd );

                        custo += 100;
                        //Toast.makeText(getApplicationContext(), "Custo Novo: "+custo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET custo = " + custo + " WHERE id = " + idG;
                        bd.execSQL( cmd );
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custo){
                        dindin = dindin - custo;

                        // checagem - ta pegando certo
                        //String din =  String.valueOf(dindin);
                        //Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        bd.execSQL( cmd );

                        //String yo =  String.valueOf(danoNovo);
                        //Toast.makeText(getApplicationContext(), "Dano Novo: "+danoNovo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        bd.execSQL( cmd );

                        custo += 500;
                        //Toast.makeText(getApplicationContext(), "Custo Novo: "+custo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET custo = " + custo + " WHERE id = " + idG;
                        bd.execSQL( cmd );
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custo){
                        dindin = dindin - custo;

                        // checagem - ta pegando certo
                        //String din =  String.valueOf(dindin);
                        //Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        bd.execSQL( cmd );

                        //String yo =  String.valueOf(danoNovo);
                        //Toast.makeText(getApplicationContext(), "Dano Novo: "+danoNovo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        bd.execSQL( cmd );

                        custo += 500;
                        //Toast.makeText(getApplicationContext(), "Custo Novo: "+custo, Toast.LENGTH_SHORT).show();
                        cmd = "UPDATE armas SET custo = " + custo + " WHERE id = " + idG;
                        bd.execSQL( cmd );
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
            }
            /*if(nomeG.equals("Shotgun")){
                if(contG == 0){
                    if(dindin >= custoMelhoriaShotgun){
                        dindin = dindin - custoMelhoriaShotgun;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaShotgun += 250;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custoMelhoriaShotgun){
                        dindin = dindin - custoMelhoriaShotgun;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaShotgun += 150;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custoMelhoriaShotgun){
                        dindin = dindin - custoMelhoriaShotgun;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaShotgun += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custoMelhoriaShotgun){
                        dindin = dindin - custoMelhoriaShotgun;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaShotgun += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custoMelhoriaShotgun){
                        dindin = dindin - custoMelhoriaShotgun;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaShotgun += 500;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 5){
                    Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();

                }
                String din =  String.valueOf(contG);
                //Toast.makeText(getApplicationContext(), "Cont Shotgun: "+din, Toast.LENGTH_SHORT).show();
            }
            if(nomeG.equals("Machado")){
                if(contG == 0){
                    if(dindin >= custoMelhoriaMachado){
                        dindin = dindin - custoMelhoriaMachado;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaMachado += 250;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custoMelhoriaMachado){
                        dindin = dindin - custoMelhoriaMachado;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaMachado += 150;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custoMelhoriaMachado){
                        dindin = dindin - custoMelhoriaMachado;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaMachado += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custoMelhoriaMachado){
                        dindin = dindin - custoMelhoriaMachado;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaMachado += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custoMelhoriaMachado){
                        dindin = dindin - custoMelhoriaMachado;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaMachado += 500;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 5){
                    Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();

                }
                String din =  String.valueOf(contG);
                //Toast.makeText(getApplicationContext(), "Cont Machado: "+din, Toast.LENGTH_SHORT).show();
            }
            if(nomeG.equals("Adaga")) {
                if(contG == 0){
                    if(dindin >= custoMelhoriaAdaga){
                        dindin = dindin - custoMelhoriaAdaga;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaAdaga += 250;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custoMelhoriaAdaga){
                        dindin = dindin - custoMelhoriaAdaga;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaAdaga += 150;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custoMelhoriaAdaga){
                        dindin = dindin - custoMelhoriaAdaga;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaAdaga += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custoMelhoriaAdaga){
                        dindin = dindin - custoMelhoriaAdaga;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaAdaga += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custoMelhoriaAdaga){
                        dindin = dindin - custoMelhoriaAdaga;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaAdaga += 500;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 5){
                    Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();

                }
                String din =  String.valueOf(contG);
                //Toast.makeText(getApplicationContext(), "Cont Adaga: "+din, Toast.LENGTH_SHORT).show();

            }
            if(nomeG.equals("ArcoFlecha")){
                if(contG == 0){
                    if(dindin >= custoMelhoriaArco){
                        dindin = dindin - custoMelhoriaArco;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaArco += 250;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custoMelhoriaArco){
                        dindin = dindin - custoMelhoriaArco;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaArco += 150;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custoMelhoriaArco){
                        dindin = dindin - custoMelhoriaArco;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaArco += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custoMelhoriaArco){
                        dindin = dindin - custoMelhoriaArco;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaArco += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custoMelhoriaArco){
                        dindin = dindin - custoMelhoriaArco;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaArco += 500;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 5){
                    Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();
                }
                String din =  String.valueOf(contG);
                //Toast.makeText(getApplicationContext(), "Cont ArcoFlecha: "+din, Toast.LENGTH_SHORT).show();
            }
            if(nomeG.equals("Glock")){
                if(contG == 0){
                    if(dindin >= custoMelhoriaGlock){
                        dindin = dindin - custoMelhoriaGlock;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaGlock += 250;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }

                }
                if(contG == 1){
                    if(dindin >= custoMelhoriaGlock){
                        dindin = dindin - custoMelhoriaGlock;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaGlock += 150;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 2){
                    if(dindin >= custoMelhoriaGlock){
                        dindin = dindin - custoMelhoriaGlock;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaGlock += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 3){
                    if(dindin >= custoMelhoriaGlock){
                        dindin = dindin - custoMelhoriaGlock;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaGlock += 100;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 4){
                    if(dindin >= custoMelhoriaGlock){
                        dindin = dindin - custoMelhoriaGlock;

                        // checagem - ta pegando certo
                        String din =  String.valueOf(dindin);
                        Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE usuario SET dinheiro = " + dindin;
                        //bd.execSQL( cmd );

                        String yo =  String.valueOf(danoNovo);
                        Toast.makeText(getApplicationContext(), "Dano Novo: "+yo, Toast.LENGTH_SHORT).show();
                        //cmd = "UPDATE armas SET dano = " + danoNovo + " WHERE id = " + idG;
                        //bd.execSQL( cmd );

                        custoMelhoriaGlock += 500;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
                if(contG == 5){
                    Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();

                }
                String din =  String.valueOf(contG);
                //Toast.makeText(getApplicationContext(), "Cont glock: "+din, Toast.LENGTH_SHORT).show();


            }*/
            if(contG>=5){
                Toast.makeText(getApplicationContext(), "Melhoria Máxima Atingida.", Toast.LENGTH_SHORT).show();
            }
            else{
                contG += 1;
                cmd = "UPDATE armas SET contM = " + contG + " WHERE id = " + idG;
                bd.execSQL( cmd );
            }

            Toast.makeText(getApplicationContext(), "Cont "+nomeG+": "+contG, Toast.LENGTH_SHORT).show();

        }
    }

}



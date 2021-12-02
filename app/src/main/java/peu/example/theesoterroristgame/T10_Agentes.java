package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class T10_Agentes extends AppCompatActivity {

    Cursor cursor;
    AdapterArmas adapter;
    SQLiteDatabase bd;
    int idglob = 0;
    String nglob;

    private TextView lblDinheiroAg;

    //TEM QUE COLOCAR ALGO - ATRIBUT NO BDD 'LIBERADO' COMO TRUE E FALSE, SE FOR TRUE POD USAR AGENTE
    //SE FOR FALSE MOSTRA O CADEADO (QUE JA VOU DEIXAR NELES, MAS QUE FUTURAMENTE TERA QUE VERIFICAR NO BDD)
    // CLIQUE EM UM AGENTE BLOQUEADO PARA DESBLOQUEÁ-LO - COMPRANDO
    //QUANDO COMPRA ALTERA O BDD E LIBERADO VIRA TRUE
    private Button btnAmyA;
    private TextView lblAmyA;
    private Button btnCollinA;
    private TextView lblCollinA;
    private Button btnJennaA;
    private TextView lblJennaA;
    private Button btnVioletA;
    private TextView lblVioletA;
    private Button btnJonsonA;
    private TextView lblJonsonA;
    private Button btnRoseA;
    private TextView lblRoseA;
    private Button btnMaykoA;
    private TextView lblMaykoA;
    private Button btnKimuraA;
    private TextView lblKimuraA;

    private Button btnFecha;
    private Button btnDesbloquear;
    private Button btnUpar;

    private TextView lblNomeA;
    private TextView lblVidaAtual;
    private TextView lblVidaMaxima;
    private TextView lblAtaqueA;
    private TextView lblContraAtA;
    private TextView lblEsquivaA;
    private TextView textView28;
    private TextView lblSobreA;
    private TextView TextView1;
    private TextView TextView2;
    private TextView TextView3;
    private TextView TextView4;
    private TextView TextView5;
    private TextView TextView12;
    private TextView TextView15;
    private TextView lblArmaA;

    private ImageView imgBranco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t10_agentes);

        lblDinheiroAg = findViewById(R.id.lblDinheiroAg);

        btnAmyA = findViewById(R.id.btnAmyA);
        lblAmyA = findViewById(R.id.lblAmyA);
        btnCollinA =  findViewById(R.id.btnCollinA);
        lblCollinA = findViewById(R.id.lblCollinA);
        btnJennaA = findViewById(R.id.btnJennaA);
        lblJennaA = findViewById(R.id.lblJennaA);
        btnVioletA = findViewById(R.id.btnVioletA);
        lblVioletA = findViewById(R.id.lblVioletA);
        btnJonsonA = findViewById(R.id.btnJonsonA);
        lblJonsonA = findViewById(R.id.lblJonsonA);
        btnRoseA = findViewById(R.id.btnRoseA);
        lblRoseA = findViewById(R.id.lblRoseA);
        btnMaykoA = findViewById(R.id.btnMaykoA);
        lblMaykoA = findViewById(R.id.lblMaykoA);
        btnKimuraA = findViewById(R.id.btnKimuraA);
        lblKimuraA = findViewById(R.id.lblKimuraA);

        btnFecha = findViewById(R.id.btnFecha);
        btnDesbloquear = findViewById(R.id.btnDesbloquear);
        btnUpar = findViewById(R.id.btnUpar);

        lblNomeA = findViewById(R.id.lblNomeA);
        lblVidaAtual = findViewById(R.id.lblVidaAtual);
        lblVidaMaxima = findViewById(R.id.lblVidaMaxima);
        lblAtaqueA = findViewById(R.id.lblAtaqueA);
        lblContraAtA = findViewById(R.id.lblContraAtA);
        lblEsquivaA = findViewById(R.id.lblEsquivaA);
        textView28 = findViewById(R.id.textView28);
        lblSobreA = findViewById(R.id.lblSobreA);
        TextView1 = findViewById(R.id.TextView1);
        TextView2 = findViewById(R.id.TextView2);
        TextView3 = findViewById(R.id.TextView3);
        TextView4 = findViewById(R.id.TextView12);
        TextView5 = findViewById(R.id.TextView5);
        lblArmaA = findViewById(R.id.lblArmaA);
        TextView12 = findViewById(R.id.TextView12);
        TextView15 = findViewById(R.id.textView15);

        imgBranco = findViewById(R.id.imgBranco);

        //exemplo escutador pros agentes pra dar cura
        EscutadorAgente ea = new EscutadorAgente();
        btnAmyA.setOnClickListener(ea);
        btnCollinA.setOnClickListener(ea);
        btnJennaA.setOnClickListener(ea);
        btnVioletA.setOnClickListener(ea);
        btnJonsonA.setOnClickListener(ea);
        btnRoseA.setOnClickListener(ea);
        btnMaykoA.setOnClickListener(ea);
        btnKimuraA.setOnClickListener(ea);

        EscutadorBotoes ef = new EscutadorBotoes();
        btnFecha.setOnClickListener(ef);
        btnUpar.setOnClickListener(ef);
        btnDesbloquear.setOnClickListener(ef);

        // criando cursor com os dados vindos do banco
        //cursorAgentes = bd.rawQuery( "SELECT _rowid_ _id, nome, historia, vida, esquiva, ataque, contraataque, arma, status FROM agentes", null );

        // criando o objeto adapter, passando o cursor
        //adapterAgentes = new AdapterAgentes(this, cursorAgentes );

        //RECUPERA VALOR DINHEIRO
        bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
        String cmd;
        int dindin;
        cmd = "SELECT dinheiro FROM usuario";

        Cursor dados = bd.rawQuery( cmd, null);
        while( dados.moveToNext()){
            dindin = dados.getInt( dados.getColumnIndex("dinheiro") );

            String money = String.valueOf(dindin);
            lblDinheiroAg.setText( money );
        }


    }

    private class EscutadorAgente implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;

            btnAmyA.setVisibility(View.INVISIBLE);
            lblAmyA.setVisibility(View.INVISIBLE);
            btnCollinA.setVisibility(View.INVISIBLE);
            lblCollinA.setVisibility(View.INVISIBLE);
            btnJennaA.setVisibility(View.INVISIBLE);
            lblJennaA.setVisibility(View.INVISIBLE);
            btnVioletA.setVisibility(View.INVISIBLE);
            lblVioletA.setVisibility(View.INVISIBLE);
            btnJonsonA.setVisibility(View.INVISIBLE);
            lblJonsonA.setVisibility(View.INVISIBLE);
            btnRoseA.setVisibility(View.INVISIBLE);
            lblRoseA.setVisibility(View.INVISIBLE);
            btnMaykoA.setVisibility(View.INVISIBLE);
            lblMaykoA.setVisibility(View.INVISIBLE);
            btnKimuraA.setVisibility(View.INVISIBLE);
            lblKimuraA.setVisibility(View.INVISIBLE);

            lblNomeA.setVisibility(View.VISIBLE);
            lblVidaAtual.setVisibility(View.VISIBLE);
            lblVidaMaxima.setVisibility(View.VISIBLE);
            lblAtaqueA.setVisibility(View.VISIBLE);
            lblContraAtA.setVisibility(View.VISIBLE);
            lblEsquivaA.setVisibility(View.VISIBLE);
            textView28.setVisibility(View.VISIBLE);
            lblSobreA.setVisibility(View.VISIBLE);
            TextView1.setVisibility(View.VISIBLE);
            TextView2.setVisibility(View.VISIBLE);
            TextView3.setVisibility(View.VISIBLE);
            TextView4.setVisibility(View.VISIBLE);
            TextView5.setVisibility(View.VISIBLE);
            lblArmaA.setVisibility(View.VISIBLE);
            TextView12.setVisibility(View.VISIBLE);
            TextView15.setVisibility(View.VISIBLE);

            imgBranco.setVisibility(View.VISIBLE);
            btnFecha.setVisibility(View.VISIBLE);


            String  nome, historia, arma, msg;
            int vidaat, vidamax, esquiva, ataque, contraataque, status;
            bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
            String cmd;
            cmd = "SELECT id, nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status FROM agentes";
            cursor = bd.rawQuery( cmd, null );

            // criando cursor com os dados vindos do banco
            switch (b.getId()){

                case R.id.btnAmyA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );
                        if(nome.equals("Amy Carter")){
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            nglob = nome;
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_amycarter);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnCollinA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );
                        if(nome.equals("Collin Auburg")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_collinauburg);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case R.id.btnJennaA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );

                        if(nome.equals("Jenna Washington")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_jennawashington);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnVioletA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString(cursor.getColumnIndex("nome"));

                        if (nome.equals("Violet Flaucus")) {
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString(cursor.getColumnIndex("historia"));
                            vidaat = cursor.getInt(cursor.getColumnIndex("vidaat"));
                            vidamax = cursor.getInt(cursor.getColumnIndex("vidamax"));
                            esquiva = cursor.getInt(cursor.getColumnIndex("esquiva"));
                            ataque = cursor.getInt(cursor.getColumnIndex("ataque"));
                            contraataque = cursor.getInt(cursor.getColumnIndex("contraataque"));
                            arma = cursor.getString(cursor.getColumnIndex("arma"));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_violetflaucus);
                            lblNomeA.setText(nome);
                            lblVidaAtual.setText(vidaAtual);
                            lblVidaMaxima.setText(vidaMax);
                            lblAtaqueA.setText(vAtaque);
                            lblEsquivaA.setText(vEsquiva);
                            lblContraAtA.setText(vContraA);
                            lblArmaA.setText(arma);
                            lblSobreA.setText(historia);

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnJonsonA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );

                        if(nome.equals("Jonson Donley")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_jonsondonley);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnRoseA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );

                        if(nome.equals("Rose Naciroff")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_rosenaciroff);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnMaykoA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );

                        if(nome.equals("Mayko Tucker")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_maykotucker);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    break;
                case  R.id.btnKimuraA:
                    while ( cursor.moveToNext() ) {
                        nome = cursor.getString( cursor.getColumnIndex( "nome" ) );

                        if(nome.equals("Kimura Otosuri")){
                            nglob = nome;
                            idglob = cursor.getInt( cursor.getColumnIndex( "id" ));
                            historia = cursor.getString( cursor.getColumnIndex( "historia" ));
                            vidaat = cursor.getInt( cursor.getColumnIndex( "vidaat" ) );
                            vidamax = cursor.getInt( cursor.getColumnIndex( "vidamax" ) );
                            esquiva = cursor.getInt( cursor.getColumnIndex( "esquiva" ) );
                            ataque = cursor.getInt( cursor.getColumnIndex( "ataque" ) );
                            contraataque = cursor.getInt( cursor.getColumnIndex( "contraataque" ) );
                            arma = cursor.getString( cursor.getColumnIndex( "arma" ));
                            status = cursor.getInt( cursor.getColumnIndex( "status" )  );

                            String vidaAtual = String.valueOf(vidaat);
                            String vidaMax = String.valueOf(vidamax);
                            String vEsquiva = String.valueOf(esquiva);
                            String vAtaque = String.valueOf(ataque);
                            String vContraA = String.valueOf(contraataque);

                            imgBranco.setImageResource(R.drawable.agente_kimuraotosuri);
                            lblNomeA.setText( nome );
                            lblVidaAtual.setText( vidaAtual );
                            lblVidaMaxima.setText( vidaMax );
                            lblAtaqueA.setText( vAtaque );
                            lblEsquivaA.setText( vEsquiva );
                            lblContraAtA.setText( vContraA );
                            lblArmaA.setText( arma );
                            lblSobreA.setText( historia );

                            if(status == 1){
                                //Toast.makeText(getApplicationContext(), "liberado", Toast.LENGTH_SHORT).show();
                                btnUpar.setVisibility(View.VISIBLE);
                            }
                            else{
                                //Toast.makeText(getApplicationContext(), "block", Toast.LENGTH_SHORT).show();
                                btnDesbloquear.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    imgBranco.setImageResource(R.drawable.agente_kimuraotosuri);
                    lblNomeA.setText( "Kimura Otosuri" );
                    break;

            }
        }
    }

    private class EscutadorBotoes implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;

            switch (b.getId()){
                case R.id.btnFecha:
                    btnAmyA.setVisibility(View.VISIBLE);
                    lblAmyA.setVisibility(View.VISIBLE);
                    btnCollinA.setVisibility(View.VISIBLE);
                    lblCollinA.setVisibility(View.VISIBLE);
                    btnJennaA.setVisibility(View.VISIBLE);
                    lblJennaA.setVisibility(View.VISIBLE);
                    btnVioletA.setVisibility(View.VISIBLE);
                    lblVioletA.setVisibility(View.VISIBLE);
                    btnJonsonA.setVisibility(View.VISIBLE);
                    lblJonsonA.setVisibility(View.VISIBLE);
                    btnRoseA.setVisibility(View.VISIBLE);
                    lblRoseA.setVisibility(View.VISIBLE);
                    btnMaykoA.setVisibility(View.VISIBLE);
                    lblMaykoA.setVisibility(View.VISIBLE);
                    btnKimuraA.setVisibility(View.VISIBLE);
                    lblKimuraA.setVisibility(View.VISIBLE);

                    lblNomeA.setVisibility(View.INVISIBLE);
                    lblVidaAtual.setVisibility(View.INVISIBLE);
                    lblVidaMaxima.setVisibility(View.INVISIBLE);
                    lblAtaqueA.setVisibility(View.INVISIBLE);
                    lblContraAtA.setVisibility(View.INVISIBLE);
                    lblEsquivaA.setVisibility(View.INVISIBLE);
                    textView28.setVisibility(View.INVISIBLE);
                    lblSobreA.setVisibility(View.INVISIBLE);
                    TextView1.setVisibility(View.INVISIBLE);
                    TextView2.setVisibility(View.INVISIBLE);
                    TextView3.setVisibility(View.INVISIBLE);
                    TextView4.setVisibility(View.INVISIBLE);
                    TextView5.setVisibility(View.INVISIBLE);
                    lblArmaA.setVisibility(View.INVISIBLE);
                    TextView12.setVisibility(View.INVISIBLE);
                    TextView15.setVisibility(View.INVISIBLE);

                    imgBranco.setVisibility(View.INVISIBLE);
                    btnFecha.setVisibility(View.INVISIBLE);
                    btnDesbloquear.setVisibility(View.INVISIBLE);
                    btnUpar.setVisibility(View.INVISIBLE);
                    break;

                case R.id.btnUpar:
                    Toast.makeText(getApplicationContext(), "Abre upar", Toast.LENGTH_SHORT).show();
                    break;
                    // NESSE ABRE OUTRA PAGINA QUE RECUPERA OS DADOS DO PERSONAGEM SELECIONADO
                    // ENVIA OS DADOS JUNTO QUANDO ABRIR PLMDD
                    // MOSTRA LA COMO Q VAI FICAR AS COISAS DO AGENTE
                    // VAI TER A CAIXA PERGUNTANDO SE QUER EU NAO, NAO: FECHA, SIM: SUBTRAI O DINHEIRO
                    // ALTERA OS VALOR DOS ATRIBUTOS E DPS DA UPDATE

                case R.id.btnDesbloquear:

                    // NESSE VAI APARECER CAIXA DE TEXTO PERGUNTANDO SE USUARIO QUER MSM DESBLOQUEAR
                    // SE NAO FECHA, SE SIM SUBTRAI 1000 DO DINHEIRO QUE TEM ((UPDATE NO BD NO MONEY E NO STTS
                    AlertDialog.Builder dialogo = new AlertDialog.Builder( T10_Agentes.this);
                    dialogo.setMessage("Custo: R$1000");
                    dialogo.setPositiveButton( "Confirmar", new EscutadorS() );
                    dialogo.setNegativeButton( "Voltar", new EscutadorN() );
                    dialogo.setCancelable( false );
                    dialogo.create();
                    dialogo.show();

            }
        }
    }

    private class EscutadorN implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    private class EscutadorS implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
            String cmd;

            //RECUPERA VALOR DINHEIRO
            int dindin;
            int att = 0;
            cmd = "SELECT dinheiro FROM usuario";


            Cursor dados = bd.rawQuery( cmd, null);
            while( dados.moveToNext()){
                dindin = dados.getInt( dados.getColumnIndex("dinheiro") );

                if(dindin >= 1000){
                    dindin = dindin - 1000;

                    // checagem - ta pegando certo
                    //String din =  String.valueOf(dindin);
                    //Toast.makeText(getApplicationContext(), din, Toast.LENGTH_SHORT).show();

                    cmd = "UPDATE usuario SET dinheiro = " + dindin;
                    bd.execSQL( cmd );

                    att +=1;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Dinheiro insuficiente.", Toast.LENGTH_SHORT).show();
                    //break;
                }
            }

            if(att!=0){
                // ATT BD
                //Toast.makeText(getApplicationContext(), "NOME: "+nglob, Toast.LENGTH_SHORT).show();
                cmd = "UPDATE agentes SET status = " + 1 + " WHERE id = " + idglob;
                bd.execSQL( cmd );
                Toast.makeText(getApplicationContext(), "Personagem desbloqueado :)", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
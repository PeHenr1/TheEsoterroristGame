package peu.example.theesoterroristgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class T8_Tratamento extends AppCompatActivity {

    private ImageView lblVazio;
    private TextView lblDinheiroT;
    private TextView lblNome;
    private TextView lblVidaAtualT;
    private TextView lblVidaMaximaT;
    private Button btnLeve;
    private Button btnMediano;
    private Button btnIntenso;

    int idg = 0;
    int vAtualRec = 0;
    int vMax = 0;

    SQLiteDatabase bd;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t8_tratamento);

        lblVazio = findViewById(R.id.lblVazio);
        lblDinheiroT = findViewById(R.id.lblDinheiroT);
        lblNome = findViewById(R.id.lblNome);
        lblVidaAtualT = findViewById(R.id.lblVidaAtualT);
        lblVidaMaximaT = findViewById(R.id.lblVidaMaximaT);
        btnLeve = findViewById(R.id.btnLeve);
        btnMediano = findViewById(R.id.btnMediano);
        btnIntenso = findViewById(R.id.btnIntenso);

        Intent j = getIntent();
        String nome = j.getStringExtra("nome");

        String  name;
        int vidaat, vidamax;
        bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);

        //---------------------------------------
        //RECUPERA VALOR DINHEIRO - OK
        String cmd;
        int dindin;
        cmd = "SELECT dinheiro FROM usuario";

        Cursor dados = bd.rawQuery( cmd, null);
        while( dados.moveToNext()){
            dindin = dados.getInt( dados.getColumnIndex("dinheiro") );

            String money = String.valueOf(dindin);
            lblDinheiroT.setText( money );
        }
        //---------------------------------------

        cmd = "SELECT id, nome, vidaat, vidamax FROM agentes";
        cursor = bd.rawQuery( cmd, null );
        while ( cursor.moveToNext() ) {
            name = cursor.getString( cursor.getColumnIndex( "nome" ) );

            if(name.equals(nome)) {
                idg = cursor.getInt(cursor.getColumnIndex("id"));
                vidaat = cursor.getInt(cursor.getColumnIndex("vidaat"));
                vidamax = cursor.getInt(cursor.getColumnIndex("vidamax"));

                vAtualRec = vidaat;
                vMax = vidamax;
                String vidaAt = String.valueOf(vidaat);
                String vidaMax = String.valueOf(vidamax);

                lblNome.setText(name);
                lblVidaAtualT.setText(vidaAt);
                lblVidaMaximaT.setText(vidaMax);

                if(name.equals("Amy Carter")){
                    lblVazio.setImageResource(R.drawable.agente_amycarter);
                }
                if(name.equals("Collin Auburg")){
                    lblVazio.setImageResource(R.drawable.agente_collinauburg);
                }
                if(name.equals("Jenna Washington")){
                    lblVazio.setImageResource(R.drawable.agente_jennawashington);
                }
                if(name.equals("Jonson Donley")){
                    lblVazio.setImageResource(R.drawable.agente_jonsondonley);
                }
                if(name.equals("Violet Flaucus")){
                    lblVazio.setImageResource(R.drawable.agente_violetflaucus);
                }
                if(name.equals("Rose Naciroff")){
                    lblVazio.setImageResource(R.drawable.agente_rosenaciroff);
                }
                if(name.equals("Mayko Tucker")){
                    lblVazio.setImageResource(R.drawable.agente_maykotucker);
                }
                if(name.equals("Kimura Otosuri")){
                    lblVazio.setImageResource(R.drawable.agente_kimuraotosuri);
                }
            }
        }

        EscutadorCura ec = new EscutadorCura();
        btnLeve.setOnClickListener(ec);
        btnMediano.setOnClickListener(ec);
        btnIntenso.setOnClickListener(ec);

    }
    private class EscutadorCura implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
            String cmd;

            //RECUPERA DINHEIRO E CHECA
            int dindin = 0;
            int att = 0;
            cmd = "SELECT dinheiro FROM usuario";
            Cursor dados = bd.rawQuery( cmd, null);
            while( dados.moveToNext()){
                dindin = dados.getInt( dados.getColumnIndex("dinheiro") );
            }

            switch (b.getId()){
                case R.id.btnLeve:
                    if(vAtualRec == vMax){
                        Toast.makeText(getApplicationContext(), "Tratamento não é necessário.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(dindin >= 100){
                            dindin = dindin - 100;

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

                    //RECUPERA VIDA
                    if(att!=0){
                        vAtualRec = vAtualRec + 5;
                        if(vAtualRec > vMax){
                            vAtualRec = vMax;
                        }
                        //String v =  String.valueOf(vAtualRec);
                        //Toast.makeText(getApplicationContext(), v, Toast.LENGTH_SHORT).show();

                        cmd = "UPDATE agentes SET vidaat = " + vAtualRec + " WHERE id = " + idg;
                        bd.execSQL( cmd );
                        Toast.makeText(getApplicationContext(), "O agente foi tratado!", Toast.LENGTH_SHORT).show();

                    }

                    //Toast.makeText(getApplicationContext(), "Tratamento Leve.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnMediano:
                    if(vAtualRec == vMax){
                        Toast.makeText(getApplicationContext(), "Tratamento não é necessário.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(dindin >= 350){
                            dindin = dindin - 350;

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

                    //RECUPERA VIDA
                    if(att!=0){
                        vAtualRec = vAtualRec + 11;
                        if(vAtualRec > vMax){
                            vAtualRec = vMax;
                        }
                        //String v =  String.valueOf(vAtualRec);
                        //Toast.makeText(getApplicationContext(), v, Toast.LENGTH_SHORT).show();

                        cmd = "UPDATE agentes SET vidaat = " + vAtualRec + " WHERE id = " + idg;
                        bd.execSQL( cmd );
                        Toast.makeText(getApplicationContext(), "O agente foi tratado!", Toast.LENGTH_SHORT).show();

                    }
                    //Toast.makeText(getApplicationContext(), "Tratamento Mediano.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnIntenso:
                    if(vAtualRec == vMax){
                        Toast.makeText(getApplicationContext(), "Tratamento não é necessário.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(dindin >= 500){
                            dindin = dindin - 500;

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

                    //RECUPERA VIDA
                    if(att!=0){
                        vAtualRec = vMax;
                        //String v =  String.valueOf(vAtualRec);
                        //Toast.makeText(getApplicationContext(), v, Toast.LENGTH_SHORT).show();

                        cmd = "UPDATE agentes SET vidaat = " + vAtualRec + " WHERE id = " + idg;
                        bd.execSQL( cmd );
                        Toast.makeText(getApplicationContext(), "O agente foi tratado!", Toast.LENGTH_SHORT).show();

                    }
                    //Toast.makeText(getApplicationContext(), "Tratamento Intenso.", Toast.LENGTH_SHORT).show();
                    break;
            }
            Intent j = new Intent( getApplicationContext(), T7_Enfermaria.class );
            startActivity(j);

        }
    }

}
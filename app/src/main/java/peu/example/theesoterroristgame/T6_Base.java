package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class T6_Base extends AppCompatActivity {

    SQLiteDatabase bd;

    private Button btnEnfermaria;
    private Button btnArsenal;
    private Button btnAgentes;
    private Button btnAjuda;
    private Button btnConfig;
    private Button btnMapa;
    private TextView lblDinheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t6_base);

        btnEnfermaria = findViewById(R.id.btnEnfermaria);
        btnArsenal = findViewById(R.id.btnArsenal);
        btnAgentes = findViewById(R.id.btnAgentes);
        btnMapa = findViewById(R.id.btnBase); //o botao tilto nao mexe q ta funcionando
        btnConfig = findViewById(R.id.btnConfigM);

        lblDinheiro = findViewById(R.id.lblDinheiro);

        AbrirTela at = new AbrirTela();
        btnEnfermaria.setOnClickListener(at);
        btnArsenal.setOnClickListener(at);
        btnAgentes.setOnClickListener(at);
        btnMapa.setOnClickListener(at);
        btnConfig.setOnClickListener(at);



        //RECUPERA VALOR DINHEIRO
        bd = openOrCreateDatabase("theesoterroristbd", MODE_PRIVATE, null);
        String cmd;
        int dindin;
        cmd = "SELECT dinheiro FROM usuario";

        Cursor dados = bd.rawQuery( cmd, null);
        while( dados.moveToNext()){
            dindin = dados.getInt( dados.getColumnIndex("dinheiro") );

            String money = String.valueOf(dindin);
            lblDinheiro.setText( money );
        }
    }


    private class AbrirTela implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            switch (b.getId()) {
                case R.id.btnEnfermaria:
                    Intent e = new Intent(getApplicationContext(), T7_Enfermaria.class);
                    startActivity(e);
                    break;
                case R.id.btnArsenal:
                    Intent a = new Intent(getApplicationContext(), T9_Arsenal.class);
                    startActivity(a);
                    break;
                case R.id.btnAgentes:
                    Intent ag = new Intent(getApplicationContext(), T10_Agentes.class);
                    startActivity(ag);
                    break;

                case  R.id.btnAjuda:
                    Intent aj = new Intent( getApplicationContext(), T4_Tutorial.class );
                    startActivity(aj);
                    break;

                case R.id.btnBase:
                    Intent ab = new Intent(getApplicationContext(), T12_Mapa.class);
                    startActivity(ab);
                    break;
                case R.id.btnConfigM:
                    Intent ac = new Intent(getApplicationContext(), T13_Configuracao.class);
                    startActivity(ac);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //cria caixa de dialogo, deefine titulo, texto, btn ação positiva e negativa
        AlertDialog.Builder dialogo = new AlertDialog.Builder( T6_Base.this);
        dialogo.setTitle("DESEJA RETORNAR AO MENU ?");
        dialogo.setMessage("Seu progresso será salvo");
        dialogo.setPositiveButton( "Sim", new T6_Base.EscutadorSim() );
        dialogo.setNegativeButton( "Não", new T6_Base.EscutadorNao() );

        //proibe usuario de fazer outra coisa, TEM QUE ESCOLHER
        dialogo.setCancelable( false );

        //cria e exibe
        dialogo.create();
        dialogo.show();
    }

    private class EscutadorNao implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    private class EscutadorSim implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent j = new Intent( getApplicationContext(), T11_InicioDois.class );
            startActivity(j);
        }
    }
}
package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class T11_InicioDois extends AppCompatActivity {


    private Button btnNovoJogo;
    private Button btnContinuar;
    private Button btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t11_inicio_dois);

        btnNovoJogo = findViewById(R.id.btnNovoJogo);
        btnNovoJogo.setOnClickListener( new NovoJogo() );

        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener( new ContinuarProgresso() );

        btnConfig = findViewById(R.id.btnContinuar);
        //btnConfig.setOnClickListener( new AbrirConfig() );
    }

    private class NovoJogo implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            //cria caixa de dialogo, deefine titulo, texto, btn ação positiva e negativa
            AlertDialog.Builder dialogo = new AlertDialog.Builder( T11_InicioDois.this);
            dialogo.setTitle(" TEM CERTEZA ?");
            dialogo.setMessage("Seu progresso anterior será apagado.");
            dialogo.setPositiveButton( "Sim", new T11_InicioDois.EscutadorSim() );
            dialogo.setNegativeButton( "Não", new T11_InicioDois.EscutadorNao() );

            //proibe usuario de fazer outra coisa, TEM QUE ESCOLHER
            dialogo.setCancelable( false );

            //cria e exibe
            dialogo.create();
            dialogo.show();

        }
    }

    private class ContinuarProgresso implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent j = new Intent( getApplicationContext(), T6_Base.class );
            startActivity(j);
        }
    }

    private class EscutadorNao implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    // vai cadastrar dnv
    private class EscutadorSim implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            // LIMPA A TABELA JOGADOR
            //TRUNCATE TABLE tabela;

            Intent j = new Intent( getApplicationContext(), T2_InicioCadastro.class );
            startActivity(j);
        }
    }
}
package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class T3_Historia extends AppCompatActivity {

    private TextView txtHistoria;
    private Button btnSeguinte;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3_historia);

        txtHistoria = findViewById( R.id.txtHistoria );
        btnSeguinte = findViewById(R.id.btnSeguinte);

        btnSeguinte.setOnClickListener( new Prosseguir() );
    }

    private class Prosseguir implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch(i){
                case 1:
                    txtHistoria.setText("Para combater os Esoterroristas, existe a Ordo Veritatis, uma organização de elite formada por cidadãos comuns, recrutados das mais diversas camadas sociais e profissões, vivendo uma vida dupla combatendo o sobrenatural.\n" +
                            "\n" +
                            "Você enfrentará criaturas do universo sobrenatural para proteger a população e seus companheiros.\n" +
                            "\n" +
                            "Além disso, agentes especializados, armas e muito mais te ajudarão nessa jornada para derrotar a fonte do mal. \n" +
                            "\n");
                    i++;
                    break;

                case 2:
                    txtHistoria.setText("Para começar a sua jornada, os agentes Carter e Auburg irão te acompanhar enquanto lhe dou as instruções básicas.\n");
                    i++;
                    break;

                default:
                    Intent m = new Intent( getApplicationContext(), T4_Tutorial.class);
                    //Intent m = new Intent( getApplicationContext(), T5_FinalizaTutorial.class);
                    startActivity(m);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        switch (i) {
            case 1:
                //cria caixa de dialogo, deefine titulo, texto, btn ação positiva e negativa
                AlertDialog.Builder dialogo = new AlertDialog.Builder( T3_Historia.this);
                dialogo.setTitle("DESEJA RETORNAR AO MENU ?");
                dialogo.setMessage("Seu progresso será salvo");
                dialogo.setPositiveButton( "Sim", new T3_Historia.EscutadorSim() );
                dialogo.setNegativeButton( "Não", new T3_Historia.EscutadorNao() );

                //proibe usuario de fazer outra coisa, TEM QUE ESCOLHER
                dialogo.setCancelable( false );

                //cria e exibe
                dialogo.create();
                dialogo.show();
            case 2:
                txtHistoria.setText("The Esoterrorist é uma organização global de renegados de várias tradições ocultas, com o objetivo de aumentar seus próprios poderes. Para tal, utilizam as Criaturas do Horror Incessante para amedrontar a sociedade e enfraquecer a membrana. \\n\\n Essa membrana divide duas dimensões, a “normal”, em que vivemos, e a “paranormal”, onde se encontram criaturas obsoletas e aterrorizantes e lugar em que tudo de mal pode acontecer. ");
                i--;
                break;

            case 3:
                txtHistoria.setText("Para combater os Esoterroristas, existe a Ordo Veritatis, uma organização de elite formada por cidadãos comuns, recrutados das mais diversas camadas sociais e profissões, vivendo uma vida dupla combatendo o sobrenatural.\n" +
                        "\n" +
                        "Você enfrentará criaturas do universo sobrenatural para proteger a população e seus companheiros.\n" +
                        "\n" +
                        "Além disso, agentes especializados, armas e muito mais te ajudarão nessa jornada para derrotar a fonte do mal. \n" +
                        "\n");
                i--;
                break;
        }
    }
    // FECHA A CAIXA
    private class EscutadorNao implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    // ABRE O INICIO DOIS, QUE VAI TER A PARTE DO CARREGAR PROGRESSO
    // SE NESSA PAGINA O USUARIO QUISER CMC JOGO NOVO, MOSTRA OUTRA CAIXA, DAI NELA FALA AS COISA E TAL

    private class EscutadorSim implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // ABRE
            Intent j = new Intent( getApplicationContext(), T11_InicioDois.class );
            startActivity(j);
        }
    }
}
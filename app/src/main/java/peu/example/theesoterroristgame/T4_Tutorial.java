package peu.example.theesoterroristgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class T4_Tutorial extends AppCompatActivity {

    private TextView txtTutorial;
    private Button btnNext;
    int n = 1;
    //private LinearLayout llBotoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txtTutorial = (TextView) findViewById( R.id.txtTutorial );
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener( new Proximo() );
        //llBotoes = (LinearLayout) findViewById(R.id.llBotoes);

    }
    private class Proximo implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if(n == 1)
                txtTutorial.setText("Cada agente tem seu próprio atributo: vida, ataque, contra-ataque, esquiva.\n" +
                        "O resultado do dado pode ser: \n" +
                        "\n" +
                        "Normal: 50% dano\n" +
                        "Bom: 80% de dano\n" +
                        "Extremo: 100% dano.\n" +
                        " \n" +
                        "Caso fracasse, o ataque nao atingirá a criatura.\n");

            if(n == 2)
                txtTutorial.setText("Cada criatura atacará um dos agentes aleatoriamente e quando o faz, o agente que receberá o ataque pode escolher entre desviar ou contra-atacar. \n" +
                        "\n" +
                        "O agente pode desviar ou contra-atacar o ataque da criatura.");


            if(n == 3)
                //if(show == false){
                    //llBotoes.setVisibility(View.VISIBLE);
                    //
               // }

            if(n == 4)
                txtTutorial.setText("Caso escolha esquivar, o sistema rolará um dado:\n\n" +
                        "Normal: você recebe 25% do dano\\n" +
                        "Bom: 10% do dano\\n" +
                        "Extremo: 1% do dano - caso tire 20, valor máximo, sai ileso\\n" +
                        "\\nSe falhar, você recebe o dano total.");

            if(n == 5)
                txtTutorial.setText("Caso escolha contra-atacar:\n" +
                        "Normal: você recebe 50% do dano, mas dá 50% do dano da arma\n" +
                        "Bom: recebe 20% do dano, mas dá 70% do dano da arma\n" +
                        "Extremo: recebe 1% do dano (se der 20, sai ileso) e 100% do dano da arma\n" +
                        "\n" +
                        "Se falhar, você recebe o dano total.\n");


            Intent j = new Intent( getApplicationContext(), T5_FinalizaTutorial.class);
            if(n > 5)
                startActivity(j);

            n++;
        }
    }
}
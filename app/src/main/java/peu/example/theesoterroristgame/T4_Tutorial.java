package peu.example.theesoterroristgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class T4_Tutorial extends AppCompatActivity {

    private TextView txtTutorial;
    private Button btnNext;
    private Button button2;
    private Button button3;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4_tutorial);

        txtTutorial = findViewById( R.id.txtTutorial );
        btnNext = findViewById(R.id.btnNext);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        btnNext.setOnClickListener( new Proximo() );
    }

    private class Proximo implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch(i){
                case 1:
                    txtTutorial.setText("Cada agente tem seu próprio atributo: vida, ataque, contra-ataque, esquiva.\n" +
                            "O resultado do dado pode ser: \n" +
                            "\n" +
                            "Normal: 50% dano\n" +
                            "Bom: 80% de dano\n" +
                            "Extremo: 100% dano.\n" +
                            " \n" +
                            "Caso fracasse, o ataque nao atingirá a criatura.\n");
                    i+=1;
                    break;

                case 2:
                    txtTutorial.setText("Cada criatura atacará um dos agentes aleatoriamente e quando o faz, o agente que receberá o ataque pode escolher entre desviar ou contra-atacar. \n" +
                            "\n" +
                            "O agente pode desviar ou contra-atacar o ataque da criatura.");
                    i+=1;
                    break;

                case 3:
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    txtTutorial.setText("Caso escolha esquivar, o sistema rolará um dado:\n\n" +
                            "Normal: você recebe 25% do dano\n" +
                            "Bom: 10% do dano\n" +
                            "Extremo: sai ileso\n\n" +
                            "Se falhar, você recebe o dano total.");
                    i+=1;
                    break;

                case 4:
                    txtTutorial.setText("Caso escolha contra-atacar:\n\n" +
                            "Normal: você recebe 50% do dano, mas dá 50% do dano da arma\n" +
                            "Bom: recebe 20% do dano, mas dá 70% do dano da arma\n" +
                            "Extremo: recebe 10% do dano (se der 20, sai ileso) e 100% do dano da arma\n" +
                            "\n" +
                            "Se falhar, você recebe o dano total.\n");
                    i+=1;
                    break;

                case 5:
                    Intent m = new Intent( getApplicationContext(), T5_FinalizaTutorial.class);
                    startActivity(m);
                    break;


            }
        }
    }

}


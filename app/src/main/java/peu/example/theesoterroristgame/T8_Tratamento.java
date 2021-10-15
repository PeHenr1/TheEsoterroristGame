package peu.example.theesoterroristgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class T8_Tratamento extends AppCompatActivity {

    private ImageView imgVazio;
    private TextView lblNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t8_tratamento);

        imgVazio = findViewById(R.id.imgVazio);
        lblNome = (TextView) findViewById(R.id.lblNome);

        Intent j = getIntent();
        String nome = j.getStringExtra("nome");

        if(nome.equals("Amy Carter")){
            imgVazio.setImageResource(R.drawable.agente_amycarter);
            lblNome.setText( nome );
        }
        if(nome.equals("Collin Auburg")){
            imgVazio.setImageResource(R.drawable.agente_collinauburg);
            lblNome.setText( nome );
        }
        if(nome.equals("Jenna Washington")){
            imgVazio.setImageResource(R.drawable.agente_jennawashington);
            lblNome.setText( nome );
        }
        if(nome.equals("Violet Flaucus")){
            imgVazio.setImageResource(R.drawable.agente_violetflaucus);
            lblNome.setText( nome );
        }
        if(nome.equals("Jonson Donley")){
            imgVazio.setImageResource(R.drawable.agente_jonsondonley);
            lblNome.setText( nome );
        }
        if(nome.equals("Rose Naciroff")){
            imgVazio.setImageResource(R.drawable.agente_rosenaciroff);
            lblNome.setText( nome );
        }
        if(nome.equals("Mayko Tucker")){
            imgVazio.setImageResource(R.drawable.agente_maykotucker);
            lblNome.setText( nome );
        }
        if(nome.equals("Kimura Otosuri")){
            imgVazio.setImageResource(R.drawable.agente_kimuraotosuri);
            lblNome.setText( nome );
        }


    }
}
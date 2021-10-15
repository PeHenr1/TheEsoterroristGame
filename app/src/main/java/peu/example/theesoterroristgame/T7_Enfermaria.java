package peu.example.theesoterroristgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class T7_Enfermaria extends AppCompatActivity {

    private Button btnVai;
    private Button btnVolta;
    private Button btnAmy;
    private TextView lblAmy;
    private Button btnCollin;
    private TextView lblCollin;
    private Button btnJenna;
    private TextView lblJenna;
    private Button btnViolet;
    private TextView lblViolet;
    private Button btnJonson;
    private TextView lblJonson;
    private Button btnRose;
    private TextView lblRose;
    private Button btnMayko;
    private TextView lblMayko;
    private Button btnKimura;
    private TextView lblKimura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t7_enfermaria);

        btnVai = findViewById(R.id.btnVai);
        btnVolta = findViewById(R.id.btnVolta);

        btnAmy = findViewById(R.id.btnAmy);
        lblAmy = findViewById(R.id.lblAmy);
        btnCollin =  findViewById(R.id.btnCollin);
        lblCollin = findViewById(R.id.lblCollin);
        btnJenna = findViewById(R.id.btnJenna);
        lblJenna = findViewById(R.id.lblJenna);
        btnViolet = findViewById(R.id.btnViolet);
        lblViolet = findViewById(R.id.lblViolet);
        btnJonson = findViewById(R.id.btnJonson);
        lblJonson = findViewById(R.id.lblJonson);
        btnRose = findViewById(R.id.btnRose);
        lblRose = findViewById(R.id.lblRose);
        btnMayko = findViewById(R.id.btnMayko);
        lblMayko = findViewById(R.id.lblMayko);
        btnKimura = findViewById(R.id.btnKimura);
        lblKimura = findViewById(R.id.lblKimura);

        //exemplo escutador pros agentes pra dar cura
        EscutadorAgentes ea = new EscutadorAgentes();
        btnAmy.setOnClickListener(ea);
        btnCollin.setOnClickListener(ea);
        btnJenna.setOnClickListener(ea);
        btnViolet.setOnClickListener(ea);
        btnJonson.setOnClickListener(ea);
        btnRose.setOnClickListener(ea);
        btnMayko.setOnClickListener(ea);
        btnKimura.setOnClickListener(ea);

        btnVai.setOnClickListener( new MostrarMais() );
        btnVolta.setOnClickListener( new MostrarMenos() );
    }

    private class MostrarMais implements  View.OnClickListener{

        @Override
        public void onClick(View view) {
            btnAmy.setVisibility(View.INVISIBLE);
            lblAmy.setVisibility(View.INVISIBLE);
            btnCollin.setVisibility(View.INVISIBLE);
            lblCollin.setVisibility(View.INVISIBLE);
            btnJenna.setVisibility(View.INVISIBLE);
            lblJenna.setVisibility(View.INVISIBLE);
            btnViolet.setVisibility(View.INVISIBLE);
            lblViolet.setVisibility(View.INVISIBLE);
            btnJonson.setVisibility(View.INVISIBLE);
            lblJonson.setVisibility(View.INVISIBLE);
            btnRose.setVisibility(View.INVISIBLE);
            lblRose.setVisibility(View.INVISIBLE);

            btnMayko.setVisibility(View.VISIBLE);
            lblMayko.setVisibility(View.VISIBLE);
            btnKimura.setVisibility(View.VISIBLE);
            lblKimura.setVisibility(View.VISIBLE);
        }
    }

    private class MostrarMenos implements  View.OnClickListener{

        @Override
        public void onClick(View view) {
            btnMayko.setVisibility(View.INVISIBLE);
            lblMayko.setVisibility(View.INVISIBLE);
            btnKimura.setVisibility(View.INVISIBLE);
            lblKimura.setVisibility(View.INVISIBLE);

            btnAmy.setVisibility(View.VISIBLE);
            lblAmy.setVisibility(View.VISIBLE);
            btnCollin.setVisibility(View.VISIBLE);
            lblCollin.setVisibility(View.VISIBLE);
            btnJenna.setVisibility(View.VISIBLE);
            lblJenna.setVisibility(View.VISIBLE);
            btnViolet.setVisibility(View.VISIBLE);
            lblViolet.setVisibility(View.VISIBLE);
            btnJonson.setVisibility(View.VISIBLE);
            lblJonson.setVisibility(View.VISIBLE);
            btnRose.setVisibility(View.VISIBLE);
            lblRose.setVisibility(View.VISIBLE);


        }
    }

    private class EscutadorAgentes implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            Intent j = new Intent( getApplicationContext(), T8_Tratamento.class );
            switch (b.getId()){
                case R.id.btnAmy:
                    //abrir novo intent com img
                    j.putExtra("nome", "Amy Carter");
                    startActivity(j);
                    break;
                case  R.id.btnCollin:
                    j.putExtra("nome", "Collin Auburg");
                    startActivity(j);
                    break;
                case R.id.btnJenna:
                    j.putExtra("nome", "Jenna Washington");
                    startActivity(j);
                    break;
                case  R.id.btnViolet:
                    j.putExtra("nome", "Violet Flaucus");
                    startActivity(j);
                    break;
                case  R.id.btnJonson:
                    j.putExtra("nome", "Jonson Donley");
                    startActivity(j);
                    break;
                case  R.id.btnRose:
                    j.putExtra("nome", "Rose Naciroff");
                    startActivity(j);
                    break;
                case  R.id.btnMayko:
                    j.putExtra("nome", "Mayko Tucker");
                    startActivity(j);
                    break;
                case  R.id.btnKimura:
                    j.putExtra("nome", "Kimura Otosuri");
                    startActivity(j);
                    break;

            }
        }
    }
}
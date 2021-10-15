package peu.example.theesoterroristgame;

import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class T10_Agentes extends AppCompatActivity {

    Cursor cursorAgentes;
    AdapterAgentes adapterAgentes;
    SQLiteDatabase bd;


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

        EscutadorFecha ef = new EscutadorFecha();
        btnFecha.setOnClickListener(ef);

        // criando cursor com os dados vindos do banco
        cursorAgentes = bd.rawQuery( "SELECT _rowid_ _id, nome, historia, vida, esquiva, ataque, contraataque, arma, status FROM agentes", null );

        // criando o objeto adapter, passando o cursor
        adapterAgentes = new AdapterAgentes(this, cursorAgentes );




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

            // criando cursor com os dados vindos do banco
            switch (b.getId()){


                case R.id.btnAmyA:
                    imgBranco.setImageResource(R.drawable.agente_amycarter);
                    //String nome = "Amy Carter";
                    //cursorAgentes = bd.rawQuery( "SELECT nome, historia, vida, esquiva, ataque, contraataque, arma FROM agentes WHERE nome = '"+nome+"' ", null );


                    // ATENÇÃO PASSAR POR PARAMETRO ALGUMA COISA E CHAMAR O ADPATER
                    // PRA  IGUAL O OUTRO LA E SEGUIR A VIDA


                    //REC INFO DO BDD

                    lblNomeA.setText( "Amy Carter" ); //dps pega do BDD agr é só pra aparecer msm
                    break;
                case  R.id.btnCollinA:
                    imgBranco.setImageResource(R.drawable.agente_collinauburg);
                    lblNomeA.setText( "Collin Auburg" );
                    break;
                case R.id.btnJennaA:
                    imgBranco.setImageResource(R.drawable.agente_jennawashington);
                    lblNomeA.setText( "Jenna Washington" );
                    break;
                case  R.id.btnVioletA:
                    imgBranco.setImageResource(R.drawable.agente_violetflaucus);
                    lblNomeA.setText( "Violet Flaucus" );
                    break;
                case  R.id.btnJonsonA:
                    imgBranco.setImageResource(R.drawable.agente_jonsondonley);
                    lblNomeA.setText( "Jonson Donley" );
                    break;
                case  R.id.btnRoseA:
                    imgBranco.setImageResource(R.drawable.agente_rosenaciroff);
                    lblNomeA.setText( "Rose Naciroff" );
                    break;
                case  R.id.btnMaykoA:
                    imgBranco.setImageResource(R.drawable.agente_maykotucker);
                    lblNomeA.setText( "Mayko Tucker" );
                    break;
                case  R.id.btnKimuraA:
                    imgBranco.setImageResource(R.drawable.agente_kimuraotosuri);
                    lblNomeA.setText( "Kimura Otosuri" );
                    break;

            }
        }
    }
    private class EscutadorFecha implements View.OnClickListener {

        @Override
        public void onClick(View view) {

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

        }
    }
}
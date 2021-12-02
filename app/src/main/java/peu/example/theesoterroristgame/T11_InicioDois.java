package peu.example.theesoterroristgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class T11_InicioDois extends AppCompatActivity {

    SQLiteDatabase bd;
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

        btnConfig = findViewById(R.id.btnConfigM);
        btnConfig.setOnClickListener( new AbrirConfig() );
    }

    private class NovoJogo implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            //cria caixa de dialogo, deefine titulo, texto, btn ação positiva e negativa
            AlertDialog.Builder dialogo = new AlertDialog.Builder( T11_InicioDois.this);
            dialogo.setTitle("TEM CERTEZA ?");
            dialogo.setMessage("Seu progresso atual será deletado.");
            dialogo.setPositiveButton( "Sim", new T11_InicioDois.EscutadorSim() );
            dialogo.setNegativeButton( "Não", new T11_InicioDois.EscutadorNao() );

            //proibe usuario de fazer outra coisa, TEM QUE ESCOLHER
            dialogo.setCancelable( false );

            //cria e exibe
            dialogo.create();
            dialogo.show();

        }
    }

    private class AbrirConfig implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent j = new Intent( getApplicationContext(), T13_Configuracao.class );
            startActivity(j);
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
            // BANCO DE DADOS
            bd = openOrCreateDatabase( "theesoterroristbd", MODE_PRIVATE, null );
            String cmd;

            // DELETA OS AGENTES, USUARIO, E ARMAS
            cmd = "DROP TABLE agentes";
            bd.execSQL( cmd );
            cmd = "DROP TABLE usuario";
            bd.execSQL( cmd );
            cmd = "DROP TABLE armas";
            bd.execSQL( cmd );

            //CRIA AS TABLE NOVAMENTE, EXCETO A DO USUARIO
            // ----- AGENTES -----
            cmd = "CREATE TABLE IF NOT EXISTS agentes ";
            cmd = cmd + "( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, historia VARCHAR, vidaat INTEGER, vidamax INTEGER, esquiva INTEGER, ataque INTEGER, contraataque INTEGER, arma VARCHAR, status TINYINT)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Amy Carter', 'Após fugir de seu país de origem, abrigou-se em locais abandonados e precários. Certa vez, alocou-se onde a membrana estava muito fraca e acabou sendo atacada por criaturas. Para sua sorte, agentes da Ordem conseguiram salvá-la a tempo.', 15, 15, 9, 12, 8, 'Rifle', 1)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Collin Auburg', 'Após o repentino desaparecimento de seu melhor amigo, afastou-se do emprego e iniciou uma busca por ele. Com seu grande potencial em investigação, acabou encontrando a Ordem, onde ingressou, acreditando conseguir ajuda em sua busca.', 16, 16, 9, 13, 8, 'Glock', 1)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Rose Naciroff', 'Após presenciar a morte de seu batalhão e capitão, a ex-Co-Capitã das forças armadas do Peru se juntou à Ordem para proteger o maior número de pessoas, não deixando que o mesmo acontecesse a outros.', 17, 17, 9, 14, 9, 'Rifle', 0)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Jonson Donley', 'Antigo corretor de imóveis que morava com sua esposa e filhos. Certa noite, acampando próximo a um chalé, presenciou o exício de sua família. Tomado pelo ódio e repúdio, começa a caçar a criatura por conta própria. Quando encontra o local em que o monstro se esconde, é surpreendido por duas pessoas, depois apresentadas como Rose e Kimura, que já estavam no local lidando com a aberração e, com isso, ingressa na ordem. ', 14, 14, 10, 11, 7, 'Arco e Flecha', 0)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Kimura Otosuri', 'Posterior a recôndita morte de seus pais, é recrutado ainda criança pela Ordem, onde é tratado, educado e treinado para destruir criaturas paranormais.', 18, 18, 15, 10, 6, 'Adaga', 0)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Jenna Washington', 'Após desvincular-se de sua família, procurou vários meios para se manter. Sem saber do perigo, aloca-se em uma residência abandonada, que semanas depois é invadida por um grupo de pessoas armadas revelando que na casa havia uma criatura ignóbil mortal.', 16, 16, 12, 11, 7, 'Glock', 0)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Violet Flaucus', 'Violet vivia com a família em uma cabana no interior, afastada da civilização. Como seus pais eram de idade, ela cuidava dos afazeres de casa, saía para comprar comida e utensílios. Subitamente, quando voltava para casa à noite, encontra sua casa arrombada e seus pais mortos. Acaba encontrando agentes da Ordem dias depois, enquanto eles vasculhavam o local.', 17, 17, 9, 14, 9, 'Machado', 0)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO agentes (nome, historia, vidaat, vidamax, esquiva, ataque, contraataque, arma, status) values ( 'Mayko Tucker', 'Entrou na Ordem junto de seu irmão, John Tucker quando ainda era pequeno. Por passar a viver lá, começou a se afeiçoar por armas, especificamente shotguns. Ágil e esperto, Mayko se mostrou um ótimo agente. ', 15, 15, 11, 11, 7, 'Shotgun', 0)";
            bd.execSQL( cmd );

            // ----- ARMAS -----
            cmd = "CREATE TABLE IF NOT EXISTS armas ";
            cmd = cmd + "( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, dano INTEGER)";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'Rifle', 12 )";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'Shotgun', 24 )";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'Machado', 15 )";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'Adaga', 13 )";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'ArcoFlecha', 10 )";
            bd.execSQL( cmd );
            cmd = "INSERT INTO armas (nome, dano) values ( 'Glock', 12 )";
            bd.execSQL( cmd );

            Intent j = new Intent( getApplicationContext(), T2_InicioCadastro.class );
            startActivity(j);
        }
    }
}
package peu.example.theesoterroristgame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class T2_InicioCadastro extends AppCompatActivity {

    private EditText txtNick;
    private EditText txtSenha;
    private Button btnSalvar;

    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_inicio_cadastro);

        txtNick =  findViewById( R.id.txtNick );
        txtSenha = findViewById( R.id.txtSenha );
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener( new SalvarDados() );


        // BANCO DE DADOS
        bd = openOrCreateDatabase( "theesoterroristbd", MODE_PRIVATE, null );
        // CRIAÇÃO E INSERÇÃO DOS DADOS DO USUARIO NO BD E EXECUÇÃO


        String cmd;

        // LIMPA A TABELA PARA SEMPRE HAVER UM SÓ USUARIO - COMO FAZER
        cmd = "DROP TABLE usuario";
        bd.execSQL( cmd );

        cmd = "CREATE TABLE IF NOT EXISTS usuario ";
        cmd = cmd + "( id INTEGER PRIMARY KEY AUTOINCREMENT, nick VARCHAR, senha VARCHAR, dinheiro INTEGER)";
        bd.execSQL( cmd );

    }

    private class SalvarDados implements View.OnClickListener {

        String cmd;

        @Override
        public void onClick(View view) {

            String nick, senha;

            nick = txtNick.getText().toString();
            senha = txtSenha.getText().toString();

            Intent i = new Intent( getApplicationContext(), T3_Historia.class );

            if (nick.equals("") || senha.equals("")) {
                Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
            }
            else{
                String cmd = "INSERT INTO usuario (nick, senha, dinheiro) VALUES ('";
                cmd = cmd + nick;
                cmd = cmd + "', '";
                cmd = cmd + senha;
                cmd = cmd + "', '";
                cmd = cmd + 10000;
                cmd = cmd + "')";
                bd.execSQL( cmd );
                startActivity(i);
            }

        }
    }
}
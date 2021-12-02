package peu.example.theesoterroristgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class T13_Configuracao extends AppCompatActivity {

    private Button btnMenu;
    private Button btnSom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t13_configuracao);

        btnMenu = findViewById(R.id.btnMenu);
        btnSom = findViewById(R.id.btnSom);

        Ir ir = new Ir();
        btnMenu.setOnClickListener(ir);
        btnSom.setOnClickListener(ir);
    }

    private class Ir implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            switch (b.getId()){
                case R.id.btnSom:
                    //Intent e = new Intent( getApplicationContext(), T7_Enfermaria.class );
                    //startActivity(e);
                    Toast.makeText(getApplicationContext() , "Em breve...", Toast.LENGTH_SHORT).show();
                    break;
                case  R.id.btnMenu:
                    Intent a = new Intent( getApplicationContext(), T11_InicioDois.class );
                    startActivity(a);
                    break;

            }
        }
    }
}
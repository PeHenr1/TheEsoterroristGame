package peu.example.theesoterroristgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class T5_FinalizaTutorial extends AppCompatActivity {

    private Button btnSeguinte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5_finaliza_tutorial);

        btnSeguinte = (Button) findViewById(R.id.btnSeguinte);
        btnSeguinte.setOnClickListener( new Prosseguir() );
    }

    private class Prosseguir implements View.OnClickListener {

                @Override
                public void onClick(View view) {
                    Intent j = new Intent( getApplicationContext(), T6_Base.class );
                    startActivity(j);
                }
    }
}
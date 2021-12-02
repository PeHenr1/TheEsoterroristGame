package peu.example.theesoterroristgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class T12_Mapa extends AppCompatActivity {

    private Button btnBase;
    private Button btnIconConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t12_mapa);

        btnBase = findViewById(R.id.btnBase);

        AbrirTela at = new AbrirTela();

        btnBase.setOnClickListener(at);
    }

    private class AbrirTela implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            switch (b.getId()){

                case R.id.btnBase:
                    Intent aj = new Intent( getApplicationContext(), T6_Base.class );
                    startActivity(aj);
                    break;
            }
        }
    }
}
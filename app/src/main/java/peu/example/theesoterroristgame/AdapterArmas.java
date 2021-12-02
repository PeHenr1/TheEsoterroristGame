package peu.example.theesoterroristgame;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterArmas extends CursorAdapter {

    public AdapterArmas(Context context, Cursor cursor) {

        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemDaLista = inflater.inflate(R.layout.arma_lista, parent, false);
        return itemDaLista;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView armaImg = view.findViewById(R.id.armaImg);
        TextView lblArmaN = view.findViewById(R.id.lblArmaN);
        TextView lblArmaD = view.findViewById(R.id.lblArmaD);

        String nome = cursor.getString( cursor.getColumnIndex("nome") );
        String dano = cursor.getString( cursor.getColumnIndex("dano") );

        lblArmaN.setText( nome );
        lblArmaD.setText( dano );

        if(nome.equals("Rifle")){
            armaImg.setImageResource(R.drawable.rifle);
        }
        if(nome.equals("Shotgun")){
            armaImg.setImageResource(R.drawable.shotgun);
        }
        if(nome.equals("Machado")){
            armaImg.setImageResource(R.drawable.machado);
        }
        if(nome.equals("Adaga")){
            armaImg.setImageResource(R.drawable.adaga);
        }
        if(nome.equals("ArcoFlecha")){
            armaImg.setImageResource(R.drawable.arcoflecha);
        }
        if(nome.equals("Glock")){
            armaImg.setImageResource(R.drawable.glock);
        }
    }
}



    // AQUI É O CÓDIGO ANTIGO, SEM O BDD, QUANDO EU USAVA COM A CLASSE
    /*
    public class AdapterArmas extends ArrayAdapter<Arma> {

        // contexto
        private Context context;

        private ArrayList<Arma> armas;

        public AdapterArmas(Context context, ArrayList<Arma> armas) {

            super(context, R.layout.arma_lista, armas);
            this.context = context;
            this.armas = armas;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater li = LayoutInflater.from(parent.getContext());

            View itemView = li.inflate(R.layout.arma_lista, parent, false);

            ImageView armaImg = itemView.findViewById(R.id.armaImg);
            TextView lblArmaN = itemView.findViewById(R.id.lblArmaN);
            TextView lblArmaD = itemView.findViewById(R.id.lblArmaD);

            lblArmaN.setText(armas.get(position).getNome());
            lblArmaD.setText(armas.get(position).getDano());

            String armaN = (armas.get(position).getNome());
            //String armaD = (armas.get(position).getDano());

            //Toast.makeText(getContext(), armaN+" "+armaD, Toast.LENGTH_SHORT).show();

            if(armaN.equals("Rifle")){
                armaImg.setImageResource(R.drawable.rifle);

            }
            if(armaN.equals("Shotgun")){
                armaImg.setImageResource(R.drawable.shotgun);
            }
            if(armaN.equals("Machado")){
                armaImg.setImageResource(R.drawable.machado);
            }
            if(armaN.equals("Adaga")){
                armaImg.setImageResource(R.drawable.adaga);
            }
            if(armaN.equals("Arco e Flecha")){
                armaImg.setImageResource(R.drawable.arcoflecha);
            }
            if(armaN.equals("Glock")){
                armaImg.setImageResource(R.drawable.glock);
            }

            return itemView;
        }
    }

     */






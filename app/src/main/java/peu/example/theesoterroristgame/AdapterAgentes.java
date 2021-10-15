package peu.example.theesoterroristgame;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdapterAgentes extends CursorAdapter {
    public AdapterAgentes(  Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }

    // O método bindView pega os dados lidos do bd no cursor, e coloca no item da lista

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView lblNomeA = view.findViewById( R.id.lblNomeA );
        TextView lblArmaA = view.findViewById( R.id.lblArmaA );

         //extrair as informações da posição atual do cursor

        String nome = cursor.getString( cursor.getColumnIndex("nome") );
        String arma = cursor.getString( cursor.getColumnIndex( "arma"));

        lblNomeA.setText( nome );
        lblArmaA.setText( arma );
    }
}

package cindy.app_cindy;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Chat> {
    private final Context context;
    private final ArrayList<Chat> elementos;

    public ChatAdapter(Context context, ArrayList<Chat> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView mensagem = (TextView) rowView.findViewById(R.id.mensagem);
        TextView mensagem2 = (TextView) rowView.findViewById(R.id.mensagem2);
        if(elementos.get(position).getId()==1) {
            mensagem2.setText(elementos.get(position).getMensagem());
            mensagem2.setBackgroundResource(R.drawable.bubble_yellow);
        }
        else {
            mensagem.setText(elementos.get(position).getMensagem());
            mensagem.setBackgroundResource(R.drawable.bubble_green);
        }
        return rowView;
    }


}
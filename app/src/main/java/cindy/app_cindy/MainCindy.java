package cindy.app_cindy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainCindy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cindy);

        ListView lista = (ListView)findViewById(R.id.lvEscolas);
        ArrayAdapter adapter = new ChatAdapter(this, adicionarChat());
        lista.setAdapter(adapter);


    }
    private ArrayList<Chat> adicionarChat() {
        ArrayList<Chat> escolas = new ArrayList<Chat>();
        Chat c = new Chat("Cindy: ","Olá, tudo bem? como possa ajudá-lo?", 1);
        escolas.add(c);
        c = new Chat("User: ","Me vê uma pizza!", 2);
        escolas.add(c);
        c = new Chat("Cindy: ","Ok. Qual sabor você gostaria de escolher?", 1);
        escolas.add(c);
        c = new Chat("User: ","Meia frango com catupiri e meia calabreza.", 2);
        escolas.add(c);
        return escolas;
    }
}

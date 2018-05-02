package cindy.app_cindy;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainCindy extends DebugActivity {

    private TextView txtUser;
    private int varNumber;
    private int progressoTotal = 0;
    private String progressoUser[] = new String[1000];
    private String progressoCindy[] = new String[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cindy);


        ListView lista = (ListView)findViewById(R.id.lvEscolas);
        ArrayAdapter adapter = new ChatAdapter(this, adicionarChat());
        lista.setAdapter(adapter);

        txtUser = (TextView) findViewById(R.id.txtUser);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Cindy");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            toast("Clicou no Search!");
            return true;
        } else if (id == R.id.action_refresh) {
            toast("Clicou no Refresh!");
            return true;
        } else if (id == R.id.action_settings) {
            toast("Clicou no Settings!");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void enviar(View v){
        if(txtUser.length()!=0) {
            int val = txtUser.length();
            String var = String.valueOf(val);
            this.varNumber = val;

            ArrayAdapter adapter2 = new ChatAdapter(this, adicionarChat2());
            ListView lista = (ListView) findViewById(R.id.lvEscolas);
            lista.setAdapter(adapter2);


            Toast.makeText(this, var, Toast.LENGTH_SHORT).show();
            adicionarChat2();
            EditText send = (EditText) findViewById(R.id.txtUser);
            send.setText("");
            this.progressoTotal = this.progressoTotal - 1;
        }

    }


    public ArrayList<Chat> adicionarChat2(){

        this.progressoUser[this.progressoTotal] = txtUser.getText().toString();
        this.progressoCindy[this.progressoTotal] = "Enviado texto de número " + String.valueOf(this.progressoTotal+1);
        ArrayList<Chat> Chat3 = new ArrayList<Chat>();
        int i;

            for (i = -1; i < progressoTotal; i++) {
                Chat c = new Chat(this.progressoUser[i+1], 2,varNumber);
                Chat3.add(c);
                c = new Chat(this.progressoCindy[i+1], 1,varNumber);
                Chat3.add(c);
            }
        this.progressoTotal = this.progressoTotal + 1;
        return Chat3;
    }

    private ArrayList<Chat> adicionarChat() {
        ArrayList<Chat> Chat2 = new ArrayList<Chat>();
        Chat c = new Chat("Olá, tudo bem? como posso ajudá-lo?", 1, 0);
        Chat2.add(c);

        return Chat2;
    }


}

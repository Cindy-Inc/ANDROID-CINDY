package cindy.app_cindy;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainCindy extends DebugActivity {

    private TextView txtUser;
    private int varNumber;
    private int progressoTotal = 0;
    private String progressoUser[] = new String[1000];
    private String progressoCindy[] = new String[1000];
    private int verdade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cindy);
        ListView lista = (ListView) findViewById(R.id.lvEscolas);
        ArrayAdapter adapter = new ChatAdapter(this, adicionarChat());
        lista.setAdapter(adapter);

        txtUser = (TextView) findViewById(R.id.txtUser);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Cindy");
    }

 /*   public void logar(View v) {
        TextView tLogin = (TextView) findViewById(R.id.tLogin);
        TextView tSenha = (TextView) findViewById(R.id.tSenha);
        String login = tLogin.getText().toString();
        String senha = tSenha.getText().toString();
        if (login.equals("admin") && senha.equals("admin")) {

            this.verdade = 1;
            setContentView(R.layout.activity_main_cindy);
            ListView lista = (ListView) findViewById(R.id.lvEscolas);
            ArrayAdapter adapter = new ChatAdapter(this, adicionarChat());
            lista.setAdapter(adapter);

            txtUser = (TextView) findViewById(R.id.txtUser);
            ActionBar actionBar = getActionBar();
            actionBar.setTitle("Cindy");


        } else {
            alert("Login e senha incorretos.");
        }
    }*/

    private void alert(String s){
        //A classe Toast mostra um alerta temporário muito comum no Android
        Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());
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
            toast("Clicou nas configurações!");
            return true;
        } else if (id == R.id.action_logout) {
            toast("Clicou no logout!");

            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Usuário fez a busca
                toast("Buscar o texto: "+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Mudou o texto digitado
                return false;
            }
        };

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

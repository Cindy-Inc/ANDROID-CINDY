package cindy.app_cindy;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MainCindy extends DebugActivity {

    private TextView txtUser;
    private int varNumber;
    private int progressoTotal = 0;
    private String progressoUser[] = new String[1000];
    private String progressoCindy[] = new String[1000];
    JSONObject context;

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
            adicionarChat2();

            Toast.makeText(this, var, Toast.LENGTH_SHORT).show();
            //adicionarChat2();
            EditText send = (EditText) findViewById(R.id.txtUser);
            send.setText("");

        }

    }

    public void updateChat(ArrayList<Chat> chatArr) {
        ArrayAdapter adapter2 = new ChatAdapter(this, chatArr);
        ListView lista = (ListView) findViewById(R.id.lvEscolas);
        lista.setAdapter(adapter2);
        //this.progressoTotal = this.progressoTotal - 1;
    }


    public void adicionarChat2() {

        this.progressoUser[this.progressoTotal] = txtUser.getText().toString().replaceAll("\n", "<br>");

        final String URL = "https://cindy-app.mybluemix.net/api/mensagem/";

        try{
            JSONObject envio = new JSONObject();
            JSONObject input = new JSONObject();
            input.put("text", (String) this.progressoUser[this.progressoTotal]);
            envio.put("input", input);
            if(context != null) {
                envio.put("context", context);
            }

            RequestQueue fila = Volley.newRequestQueue(this);
            Requisicao req = new Requisicao();
            ErroRequisicao erro = new ErroRequisicao();
            JsonObjectRequest reqJson = new JsonObjectRequest(URL, envio, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // display response
                        JSONObject jObj = response.getJSONObject("output");
                        context = response.getJSONObject("context");
                        Log.i("INFO", jObj.getString("text").toString());
                        JSONArray arr = jObj.getJSONArray("text");
                        progressoCindy[progressoTotal] = arr.getString(0);
                        Log.d("Response", response.toString());

                        ArrayList<Chat> Chat3 = new ArrayList<Chat>();
                        int i;

                        for (i = -1; i < progressoTotal; i++) {
                            Chat c = new Chat(progressoUser[i + 1], 2, varNumber);
                            Chat3.add(c);
                            c = new Chat(progressoCindy[i + 1], 1, varNumber);
                            Chat3.add(c);
                        }
                        progressoTotal = progressoTotal + 1;

                        updateChat(Chat3);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
            );
            fila.add(reqJson);

        }catch(JSONException e)
        {
            e.printStackTrace();
        }


    }

    private ArrayList<Chat> adicionarChat() {
        ArrayList<Chat> Chat2 = new ArrayList<Chat>();
        Chat c = new Chat("Digite uma mensagem para falar com a assistente virtual.", 1, 0);
        Chat2.add(c);

        return Chat2;
    }
}

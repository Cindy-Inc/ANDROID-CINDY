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

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainCindy extends DebugActivity {

    private TextView txtUser;
    private int varNumber;
    private int progressoTotal = 0;
    private String progressoUser[] = new String[1000];
    private String progressoCindy[] = new String[1000];
    private List<String[]> progresso = new ArrayList<>();
    JSONObject context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cindy);
        ListView lista = (ListView) findViewById(R.id.lvEscolas);
        ArrayAdapter adapter = new ChatAdapter(this, adicionarChat());
        lista.setAdapter(adapter);

        txtUser = (TextView) findViewById(R.id.txtUser);
       // ActionBar actionBar = getActionBar();
       // actionBar.setTitle("Cindy");
    }

    private void alert(String s){
        //A classe Toast mostra um alerta temporário muito comum no Android
        Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());
        return true;
    }*/
   // @Override
   // public boolean onOptionsItemSelected(MenuItem item) {
       // int id = item.getItemId();
       // if (id == R.id.action_search) {
            //toast("Clicou no Search!");
            //return true;
        //} /*else if (id == R.id.action_refresh) {
           // toast("Clicou no Refresh!");
           // return true;
        //}*/ else if (id == R.id.action_settings) {
           // toast("Clicou nas configurações!");
          //  return true;
        //} else if (id == R.id.action_logout) {
           // toast("Clicou no logout!");

           // finish();

         //   return true;
       // }
      //  return super.onOptionsItemSelected(item);
    //}
   /* private SearchView.OnQueryTextListener onSearch(){
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

    }*/

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void enviar(View v){
        if(txtUser.length()!=0) {
            int val = txtUser.length();
            String var = String.valueOf(val);
            this.varNumber = val;
            adicionarChat2();

            //Toast.makeText(this, var, Toast.LENGTH_SHORT).show();
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

//        this.progressoUser[this.progressoTotal] = txtUser.getText().toString().replaceAll("\n", "<br>");
        String[] userTxt = {"user", txtUser.getText().toString().replaceAll("\n", "<br>")};
        this.progresso.add(userTxt);

        final String URL = "https://3cindy-app.mybluemix.net/api/v1/message/";

        try{
            JSONObject envio = new JSONObject();
            envio.put("text", userTxt[1]);
            if(context != null) {
                envio.put("context", context);
            }

            RequestQueue fila = Volley.newRequestQueue(this);
            JsonObjectRequest reqJson = new JsonObjectRequest(URL, envio, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // display response
                        JSONObject respObj = response.getJSONObject("response");
                        JSONObject jObj = respObj.getJSONObject("output");
                        context = respObj.getJSONObject("context");

                        JSONArray genericObj = jObj.getJSONArray("generic");
                        for (int i =0; i < genericObj.length(); i++) {
                            JSONObject obj = genericObj.getJSONObject(i);
//                            progressoCindy[progressoTotal] = obj.getString("text");
                            String cindyTxt[] = {"cindy", obj.getString("text")};
                            progresso.add(cindyTxt);
                        }
//                        JSONArray arr = jObj.getJSONArray("text");
//                        progressoCindy[progressoTotal] = arr.getString(0);
                        Log.d("Response", response.toString());

                        ArrayList<Chat> Chat3 = new ArrayList<Chat>();

                        for(int i = 0; i < progresso.size(); i++) {
                            if (progresso.get(i)[0].equals("cindy")) {
                                Chat3.add(new Chat(progresso.get(i)[1], 1, varNumber));
                            } else {
                                Chat3.add(new Chat(progresso.get(i)[1], 2, varNumber));
                            }
                        }
                        updateChat(Chat3);


//                        for (int i = -1; i < progressoTotal; i++) {
//                            Chat c = new Chat(progressoUser[i + 1], 2, varNumber);
//                            Chat3.add(c);
//                            c = new Chat(progressoCindy[i],1, varNumber);
//                            Chat3.add(c);
//                        }
//                        progressoTotal = progressoTotal + 1;


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
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YmFmZmU0ODNkNTU3MDA3MDVmNWJkYWIiLCJ0eXBlIjoidXN1YXJpbyIsImVtYWlsIjoiYWRtaW5AY2luZHkuY28iLCJuYW1lIjoiQ0lORFkiLCJhY3RpdmUiOnRydWUsImFkZHJlc3MiOiJBdi4gTGlucyBkZSBWYXNjb25jZWxvcyIsIm51bWJlciI6IjExNTciLCJpYXQiOjE1MzgyNjcxOTZ9.0M7mxcnd33ZPIHwDpzvU20GBDGt4qC-db3-VQ_1OApQ");
                    return headers;
                }
            };
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

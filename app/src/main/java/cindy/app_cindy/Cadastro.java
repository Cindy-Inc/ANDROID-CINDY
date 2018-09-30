package cindy.app_cindy;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends DebugActivity {

    Map<String, String> usuario = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Faça o seu cadastro:");
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            // O método finish() vai encerrar essa activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cadastro1(View v){

        String nome = ((EditText) findViewById(R.id.txtNome1)).getText().toString();
        String email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
        String senha = ((EditText) findViewById(R.id.txtSenha)).getText().toString();
        String confirmaSenha = ((EditText) findViewById(R.id.txtSenha2)).getText().toString();

        usuario.put("nome", nome);
        usuario.put("email", email);
        usuario.put("senha", senha);

        setContentView(R.layout.cadastro2);

    }

    public void cadastro2(View v){

        String numeroCartao = ((EditText) findViewById(R.id.txtNumCard)).getText().toString();
        String codigoSeguranca = ((EditText) findViewById(R.id.txtSafety)).getText().toString();
        String nomeCartao = ((EditText) findViewById(R.id.txtNameCard)).getText().toString();
        String dataValidade = ((EditText) findViewById(R.id.editText4)).getText().toString();

        usuario.put("numeroCartao", numeroCartao);
        usuario.put("codigoSeguranca", codigoSeguranca);
        usuario.put("nomeCartao", nomeCartao);
        usuario.put("dataValidade", dataValidade);

        setContentView(R.layout.cadastro3);

    }

    public void cadastro3(View v){
        String cpf = ((EditText) findViewById(R.id.txtNumCPF)).getText().toString();
        String nomeUsuario = ((EditText) findViewById(R.id.nomeUsuario)).getText().toString();

        usuario.put("cpf", cpf);
        usuario.put("usuario", nomeUsuario);

        cadastrar();
    }

    public void cadastrar() {
        String url = "http://10.0.2.2:3000/cadastro";
        try {
            RequestQueue fila = Volley.newRequestQueue(this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    return usuario;
                }
            };
            fila.add(postRequest);
        }catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

}
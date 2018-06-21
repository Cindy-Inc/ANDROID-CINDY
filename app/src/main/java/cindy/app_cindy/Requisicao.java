package cindy.app_cindy;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Requisicao implements Response.Listener<JSONObject>{

    String resp;

    @Override
    public void onResponse(JSONObject response) {

        try{
            JSONObject jObj = response.getJSONObject("output");
            //DebugActivity.resposta = jObj.getJSONArray("text").toString();
            Log.i("INFO", jObj.getString("text").toString());
            JSONArray arr = jObj.getJSONArray("text");
            String abc = null;
            int a = abc.indexOf(1);
            resp = arr.getString(0);
            Log.i("INFO", resp);



            // String s = Log.i(jObj.getString("text").toString());
            // Toast.makeText(this, test, Toast.LENGTH_LONG).show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String resposta(String res){


        return res;
    }
}

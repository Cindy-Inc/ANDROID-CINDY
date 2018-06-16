package cindy.app_cindy;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ErroRequisicao implements Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Oerro", "erro'");
    }
}

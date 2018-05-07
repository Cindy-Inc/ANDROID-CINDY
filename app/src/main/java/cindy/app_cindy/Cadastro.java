package cindy.app_cindy;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Cadastro extends DebugActivity {
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

    public void cadastro2(View v){

        setContentView(R.layout.cadastro2);

    }
    public void cadastro3(View v){

        setContentView(R.layout.cadastro3);

    }
}
package ao.uan.fc.dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView headerUser;
    //private String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerUser = findViewById(R.id.headerUsuario);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if(user !=null ){
                String usuario =  user.getEmail();
                headerUser.setText(usuario);
            }

            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        

        if(id == R.id.Sair){
            mAuth.signOut();
            finish();
        }

        return true;
    }

    public void consultar(View view){
        Intent intent = new Intent(MainActivity.this, PesquisarUseActivity.class);
        startActivity(intent);
    }

    public void Galeria(View view){
        Intent intent = new Intent(MainActivity.this, GaleriaPhotoActivity.class);
        startActivity(intent);
    }
}

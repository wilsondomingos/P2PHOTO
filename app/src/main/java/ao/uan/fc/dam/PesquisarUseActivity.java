package ao.uan.fc.dam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PesquisarUseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView PesquisaNomes;
    private TextView textoNomes;
    private TextView textoEmails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_use);

       // ListarUser();

    }

    public  void Pesquisar(View view){
        PesquisaNomes = findViewById(R.id.PesquisaNome);
        String NomePesquisas = PesquisaNomes.getText().toString().trim();

        textoNomes = findViewById(R.id.textoNome);
        textoEmails = findViewById(R.id.textoEmail);

        String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/user/pesquisar";

        Ion.with(PesquisarUseActivity.this)
                .load("POST",URL)
                .setBodyParameter("nome",NomePesquisas)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try{
                            for(int i=0; i < result.size(); i++){
                                JsonObject user = result.get(i).getAsJsonObject();
                               // Log.d("Cliente", user.get("name").getAsString());
                                //Toast.makeText(PesquisarUseActivity.this, user.get("name").getAsString(), Toast.LENGTH_SHORT).show();
                               // Toast.makeText(PesquisarUseActivity.this, user.get("email").getAsString(), Toast.LENGTH_SHORT).show();
                                String nome = user.get("name").getAsString();
                                String email =user.get("email").getAsString();

                                textoNomes.setText(nome);
                                textoEmails.setText(email);

                            }
                        }catch (Exception erro){
                            Toast.makeText(PesquisarUseActivity.this, "Erro ao Listar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
/*
    private void ListarUser(){

        String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/user/";

                Ion.with(PesquisarUseActivity.this)
                        .load(URL)
                        .asJsonArray()
                        .setCallback(new FutureCallback<JsonArray>() {
                            @Override
                            public void onCompleted(Exception e, JsonArray result) {
                                try{
                                    for(int i=0; i < result.size(); i++){
                                        JsonObject user = result.get(i).getAsJsonObject();
                                        Log.d("Cliente", user.get("name").getAsString());
                                        Toast.makeText(PesquisarUseActivity.this, user.get("name").getAsString(), Toast.LENGTH_SHORT).show();

                                    }
                            }catch (Exception erro){
                                    Toast.makeText(PesquisarUseActivity.this, "Erro ao Listar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

    }

*/
}

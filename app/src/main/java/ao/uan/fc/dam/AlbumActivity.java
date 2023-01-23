package ao.uan.fc.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import ao.uan.fc.dam.models.album;

public class AlbumActivity extends AppCompatActivity {
    private EditText editTextAlbum;
    private Button btnCrialAlbum;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

    }


    public  void Criar_album(View view){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            String userEmail = user.getEmail();
            String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/user/id";
            Ion.with(AlbumActivity.this)
                    .load("POST",URL)
                    .setBodyParameter("email",userEmail)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {
                            try{
                                for(int i=0; i < result.size(); i++){
                                    JsonObject user = result.get(i).getAsJsonObject();

                                    id  = user.get("id").getAsString();




                                }
                                editTextAlbum = findViewById(R.id.editTalbum);
                                String NomeAlbum = editTextAlbum.getText().toString().trim();
                                String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/albums";
                                Ion.with(AlbumActivity.this)
                                        .load("POST",URL)
                                        .setBodyParameter("titulo",NomeAlbum)
                                        .setBodyParameter("user_id",id)
                                        .asJsonArray()
                                        .setCallback(new FutureCallback<JsonArray>() {
                                            @Override
                                            public void onCompleted(Exception e, JsonArray result) {
                                                try{
                                                    Intent intent = new Intent(AlbumActivity.this, GaleriaPhotoActivity.class);
                                                    startActivity(intent);
                                                }catch (Exception erro){
                                                    Toast.makeText(AlbumActivity.this, "Erro no id", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }catch (Exception erro){
                                Toast.makeText(AlbumActivity.this, "Erro ao Criar Album", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }
}

package ao.uan.fc.dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import ao.uan.fc.dam.adapters.AlbumAdapter;
import ao.uan.fc.dam.models.album;

public class GaleriaPhotoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public String id;
    RecyclerView RecyclerViewAlbum;
    AlbumAdapter albumAdapter;
    List<album> ListaDeAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_photo);

        RecyclerViewAlbum =(RecyclerView)findViewById(R.id.RecyViewAlbum);

        ListaDeAlbum = new ArrayList<>();
        albumAdapter = new AlbumAdapter(GaleriaPhotoActivity.this,ListaDeAlbum);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GaleriaPhotoActivity.this);
        RecyclerViewAlbum.setLayoutManager(layoutManager);
        RecyclerViewAlbum.addItemDecoration(new DividerItemDecoration(GaleriaPhotoActivity.this, LinearLayoutManager.VERTICAL));
        RecyclerViewAlbum.setAdapter(albumAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            String userEmail = user.getEmail();
        String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/user/id";
        Ion.with(GaleriaPhotoActivity.this)
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

                            String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/albums/StoreAlbum";
                            Ion.with(GaleriaPhotoActivity.this)
                                    .load("POST",URL)
                                    .setBodyParameter("user_id",id)
                                    .asJsonArray()
                                    .setCallback(new FutureCallback<JsonArray>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonArray result) {
                                            try{
                                                for(int i=0; i < result.size(); i++){
                                                    JsonObject album = result.get(i).getAsJsonObject();

                                                    String Album  = album.get("titulo").getAsString();

                                                     album  NewAlbum =new album();
                                                    NewAlbum.setId(album.get("id").getAsInt());
                                                    NewAlbum.setTitulo(album.get("titulo").getAsString());
                                                    ListaDeAlbum.add(NewAlbum);

                                                    Toast.makeText(GaleriaPhotoActivity.this, Album, Toast.LENGTH_SHORT).show();


                                                }
                                                albumAdapter.notifyDataSetChanged();
                                            }catch (Exception erro){
                                                Toast.makeText(GaleriaPhotoActivity.this, "Erro no id", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }catch (Exception erro){
                            Toast.makeText(GaleriaPhotoActivity.this, "Erro no id", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }





    }





    public void CriarAlbum(View view){
        Intent intent = new Intent(GaleriaPhotoActivity.this, AlbumActivity.class);
        startActivity(intent);
    }

    public void AdicionarFoto(View view){
        Intent intent = new Intent(GaleriaPhotoActivity.this, FotoActivity.class);
        startActivity(intent);
    }
}

package ao.uan.fc.dam;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;

    private EditText editEmail;
    private EditText editSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth =FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.campoEmail);
        editSenha = findViewById(R.id.campoSenha);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
*/


    public  void  login(View view){
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if(email.equals("")){
            editEmail.setError("O campo Email é Obrigatorio");
            return;
        }
        if(senha.equals("")){
            editSenha.setError("O campo Senha é Obrigatorio");
            return;
        }



        mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateUI(mAuth.getCurrentUser());

                }else {
                    Toast.makeText(LoginActivity.this,"Utilizador não esta Logado ou incorreto",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    public  String id;
    private void updateUI(FirebaseUser user){

        if(user != null){


            Intent i = new Intent(LoginActivity.this, MainActivity.class);
           // i.putExtra("id",id);
            startActivity(i);
        }
    }

    public  void  registar(View view){
        Intent intent = new Intent(LoginActivity.this, registroActivity.class);
        startActivity(intent);
    }

}

package ao.uan.fc.dam;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class registroActivity extends Activity {

    private FirebaseAuth mAuth;
    private EditText registoNome;
    private EditText registoEmail;
    private EditText registoSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth =FirebaseAuth.getInstance();


        registoNome = findViewById(R.id.PesquisaNome);
        registoEmail = findViewById(R.id.registoemail);
        registoSenha = findViewById(R.id.registoSenha);
    }

    public  void  Salvar(View view){
       final String utilizador = registoNome.getText().toString().trim();
       final String email = registoEmail.getText().toString().trim();
             String senha = registoSenha.getText().toString().trim();

        if(utilizador.equals("")){
            registoNome.setError("O camo nome é Obrigatorio");
            registoNome.requestFocus();
            return;
        }
        if(email.equals("")){
            registoEmail.setError("O camo email é Obrigatorio");
            registoEmail.requestFocus();
            return;
        }
        if(senha.equals("")){
            registoSenha.setError("O camo senha é Obrigatorio");
            registoSenha.requestFocus();
            return;
        }
        salvarUser(utilizador,email,senha);




        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent i = new Intent(registroActivity.this, MainActivity.class);
                    startActivity(i);


                }else{

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        registoSenha.setError("Senha Fraca");
                        registoSenha.requestFocus();

                    }catch(FirebaseAuthInvalidCredentialsException e){
                        registoEmail.setError("Email invalido");
                        registoEmail.requestFocus();

                    }catch(FirebaseAuthUserCollisionException e){
                        registoEmail.setError("este Email ja existe");
                        registoEmail.requestFocus();

                    }catch(Exception e){
                        Log.e("Registo", e.getMessage());
                    }

                }
            }
        });

    }

    public void salvarUser(String nome, String email, String senha){

        String URL ="https://ofa.ao/p2photo/P2Foto_api/public/api/v1/user";

        Ion.with(registroActivity.this)
                .load("POST",URL)
                .setBodyParameter("name",nome)
                .setBodyParameter("email",email)
                .setBodyParameter("password",senha)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try{
                            String resultado = result.get("data").getAsString();
                    }catch (Exception erro){
                           // Toast.makeText(registroActivity.this, "Ops! erro ao Salvar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

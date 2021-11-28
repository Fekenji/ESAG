package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteracaoActivity extends AppCompatActivity {

    EditText etNovaSenha, etConfirmarNovaSenha;
    Button btnAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);

        etNovaSenha = (EditText) findViewById(R.id.etNovaSenha);
        etConfirmarNovaSenha = (EditText) findViewById(R.id.etConfirmarNovaSenha);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String novaSenha = etNovaSenha.getText().toString();

                if (novaSenha.trim().equals("")) {
                    Toast.makeText(AlteracaoActivity.this, "Digite o campo de senha!", Toast.LENGTH_LONG).show();
                } else {
                    String confirmarNovaSenha = etConfirmarNovaSenha.getText().toString();

                    if (!novaSenha.equals(confirmarNovaSenha)) {
                        Toast.makeText(getApplicationContext(), "As senhas precisam ser iguais!", Toast.LENGTH_SHORT).show();
                    } else {
                        Token token = new Token(getApplicationContext());

                        String email = token.getToken().toString();
                        Log.e("onFailureError", email);

//                        Usuario usuario = new Usuario(email, novaSenha, "");
//
//                        Call<JsonObject> call = RetrofitClient.getRetrofitInstance().getMyApi().alterar(usuario);
//
//                        call.enqueue(new Callback<JsonObject>() {
//                            @Override
//                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                                if(response.isSuccessful()) {
//                                    Toast.makeText(getApplicationContext(), "Senha modificada com sucesso!", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(AlteracaoActivity.this, AgendamentosActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<JsonObject> call, Throwable t) {
//                                Log.e("onFailureError", t.getMessage());
//                            }
//                        });
                    }
                }
            }
        });
    }
}
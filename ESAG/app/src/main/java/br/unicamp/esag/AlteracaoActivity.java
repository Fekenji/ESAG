package br.unicamp.esag;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    Button btnAlterar, btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        etNovaSenha = (EditText) findViewById(R.id.etNovaSenha);
        etConfirmarNovaSenha = (EditText) findViewById(R.id.etConfirmarNovaSenha);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

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
                        String finalToken = "Bearer " + token.getToken().replaceAll("\"", "");
                        Usuario usuario = new Usuario("", novaSenha, "");
                        Log.e("tokenGerado", finalToken);

                        Call<JsonObject> call = RetrofitClient.getRetrofitInstance().getMyApi().alterar(usuario, finalToken);
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Senha modificada com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AlteracaoActivity.this, AgendamentosActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Log.e("responseNotSuccessful", response.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.e("onFailureError", t.getMessage());
                            }
                        });
                    }
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(AlteracaoActivity.this);
                caixaDialogo.setTitle("Exclusão de conta");
                caixaDialogo.setIcon(android.R.drawable.ic_menu_delete);
                caixaDialogo.setMessage("Tem certeza que deseja excluir sua conta?");

                caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Excluir conta
                    }
                });

                caixaDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                caixaDialogo.show();
            }
        });
    }
}
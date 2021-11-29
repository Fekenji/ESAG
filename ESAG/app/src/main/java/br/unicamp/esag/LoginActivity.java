package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etSenha;
    TextView tvCadastro;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        tvCadastro = (TextView) findViewById(R.id.tvCadastro);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        // Cria um objeto contendo uma string
        SpannableString ss = new SpannableString("Não tem conta? Clique aqui para cadastrar!");
        // Faz com que os caracteres 22 a 26 da string sejam clicáveis
        ss.setSpan(new CustomClickableSpan(), 22, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvCadastro.setText(ss); // Coloca o texto no TextView
        tvCadastro.setMovementMethod(LinkMovementMethod.getInstance()); // Cria o movimento de redirecionamento de página

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    public void doLogin(){
        Log.e("debug", "entrou aqui");

        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if (email.trim().equals("")) {
            Toast.makeText(LoginActivity.this, "Digite o campo de e-mail!", Toast.LENGTH_LONG).show();
        } else if (senha.trim().equals("")) {
            Toast.makeText(LoginActivity.this, "Digite o campo de senha!", Toast.LENGTH_LONG).show();
        } else {
            String nome = "";
            Usuario usuario = new Usuario(email, senha, nome);
            Call<JsonObject> call = RetrofitClient.getRetrofitInstance().getMyApi().login(usuario);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()) {
                        String receivedToken = response.body().get("token").toString();
                        Token token = new Token(getApplicationContext());
                        token.setToken(receivedToken);
                        Intent intent = new Intent(LoginActivity.this, AlteracaoActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "E-mail ou senha inválidos", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro de servidor", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View textView) {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent); // Ao clicar, o usuário é transferido para a tela de cadastro
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE); // Muda a cor do texto clicável para azul
            ds.setUnderlineText(true); // O local clicável fica com underline
        }
    }
}
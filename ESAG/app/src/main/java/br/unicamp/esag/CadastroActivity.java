package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome, etEmail, etSenha;
    TextView tvLogin;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        // Formatação brasileira de telefone

        // Cria um objeto contendo uma string
        SpannableString ss = new SpannableString("Já tem conta? Clique aqui para entrar!");
        // Faz com que os caracteres 22 a 26 da string sejam clicáveis
        ss.setSpan(new CustomClickableSpan(), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLogin.setText(ss); // Coloca o texto no TextView
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance()); // Cria o movimento de redirecionamento de página

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                if (nome.trim().equals("")) {
                    Toast.makeText(CadastroActivity.this, "Digite o campo de nome!", Toast.LENGTH_LONG).show();
                } else if (email.trim().equals("")) {
                    Toast.makeText(CadastroActivity.this, "Digite o campo de e-mail!", Toast.LENGTH_LONG).show();
                } else if (senha.trim().equals("")) {
                    Toast.makeText(CadastroActivity.this, "Digite o campo de senha!", Toast.LENGTH_LONG).show();
                } else {
                    Usuario usuario = new Usuario(email, senha,nome);

                    Call<JsonObject> call = RetrofitClient.getRetrofitInstance().getMyApi().cadastrarUsuario(usuario);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.isSuccessful())
                            {
                                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "E-mail inválido", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e("onFailureError", t.getMessage());
                        }
                    });
                }
            }
        });

    }

    class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View textView) {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent); // Ao clicar, o usuário é transferido para a tela de cadastro
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE); // Muda a cor do texto clicável para azul
            ds.setUnderlineText(true); // O local clicável fica com underline
        }
    }
}
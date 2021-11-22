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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private void doLogin(){

        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String nome = "";

        Usuario usuario = new Usuario(email, senha, nome);

//        Call<Usuario> call = RetrofitClient.getRetrofitInstance().getMyApi().cadastrarUsuario(usuario);
//        call.enqueue(new Callback<Usuario>() {
//            @Override
//            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                if(response.body() != null)
//                {
//                    Intent intent = new Intent(LoginActivity.this, AgendamentosActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else{
//                    Toast.makeText(LoginActivity.this, "Por favor tente novamente", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Usuario> call, Throwable t) {
//                String messageProblem = t.getMessage().toString();
//                Toast.makeText(LoginActivity.this, messageProblem, Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
//            }
//        });
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
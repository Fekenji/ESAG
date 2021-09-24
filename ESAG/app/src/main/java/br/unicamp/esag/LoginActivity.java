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
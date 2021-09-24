package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome, etEmail, etTelefone, etSenha;
    TextView tvLogin;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etSenha = (EditText) findViewById(R.id.etSenha);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        // Formatação brasileira de telefone
        etTelefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));

        // Cria um objeto contendo uma string
        SpannableString ss = new SpannableString("Já tem conta? Clique aqui para entrar!");
        // Faz com que os caracteres 22 a 26 da string sejam clicáveis
        ss.setSpan(new CustomClickableSpan(), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLogin.setText(ss); // Coloca o texto no TextView
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance()); // Cria o movimento de redirecionamento de página

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefone = etNome.getText().toString();

                if (telefone.trim() == "") {
                    Snackbar.make(view, "Pão", BaseTransientBottomBar.LENGTH_LONG).show();
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
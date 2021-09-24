package br.unicamp.appconversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity2 extends AppCompatActivity {

    EditText edtTemperaturaCelsius;
    Button btnConcluir;
    RadioButton rbFahrenheit, rbKelvin;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtTemperaturaCelsius = (EditText) findViewById(R.id.edtTemperaturaCelsius);
        btnConcluir = (Button) findViewById(R.id.btnConcluir);
        rbFahrenheit = (RadioButton) findViewById(R.id.rbFahrenheit);
        rbKelvin = (RadioButton) findViewById(R.id.rbKelvin);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valorDigitado = edtTemperaturaCelsius.getText().toString();

                if (valorDigitado.equals("")) {
                    Snackbar.make(view, "Digite um valor em graus Celsius!", BaseTransientBottomBar.LENGTH_LONG).show();
                }
                else {
                    String[] partes = valorDigitado.split("\\.");

                    if (partes[0].length() > 20) {
                        Snackbar.make(view, "Digite um valor menor!", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                    else if (partes.length == 2 && partes[1].length() > 10) {
                        Snackbar.make(view, "Digite um valor com no máximo 10 casas decimais!", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                    else {
                        double celsius = Double.parseDouble(valorDigitado);

                        if (celsius < -273.15) {
                            Snackbar.make(view, "Digite um valor maior!", BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                        else {
                            if (rbFahrenheit.isChecked()) {
                                double fahrenheit = 1.8 * celsius + 32;
                                tvResultado.setText("Resultado: " + NumberFormat.getNumberInstance().format(fahrenheit) + "°F");
                            }
                            else if (rbKelvin.isChecked()) {
                                double kelvin = celsius + 273.15;
                                tvResultado.setText("Resultado: " + NumberFormat.getNumberInstance().format(kelvin) + " K");
                            }
                            else {
                                Snackbar.make(view, "Escolha uma opção para conversão!", BaseTransientBottomBar.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
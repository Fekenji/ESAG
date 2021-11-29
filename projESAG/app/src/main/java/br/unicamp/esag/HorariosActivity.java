package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorariosActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btnEscolherDia;
    ListView lvHorarios;
    Token token;
    String[] dataCompleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        lvHorarios = (ListView) findViewById(R.id.lViewHorarios);
        btnEscolherDia = (Button) findViewById(R.id.btnEscolherDia);

        btnEscolherDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        lvHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                String[] dados = ((TextView) view).getText().toString().split("\n");
                Agendamentos agendamentos = new Agendamentos(dados[0], dataCompleta[position], "");
                token = new Token(getApplicationContext());
                String finalToken = "Bearer " + token.getToken().replaceAll("\"", "");
                Call<JsonObject> call = RetrofitClient.getRetrofitInstance().getMyApi().agendarHorario(finalToken, agendamentos);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful())
                        {
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                        else
                        {
                            Log.e("unsuccessfulResponse", "deu ruim");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e("failure", "entrou no failure");
                    }
                });



            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, i);
        calendar.set(Calendar.MONTH, ++i1);
        calendar.set(Calendar.DAY_OF_MONTH, i2);

        String data = String.format("%d-%d-%d", i, i1, i2);
        Log.wtf("createdString", data);

        Call<List<Agendamentos>> call = RetrofitClient.getRetrofitInstance().getMyApi().getHorarios(data);
        call.enqueue(new Callback<List<Agendamentos>>() {
            @Override
            public void onResponse(Call<List<Agendamentos>> call, Response<List<Agendamentos>> response) {
                List<Agendamentos> horariosList = response.body();
                String[] umHorario = new String[horariosList.size()];
                dataCompleta = new String[horariosList.size()];

                for(int i =0; i < horariosList.size(); i++)
                {
                    umHorario[i] = horariosList.get(i).getNomeEstabelecimento() + "\n";
                    umHorario[i] += horariosList.get(i).getLocalizacao() + "\n";
                    String horario = horariosList.get(i).getHorario().substring(11, 16);
                    dataCompleta[i] = horariosList.get(i).getHorario();
                    umHorario[i] += String.format("%s", horario);

                }

                lvHorarios.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, umHorario));
            }

            @Override
            public void onFailure(Call<List<Agendamentos>> call, Throwable t) {
                Log.e("onFailure", "SERVER ERROR");

            }
        });

    }
}
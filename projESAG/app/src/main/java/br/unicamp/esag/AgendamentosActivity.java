
package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendamentosActivity extends AppCompatActivity {

    ListView lvAgendamentos;
    ImageButton ibMenu;
    Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        token = new Token(getApplicationContext());
        lvAgendamentos = (ListView) findViewById(R.id.lViewAgendamentos);

        Token token = new Token(getApplicationContext());
        String finalToken = "Bearer " + token.getToken().replaceAll("\"","");

        Call<List<Agendamentos>> call = RetrofitClient.getRetrofitInstance().getMyApi().getConsultas(finalToken);
        call.enqueue(new Callback<List<Agendamentos>>() {
            @Override
            public void onResponse(Call<List<Agendamentos>> call, Response<List<Agendamentos>> response) {
                List<Agendamentos> agendamentosList = response.body();
                String[] umAgendamento = new String[agendamentosList.size()];

                for(int i =0; i < agendamentosList.size(); i++)
                {
                    umAgendamento[i] = agendamentosList.get(i).getNomeEstabelecimento() + "\n";
                    umAgendamento[i] += agendamentosList.get(i).getLocalizacao() + "\n";
                    String ano = agendamentosList.get(i).getHorario().substring(0, 4);
                    String mes = agendamentosList.get(i).getHorario().substring(5, 7);
                    String dia = agendamentosList.get(i).getHorario().substring(8, 10);
                    String horario = agendamentosList.get(i).getHorario().substring(11, 16);

                    umAgendamento[i] += String.format("%s/%s/%s, às %s", dia, mes, ano, horario);

                }

                lvAgendamentos.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, umAgendamento));
            }

            @Override
            public void onFailure(Call<List<Agendamentos>> call, Throwable t) {
                Log.e("response", "deu failure");
            }
        });



    }
}
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
        setContentView(R.layout.activity_agendamentos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        token = new Token(getApplicationContext());


        lvAgendamentos = (ListView) findViewById(R.id.lViewAgendamentos);
        ibMenu = (ImageButton) findViewById(R.id.ibMenu);

        //ArrayList<String> teste = preencherDados();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, teste);
        //lvAgendamentos.setAdapter(adapter);


        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AgendamentosActivity", token.getToken());
            }
        });

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
                    umAgendamento[i] += agendamentosList.get(i).getNomeEstabelecimento() + " ";
                    umAgendamento[i] += agendamentosList.get(i).getHorario() + " ";
                    umAgendamento[i] += agendamentosList.get(i).getLocalizacao();

                }


                lvAgendamentos.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, umAgendamento));
            }

            @Override
            public void onFailure(Call<List<Agendamentos>> call, Throwable t) {
                Log.e("response", "deu failure");
            }
        });



    }

    private ArrayList<String> preencherDados()
    {
        ArrayList<String> dados = new ArrayList<String>();
        dados.add("item 1");
        dados.add("item 2");
        dados.add("item 3");
        dados.add("item 4");
        dados.add("item 5");
        dados.add("item 6");
        dados.add("item 7");
        dados.add("item 8");
        dados.add("item 9");
        dados.add("item 10");
        dados.add("item 11");
        dados.add("item 12");
        dados.add("item 13");
        dados.add("item 14");
        dados.add("item 15");
        dados.add("item 16");
        return dados;
    }
}
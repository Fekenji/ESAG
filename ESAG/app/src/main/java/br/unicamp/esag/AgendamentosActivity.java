package br.unicamp.esag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class AgendamentosActivity extends AppCompatActivity {

    ListView lvAgendamentos;
    ImageButton ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);

        lvAgendamentos = (ListView) findViewById(R.id.lViewAgendamentos);
        ibMenu = (ImageButton) findViewById(R.id.ibMenu);

        ArrayList<String> teste = preencherDados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, teste);
        lvAgendamentos.setAdapter(adapter);

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
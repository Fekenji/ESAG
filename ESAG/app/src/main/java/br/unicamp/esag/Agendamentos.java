package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agendamentos implements Serializable {

    @SerializedName("nomeEstabelecimento")
    private String nomeEstabelecimento;

    @SerializedName("horario")
    private String horario;

    @SerializedName("localizacao")
    private String localizacao;

    public Agendamentos(String nome, String horario, String localizacao)
    {
        this.nomeEstabelecimento = nome;
        this.horario = horario;
        this.localizacao = localizacao;
    }

    public String getNomeEstabelecimento() { return this.nomeEstabelecimento; }
    public String getHorario() { return this.horario; }
    public String getLocalizacao() { return this.localizacao; }
}

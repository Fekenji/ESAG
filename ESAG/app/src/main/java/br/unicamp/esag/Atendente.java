package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Atendente implements Serializable{

    @SerializedName("idAtendente")
    private int idAtendente;

    @SerializedName("nomeAtendente")
    private String nomeAtendente;

    @SerializedName("profissaoAtendente")
    private String profissaoAtendente;


    public Atendente(int id, String nome, String profissao)
    {
        this.idAtendente = id;
        this.nomeAtendente = nome;
        this.profissaoAtendente = profissao;
    }


    public int getIdAtendente() {
        return idAtendente;
    }
    public void setIdAtendente(int idAtendente) {
        this.idAtendente = idAtendente;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }
    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getProfissaoAtendente() {
        return profissaoAtendente;
    }
    public void setProfissaoAtendente(String profissaoAtendente) {
        this.profissaoAtendente = profissaoAtendente;
    }
}

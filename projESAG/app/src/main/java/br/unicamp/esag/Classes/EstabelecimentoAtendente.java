package br.unicamp.esag.Classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EstabelecimentoAtendente implements Serializable{

    @SerializedName("idEstabelecimentoAtendente")
    private int idEstabelecimentoAtendente;

    @SerializedName("idAtendente")
    private int idAtendente;

    @SerializedName("idEstabelecimento")
    private int idEstabelecimento;


    public EstabelecimentoAtendente(int idEstabelecimentoAtendente, int idAtendente, int idEstabelecimento) {

        this.idEstabelecimentoAtendente = idEstabelecimentoAtendente;
        this.idAtendente = idAtendente;
        this.idEstabelecimento = idEstabelecimento;
    }


    public int getIdEstabelecimentoAtendente() {
        return idEstabelecimentoAtendente;
    }
    public void setIdEstabelecimentoAtendente(int idEstabelecimentoAtendente) {
        this.idEstabelecimentoAtendente = idEstabelecimentoAtendente;
    }

    public int getIdAtendente() {
        return idAtendente;
    }
    public void setIdAtendente(int idAtendente) {
        this.idAtendente = idAtendente;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }
    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }
}

package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Estabelecimento implements Serializable{

    @SerializedName("idEstabelecimento")
    private int idEstabelecimento;

    @SerializedName("tipoEstabelecimento")
    private String tipoEstabelecimento;

    @SerializedName("localizacao")
    private String localizacao;


    public Estabelecimento(int id, String tipo, String localizacao) {

        this.idEstabelecimento = id;
        this.tipoEstabelecimento = tipo;
        this.localizacao = localizacao;
    }


    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }
    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getTipoEstabelecimento() {
        return tipoEstabelecimento;
    }
    public void setTipoEstabelecimento(String tipoEstabelecimento) {
        this.tipoEstabelecimento = tipoEstabelecimento;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}

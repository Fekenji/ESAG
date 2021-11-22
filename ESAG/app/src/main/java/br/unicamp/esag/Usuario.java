package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Usuario implements Serializable{

    @SerializedName("emailUsuario")
    private String emailUsuario;

    @SerializedName("senhaUsuario")
    private String senhaUsuario;

    @SerializedName("nomeUsuario")
    private String nomeUsuario;


    public Usuario(String email, String senha, String nome)
    {
        this.emailUsuario = email;
        this.senhaUsuario = senha;
        this.nomeUsuario = nome;
    }


    public String getEmailUsuario() {
        return emailUsuario;
    }
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}

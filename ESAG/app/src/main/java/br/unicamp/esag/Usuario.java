package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Usuario implements Serializable{

    @SerializedName("emailUsuario")
    private String emailUsuario;

    @SerializedName("senhaUsuario")
    private String senhaUsuario;

    @SerializedName("telefoneUsuario")
    private String telefoneUsuario;

    @SerializedName("nomeUsuario")
    private String nomeUsuario;


    public Usuario(String email, String senha, String telefone, String nome)
    {
        this.emailUsuario = email;
        this.senhaUsuario = senha;
        this.telefoneUsuario = telefone;
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

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }
    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}

package br.unicamp.esag;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{

    @SerializedName("idConsulta")
    private int idConsulta;

    @SerializedName("horarioConsulta")
    private Date horarioConsulta;

    @SerializedName("emailUsuario")
    private String emailUsuario;

    @SerializedName("idEstabelecimentoAtendente")
    private int idEstabelecimentoAtendimento;


    public Consulta(int idConsulta, Date horario, String email,
                    int idEstabelecimentoAtendimento) {
        this.idConsulta = idConsulta;
        this.horarioConsulta = horario;
        this.emailUsuario = email;
        this.idEstabelecimentoAtendimento = idEstabelecimentoAtendimento;
    }


    public int getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getHorarioConsulta() {
        return horarioConsulta;
    }
    public void setHorarioConsulta(Date horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdEstabelecimentoAtendimento() {
        return idEstabelecimentoAtendimento;
    }
    public void setIdEstabelecimentoAtendimento(int idEstabelecimentoAtendimento) {
        this.idEstabelecimentoAtendimento = idEstabelecimentoAtendimento;
    }
}

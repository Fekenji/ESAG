package br.unicamp.esag;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://192.168.0.18:3000/api/esag/";

    @Headers("Content-Type: application/json")

    @POST("usuarios/cadastro")
    Call<JsonObject> cadastrarUsuario(@Body Usuario usuario);

    @POST("usuarios/login")
    Call<JsonObject> login(@Body Usuario usuario);

    @PATCH("usuarios/alteracao")
    Call<JsonObject> alterar(@Body Usuario usuario, @Header("Authorization") String token);

    @GET("agendamentos")
    Call<List<Agendamentos>> getConsultas(@Header("Authorization") String token);
}

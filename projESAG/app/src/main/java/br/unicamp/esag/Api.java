package br.unicamp.esag;

import com.google.gson.JsonObject;

import java.util.List;

import br.unicamp.esag.Classes.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {
    String BASE_URL = "http://192.168.1.219:3000/api/esag/";

    @Headers("Content-Type: application/json")

    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("usuarios/cadastro")
    Call<JsonObject> cadastrarUsuario(@Body Usuario usuario);

    @POST("usuarios/login")
    Call<JsonObject> login(@Body Usuario usuario);

    @PUT("usuarios/alteracao")
    Call<JsonObject> alterar(@Body Usuario usuario);
}
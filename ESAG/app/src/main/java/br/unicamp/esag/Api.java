package br.unicamp.esag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://192.168.0.42/3000/api/esag/";

    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    @POST("usuarios/login")
    Call<Usuario> doLogin(@Body Usuario usuario);
}

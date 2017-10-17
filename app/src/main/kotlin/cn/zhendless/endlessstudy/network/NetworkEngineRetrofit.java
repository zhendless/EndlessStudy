package cn.zhendless.endlessstudy.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkEngineRetrofit {

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    public void createBaseRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    interface MovieService {
        @GET("top250")
        Call<MovieSubject> getTop250(@Query("start") int start,
                                     @Query("count") int count);
    }

    public void requestTop250() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieSubject> call = movieService.getTop250(0, 20);
        call.enqueue(new Callback<MovieSubject>() {
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {

            }

            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {

            }
        });
    }

    private class MovieSubject {
    }
}

package com.example.fifa2022matches.manager;

import android.content.Context;

import com.example.fifa2022matches.ResponceListener;
import com.example.fifa2022matches.model.FixtureResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://elenasport-io1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getFixture(ResponceListener listener,int id){
        CallFixture callFixture = retrofit.create(CallFixture.class);
        Call<FixtureResponce> call = callFixture.callFixtures(id);
        call.enqueue(new Callback<FixtureResponce>() {
            @Override
            public void onResponse(Call<FixtureResponce> call, Response<FixtureResponce> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didfatch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<FixtureResponce> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallFixture{
        @GET("v2/seasons/{id}/fixtures")
        @Headers(
                {"Accept: application/json",
                "X-RapidAPI-Key: 92b7b2b8fdmsh9fd4559492136dfp1b08b8jsn6b923031a99c",
                        "X-RapidAPI-Host: elenasport-io1.p.rapidapi.com"
                }
        )
        Call<FixtureResponce> callFixtures(
            @Path("id") int id
            );
    }
}

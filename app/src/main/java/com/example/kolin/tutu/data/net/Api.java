package com.example.kolin.tutu.data.net;


import com.example.kolin.tutu.data.models.CityEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kolin on 10.11.2016.
 */

public interface Api {

    @GET("citiesFrom.json")
    Observable<List<CityEntity>> getCitiesFrom();

    @GET("citiesTo.json")
    Observable<List<CityEntity>> getCitiesTo();
}

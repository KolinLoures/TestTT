package com.example.kolin.tutu.data.net;


import com.example.kolin.tutu.data.models.CityEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 10.11.2016.
 */

public class Rest {

    private Api api;

    public Rest() {
        this.api = RetrofitSingleton.getInstanse().create(Api.class);
    }

    public Observable<List<CityEntity>> getCitiesFrom() {
        return api.getCitiesFrom();
    }

    public Observable<List<CityEntity>> getCitiesTo() {
        return api.getCitiesTo();
    }


}

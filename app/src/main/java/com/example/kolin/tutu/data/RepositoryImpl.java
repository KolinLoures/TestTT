package com.example.kolin.tutu.data;

import com.example.kolin.tutu.data.models.CityEntity;
import com.example.kolin.tutu.data.net.Rest;
import com.example.kolin.tutu.domain.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 10.11.2016.
 */

public class RepositoryImpl implements Repository {

    private static final String TAG = RepositoryImpl.class.getSimpleName();

    private Rest rest;

    public RepositoryImpl() {
        this.rest = new Rest();
    }


    @Override
    public Observable<List<CityEntity>> getAllCities(@Direction.TypeDirection int pos) {
        Observable<List<CityEntity>> result;
        if (pos == Direction.FROM){
            result = rest.getCitiesFrom();
        } else {
            result = rest.getCitiesTo();
        }
        return result;
    }
}

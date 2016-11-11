package com.example.kolin.tutu.domain;

import com.example.kolin.tutu.data.Direction;
import com.example.kolin.tutu.data.models.CityEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 10.11.2016.
 */

public interface Repository {

    Observable<List<CityEntity>> getAllCities(@Direction.TypeDirection int pos);

}

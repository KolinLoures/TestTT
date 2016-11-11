package com.example.kolin.tutu.domain.interactor;

import android.util.Log;

import com.example.kolin.tutu.data.Direction;
import com.example.kolin.tutu.data.RepositoryImpl;
import com.example.kolin.tutu.data.models.CityEntity;
import com.example.kolin.tutu.domain.Repository;
import com.example.kolin.tutu.domain.model.Station;
import com.example.kolin.tutu.domain.model.StationComparator;
import com.example.kolin.tutu.domain.model.StationMapper;
import com.example.kolin.tutu.domain.usecases.CloudUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by kolin on 10.11.2016.
 */

public class GetStations extends CloudUseCase {

    private Repository repository;
    @Direction.TypeDirection
    private int currentDirection;

    public GetStations() {
        repository = new RepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository
                .getAllCities(currentDirection)
                .flatMap(new Func1<List<CityEntity>, Observable<List<Station>>>() {
                    @Override
                    public Observable<List<Station>> call(List<CityEntity> cityEntities) {
                        List<Station> stationList = new ArrayList<>();
                        for (CityEntity c : cityEntities) {
                            stationList.addAll(StationMapper.createStationFromStationEntity(c.getStationEntities()));
                        }
                        Collections.sort(stationList, new StationComparator());
                        return Observable.just(stationList);
                    }
                })
                .doOnNext(new Action1<List<Station>>() {
                    @Override
                    public void call(List<Station> stations) {
                        for (Station s : stations)
                            Log.e("TAGAGAGA", s.getStationTitle());
                    }
                });
    }

    public void setDirection(@Direction.TypeDirection int direction) {
        currentDirection = direction;
    }
}

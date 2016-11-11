package com.example.kolin.tutu.domain.model;

import com.example.kolin.tutu.data.models.StationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 11.11.2016.
 */

public class StationMapper {

    public static List<Station> createStationFromStationEntity(List<StationEntity> stationEntities){
        List<Station> result = new ArrayList<>();
        for (StationEntity s: stationEntities){
            Station station = new Station();
            station.setCityId(s.getCityId());
            station.setCityTitle(s.getCityTitle());
            station.setCountryTitle(s.getCountryTitle());
            station.setDistrictTitle(s.getDistrictTitle());
            station.setRegionTitle(s.getRegionTitle());
            station.setStationId(s.getStationId());
            station.setStationTitle(s.getStationTitle());
            result.add(station);
        }
        return result;
    }
}

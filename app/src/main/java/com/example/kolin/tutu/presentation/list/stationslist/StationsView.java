package com.example.kolin.tutu.presentation.list.stationslist;

import com.example.kolin.tutu.domain.model.Station;

import java.util.List;

/**
 * Created by kolin on 10.11.2016.
 */

public interface StationsView {

    void showLoadedData(List<Station> stations);

    void showProgressBar(boolean show);
}

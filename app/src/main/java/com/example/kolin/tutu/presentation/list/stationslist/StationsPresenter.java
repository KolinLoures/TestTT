package com.example.kolin.tutu.presentation.list.stationslist;

import android.util.Log;

import com.example.kolin.tutu.domain.interactor.GetStations;
import com.example.kolin.tutu.domain.model.Station;
import com.example.kolin.tutu.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by kolin on 10.11.2016.
 */

public class StationsPresenter extends AbstractPresenter<StationsView> {

    private static final String TAG = StationsPresenter.class.getSimpleName();

    private GetStations getStations;
    private List<Station> loadedData = new ArrayList<>();


    public StationsPresenter(StationsView view) {
        super.attachView(view);

        getStations = new GetStations();
    }

    public void execute(int direction) {
        setProgressBarVisible(true);

        getStations.setDirection(direction);
        getStations.execute(new CountriesSubscriber());
    }

    private class CountriesSubscriber extends Subscriber<List<Station>> {

        @Override
        public void onCompleted() {
            setProgressBarVisible(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.toString());
        }

        @Override
        public void onNext(List<Station> stations) {
            showLoadedData(stations);
        }
    }

    public void showLoadedData(List<Station> stations) {
        loadedData.clear();
        loadedData.addAll(stations);

        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
        }

        getView().showLoadedData(stations);
    }

    public void ubsubscribe() {
        detachView();
        getStations.unsubscribe();
    }

    public void setProgressBarVisible(boolean visible) {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
        }

        getView().showProgressBar(visible);
    }

    public void searchLoadedData(String query) {

        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
        }

        if (!query.isEmpty()) {
            List<Station> result = new ArrayList<>();
            for (Station s : loadedData) {
                if (s.getStationTitle().contains(query)) {
                    result.add(s);
                }
            }
            getView().showLoadedData(result);
        } else {
            getView().showLoadedData(loadedData);
        }
    }

    public List<Station> getLoadedData() {
        return loadedData;
    }

    public void setLoadedData(List<Station> loadedData) {
        this.loadedData.clear();
        this.loadedData.addAll(loadedData);
    }
}

package com.example.kolin.tutu.domain.model;

import java.util.Comparator;

/**
 * Created by kolin on 11.11.2016.
 */

public class StationComparator implements Comparator<Station> {

    @Override
    public int compare(Station o1, Station o2) {
        int flag = o1.getCountryTitle().compareTo(o2.getCountryTitle());
        if (flag == 0)
            flag = o1.getCityTitle().compareTo(o2.getCityTitle());
        return flag;
    }
}

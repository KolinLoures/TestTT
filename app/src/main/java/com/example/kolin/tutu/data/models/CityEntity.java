package com.example.kolin.tutu.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 10.11.2016.
 */

public class CityEntity {

    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("cityTitle")
    @Expose
    private String cityTitle;
    @SerializedName("countryTitle")
    @Expose
    private String countryTitle;
    @SerializedName("districtTitle")
    @Expose
    private String districtTitle;
    @SerializedName("regionTitle")
    @Expose
    private String regionTitle;
    @SerializedName("stations")
    @Expose
    private List<StationEntity> stationEntities = new ArrayList<>();

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public List<StationEntity> getStationEntities() {
        return stationEntities;
    }

    public void setStationEntities(List<StationEntity> stationEntities) {
        this.stationEntities = stationEntities;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cityId='" + cityId + '\'' +
                ", cityTitle='" + cityTitle + '\'' +
                ", countryTitle='" + countryTitle + '\'' +
                ", districtTitle='" + districtTitle + '\'' +
                ", regionTitle='" + regionTitle + '\'' + '\n';
    }
}

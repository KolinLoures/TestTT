package com.example.kolin.tutu.presentation.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.domain.model.Station;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewCity;
    private TextView textViewCountry;
    private TextView textViewDistrict;
    private TextView textViewRegion;
    private TextView textViewStationTitle;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Station station = (Station) getIntent().getSerializableExtra("station");

        textViewCity = (TextView) findViewById(R.id.detail_activity_city);
        textViewStationTitle = (TextView) findViewById(R.id.detail_activity_station_title);
        textViewCountry = (TextView) findViewById(R.id.detail_activity_country);
        textViewDistrict = (TextView) findViewById(R.id.detail_activity_district);
        textViewRegion = (TextView) findViewById(R.id.detail_activity_region);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.detail_activity_collapsing_toolbar);
        setAllViewText(station);
    }

    private void setAllViewText(Station station) {
        collapsingToolbarLayout.setTitle(station.getStationTitle());
        setNecessaryText(textViewStationTitle, station.getStationTitle());
        setNecessaryText(textViewCity, station.getCityTitle());
        setNecessaryText(textViewCountry, station.getCountryTitle());
        setNecessaryText(textViewDistrict, station.getDistrictTitle());
        setNecessaryText(textViewRegion, station.getRegionTitle());

    }

    private void setNecessaryText(TextView textView, String text) {
        if (!text.isEmpty()) {
            textView.setText(text);
        } else {
            textView.setText(getString(R.string.null_result));
        }
    }
}

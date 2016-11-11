package com.example.kolin.tutu.presentation.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.domain.model.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 11.11.2016.
 */

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.StationsViewHolder> {

    private List<Station> stationList = new ArrayList<>();

    private StationAdapterListener listener;

    public interface StationAdapterListener{
        void onClickBtnDetailInfo(Station station);

        void onClickStation(String stationTitle);
    }

    @Override
    public StationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stations_item, parent, false);
        return new StationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationsViewHolder holder, int position) {
        Station station = stationList.get(position);

        holder.tvStationTitle.setText(station.getStationTitle());
        holder.tvStationCountry.setText(station.getCountryTitle());
        holder.tvStationCity.setText(station.getCityTitle());
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }


    class StationsViewHolder extends RecyclerView.ViewHolder{

        private TextView tvStationTitle;
        private TextView tvStationCountry;
        private TextView tvStationCity;

        private ImageButton ibStationDetailInfo;

        public StationsViewHolder(View itemView) {
            super(itemView);

            tvStationTitle = (TextView) itemView.findViewById(R.id.stations_item_title);
            tvStationCountry = (TextView) itemView.findViewById(R.id.stations_item_country);
            tvStationCity = (TextView) itemView.findViewById(R.id.stations_item_city);

            ibStationDetailInfo = (ImageButton) itemView.findViewById(R.id.stations_item_detail_info);

            ibStationDetailInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onClickBtnDetailInfo(stationList.get(getLayoutPosition()));
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onClickStation(stationList.get(getLayoutPosition()).getStationTitle());
                    }
                }
            });
        }
    }

    public void addAll(List<Station> stations){
        stationList.clear();
        stationList.addAll(stations);
        notifyDataSetChanged();
    }

    public void setListener(StationAdapterListener listener) {
        this.listener = listener;
    }
}

package com.example.kolin.tutu.presentation.list.stationslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.domain.model.Station;
import com.example.kolin.tutu.presentation.list.adapter.StationsAdapter;

import java.io.Serializable;
import java.util.List;

public class StationsFragment extends Fragment implements StationsView {


    private static final String DIR_ARG = "direction_arg";
    private static final String STATE_ARG = "state";

    private StationsPresenter presenter;
    private StationsAdapter adapter;

    private ProgressBar progressBar;
    private SearchView searchView;

    private StationsFragmentListener listener;

    public interface StationsFragmentListener{
        void onClickAdapterDetailInfo(Station station);

        void onClickAdapter(String title);
    }

    public StationsFragment() {
        // Required empty public constructor
    }

    public static StationsFragment newInstance(int direction) {
        StationsFragment fragment = new StationsFragment();
        Bundle args = new Bundle();
        args.putInt(DIR_ARG, direction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new StationsPresenter(this);
        adapter = new StationsAdapter();
        adapter.setListener(new StationsAdapter.StationAdapterListener() {
            @Override
            public void onClickBtnDetailInfo(Station station) {
                if (listener != null){
                    listener.onClickAdapterDetailInfo(station);
                }
            }

            @Override
            public void onClickStation(String stationTitle) {
                if (listener != null){
                    listener.onClickAdapter(stationTitle);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_stations_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = (ProgressBar) view.findViewById(R.id.fragment_stations_progress_bar);
        progressBar.setVisibility(View.GONE);
        searchView = (SearchView) view.findViewById(R.id.fragment_stations_search_view);
        setOnQueryListenerSearchView();
        return view;
    }

    private void setOnQueryListenerSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchLoadedData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchLoadedData(newText);
                return true;
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            int direction = getArguments().getInt(DIR_ARG);
            presenter.execute(direction);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            List<Station> data = (List<Station>) savedInstanceState.getSerializable(STATE_ARG);
            presenter.setLoadedData(data);
            showLoadedData(data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (StationsFragmentListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context.toString()
                    + " must implement StationsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        searchView.setOnQueryTextListener(null);
        adapter.setListener(null);
        presenter.ubsubscribe();
        super.onDetach();
    }

    @Override
    public void showLoadedData(List<Station> stations) {
        adapter.addAll(stations);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STATE_ARG, (Serializable) presenter.getLoadedData());
    }
}

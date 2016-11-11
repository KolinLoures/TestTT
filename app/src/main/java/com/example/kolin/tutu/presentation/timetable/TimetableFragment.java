package com.example.kolin.tutu.presentation.timetable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.data.Direction;
import com.example.kolin.tutu.presentation.common.Updatable;


public class TimetableFragment extends Fragment implements Updatable<String> {

    private static final String ARG_STATE_TO = "arg_state_to";
    private static final String ARG_STATE_FROM = "arg_state_from";
    private static final String ARG_STATE_DATE = "arg_state_date";

    private ImageButton btnFrom;
    private ImageButton btnTo;
    private ImageButton btnDate;

    private TextView textViewTo;
    private TextView textViewFrom;
    private TextView textViewDate;

    private View.OnClickListener onClickListener;
    private TimetableFragmentListener listener;


    public interface TimetableFragmentListener {
        void onClickBtnDirections(@Direction.TypeDirection int direction);

        void onClickBtnDate();
    }

    public TimetableFragment() {
        // Required empty public constructor
    }

    public static TimetableFragment newInstance(String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        btnTo = (ImageButton) view.findViewById(R.id.fragment_timetable_btn_to);
        btnFrom = (ImageButton) view.findViewById(R.id.fragment_timetable_btn_from);
        btnDate = (ImageButton) view.findViewById(R.id.fragment_timetable_btn_date);

        textViewTo = (TextView) view.findViewById(R.id.fragment_timetable_text_to);
        textViewFrom = (TextView) view.findViewById(R.id.fragment_timetable_text_from);
        textViewDate = (TextView) view.findViewById(R.id.fragment_timetable_text_date);

        setBtnListeners();
        return view;
    }

    private void setBtnListeners() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(v);
            }
        };

        btnTo.setOnClickListener(onClickListener);
        btnFrom.setOnClickListener(onClickListener);
        btnDate.setOnClickListener(onClickListener);
    }

    private void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.fragment_timetable_btn_from:
                if (listener != null) {
                    listener.onClickBtnDirections(Direction.FROM);
                }
                break;
            case R.id.fragment_timetable_btn_to:
                if (listener != null) {
                    listener.onClickBtnDirections(Direction.TO);
                }
                break;
            case R.id.fragment_timetable_btn_date:
                if (listener != null) {
                    listener.onClickBtnDate();
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (TimetableFragmentListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context.toString()
                    + " must implement TimetableFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        onClickListener = null;
        btnFrom.setOnClickListener(null);
        btnTo.setOnClickListener(null);
        btnDate.setOnClickListener(null);
        super.onDetach();
    }

    @Override
    public void updateTo(String name) {
        textViewTo.setText(name);
    }

    @Override
    public void updateFrom(String name) {
        textViewFrom.setText(name);
    }

    @Override
    public void updateDate(String name) {
        textViewDate.setText(name);
    }


}

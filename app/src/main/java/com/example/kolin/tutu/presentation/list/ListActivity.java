package com.example.kolin.tutu.presentation.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.data.Direction;
import com.example.kolin.tutu.domain.model.Station;
import com.example.kolin.tutu.presentation.detail.DetailActivity;
import com.example.kolin.tutu.presentation.list.stationslist.StationsFragment;

public class ListActivity extends AppCompatActivity implements StationsFragment.StationsFragmentListener {


    private static final String TAG_STATIONS_FRAGMENT = StationsFragment.class.getSimpleName();

    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int currentDirection = getIntent().getIntExtra("direction", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_list_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setCurrentTitle(currentDirection);

        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, TAG_STATIONS_FRAGMENT);
        } else {
            currentFragment = StationsFragment.newInstance(currentDirection);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_list_frame_layout, currentFragment, TAG_STATIONS_FRAGMENT)
                .commit();
    }

    private void setCurrentTitle(int direction){
        if (direction == Direction.TO){
            setTitle(getString(R.string.point_to));
        } else {
            setTitle(getString(R.string.point_from));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onClickAdapterDetailInfo(Station station) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("station", station);
        startActivity(intent);
    }

    @Override
    public void onClickAdapter(String title) {
        Intent intent = new Intent();
        intent.putExtra("station_title", title);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, TAG_STATIONS_FRAGMENT, currentFragment);

    }
}

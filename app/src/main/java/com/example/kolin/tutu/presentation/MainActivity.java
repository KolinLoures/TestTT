package com.example.kolin.tutu.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.kolin.tutu.R;
import com.example.kolin.tutu.data.Direction;
import com.example.kolin.tutu.presentation.common.Updatable;
import com.example.kolin.tutu.presentation.info.InfoFragment;
import com.example.kolin.tutu.presentation.list.ListActivity;
import com.example.kolin.tutu.presentation.timetable.TimetableFragment;

import java.util.List;

import static com.example.kolin.tutu.R.id.nav_menu_timetable;


public class MainActivity extends AppCompatActivity implements TimetableFragment.TimetableFragmentListener {

    private static final String TAG_FRAGMENT_TIME = "fragment_time";
    private static final String TAG_FRAGMENT_INFO = "fragment_info";

    private static final String STATE_CHECKED_ITEM = "state_checked";

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;

    private DatePickerFragment datePickerFragment;
    private int currentItemId;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.main_activity_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemNavView(item);
                return true;
            }
        });


        setToolbar();
        if (savedInstanceState == null){
            currentItemId = nav_menu_timetable;
            selectItemNavView(navigationView.getMenu().findItem(currentItemId));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentItemId = savedInstanceState.getInt(STATE_CHECKED_ITEM);
            selectItemNavView(navigationView.getMenu().findItem(currentItemId));
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemNavView(MenuItem item) {
        Fragment fragment = null;
        String currentTag = null;
        switch (item.getItemId()) {
            case nav_menu_timetable:
                currentTag = TAG_FRAGMENT_TIME;
                if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_TIME) != null) {
                    fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT_TIME);
                } else {
                    fragment = new TimetableFragment();
                }
                break;
            case R.id.nav_menu_about_app:
                currentTag = TAG_FRAGMENT_INFO;
                if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_INFO) != null) {
                    fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT_INFO);
                } else {
                    fragment = new InfoFragment();
                }
                break;
        }

        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_container, fragment, currentTag)
                    .commit();
            setTitle(item.getTitle());
        }

        currentItemId = item.getItemId();
        item.setChecked(true);
        drawerLayout.closeDrawers();
    }

    @Override
    protected void onDestroy() {
        navigationView.setNavigationItemSelectedListener(null);
        if (datePickerFragment != null) {
            datePickerFragment.setListener(null);
        }
        super.onDestroy();
    }

    @Override
    public void onClickBtnDirections(@Direction.TypeDirection int direction) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("direction", direction);
        startActivityForResult(intent, direction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onClickBtnDate() {
        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setListener(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onClickOk(int year, int month, int dayOfMonth) {
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                for (Fragment f : fragments) {
                    if (f instanceof Updatable) {
                        ((Updatable) f).updateDate(dayOfMonth + "." + month + "." + year);
                    }
                }
            }
        });
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            switch (requestCode) {
                case Direction.FROM:
                    for (Fragment f : fragments) {
                        if (f instanceof Updatable) {
                            ((Updatable) f).updateFrom(data.getStringExtra("station_title"));
                        }
                    }
                    break;
                case Direction.TO:
                    for (Fragment f : fragments) {
                        if (f instanceof Updatable) {
                            ((Updatable) f).updateTo(data.getStringExtra("station_title"));
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_CHECKED_ITEM, currentItemId);

    }
}

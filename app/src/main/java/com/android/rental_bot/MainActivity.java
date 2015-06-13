package com.android.rental_bot;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private boolean hideMenuItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Drawer toggle button code starts
        mTitle = getTitle();
        mDrawerTitle = getResources().getString(R.string.drawer_open);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMainActivity);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                hideMenuItem = false;
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideMenuItem = true;
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // Drawer toggle button code ends


        // FAB code starts
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewMainActivityMainContent);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMainActivity);
        fab.attachToScrollView((com.melnykov.fab.ObservableScrollView) scrollView);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // FAB code ends

        // Drawer - My Own Unit list view code starts
        ArrayList<Unit> arrayOfUnits = new ArrayList<>();
        Unit unit1 = new Unit(R.drawable.room1, "Accomodation", "Sunway University", 700, "Fully Furnished", 2, 4, "M");
        Unit unit2 = new Unit(R.drawable.room2, "A1-7-5, Vista Komanwel A", "Bukit Jalil", 500, "Fully Furnished", 3, 6, "F");
        Unit unit3 = new Unit(R.drawable.room3, "A1-7-6, Vista Komanwel A", "Bukit Jalil", 600, "Fully Furnished", 4, 8, "M");
        arrayOfUnits.add(unit1);
        arrayOfUnits.add(unit2);
        arrayOfUnits.add(unit3);
        ListAdapter listAdapter = new MyOwnUnitAdapter(this, arrayOfUnits);
        final ListView my_own_unit_listview = (ListView) findViewById(R.id.listViewMainActivityMyOwnUnit);
        my_own_unit_listview.setAdapter(listAdapter);
        my_own_unit_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Unit unit = (Unit) parent.getItemAtPosition(position);
                        Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                        // Start RoomDetail Intent
                        Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                        startActivity(intent);
                    }
                }
        );


        /*if (my_own_unit_listview.getVisibility() == View.VISIBLE) {
            my_own_unit_listview.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = my_own_unit_listview.getLayoutParams();
            params.height = 0;
            my_own_unit_listview.setLayoutParams(params);
        }*/


        final Button expand_my_own_unit_button = (Button) findViewById(R.id.btnMainActivityExpandMyOwnUnit);
        final ImageView my_own_unit_expand_button_image = (ImageView) findViewById(R.id.imageViewMainActivityExpandMyOwnUnit);
        expand_my_own_unit_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_own_unit_listview.getVisibility() == View.VISIBLE) {
                    my_own_unit_listview.setVisibility(View.INVISIBLE);
                    my_own_unit_expand_button_image.setImageResource(R.drawable.ic_action_expand);
                    ViewGroup.LayoutParams params = my_own_unit_listview.getLayoutParams();
                    params.height = 0;
                    my_own_unit_listview.setLayoutParams(params);
                }
                else {
                    my_own_unit_listview.setVisibility(View.VISIBLE);
                    my_own_unit_expand_button_image.setImageResource(R.drawable.ic_action_hide);
                    ViewGroup.LayoutParams params = my_own_unit_listview.getLayoutParams();
                    float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 136, getResources().getDisplayMetrics());
                    params.height = (int) pixels;
                    my_own_unit_listview.setLayoutParams(params);
                }
            }
        });
        // Drawer - My Own Unit list view code ends

        // Drawer - My History list view code starts
        // using back the items from my own unit list
        final ListView my_history_listview = (ListView) findViewById(R.id.listViewMainActivityMyHistory);
        my_history_listview.setAdapter(listAdapter);
        my_history_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Unit unit = (Unit) parent.getItemAtPosition(position);
                        Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                    }
                }
        );

        /*if (my_history_listview.getVisibility() == View.VISIBLE) {
            my_history_listview.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = my_history_listview.getLayoutParams();
            params.height = 0;
            my_history_listview.setLayoutParams(params);
        }*/

        final Button expand_my_history_button = (Button) findViewById(R.id.btnMainActivityExpandMyHistory);
        final ImageView my_history_expand_button_image = (ImageView) findViewById(R.id.imageViewMainActitivtyExpandMyHistory);
        expand_my_history_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_history_listview.getVisibility() == View.VISIBLE) {
                    my_history_listview.setVisibility(View.INVISIBLE);
                    my_history_expand_button_image.setImageResource(R.drawable.ic_action_expand);
                    ViewGroup.LayoutParams params = my_history_listview.getLayoutParams();
                    params.height = 0;
                    my_history_listview.setLayoutParams(params);
                } else {
                    my_history_listview.setVisibility(View.VISIBLE);
                    my_history_expand_button_image.setImageResource(R.drawable.ic_action_hide);
                    ViewGroup.LayoutParams params = my_history_listview.getLayoutParams();
                    float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 136, getResources().getDisplayMetrics());
                    params.height = (int) pixels;
                    my_history_listview.setLayoutParams(params);
                }
            }
        });
        // Drawer - My History list view code ends

        // SwipeLayout code starts
        SwipeLayout swipeLayout = (SwipeLayout) findViewById(R.id.swipeLayoutMainActivity);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Top, findViewById(R.id.bottomWrapperSwipeLayoutMainActivity));
        swipeLayout.setRightSwipeEnabled(false);
        // SwipeLayout code ends

        // Search bar code starts
        final Button searchButton = (Button) findViewById(R.id.btnMainActivitySearchButton);
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Search Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Drawer toggle button related code starts
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    // Drawer toggle button related code ends

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Code to hide/unhide menu item
        menu.findItem(R.id.action_settings).setVisible(!hideMenuItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

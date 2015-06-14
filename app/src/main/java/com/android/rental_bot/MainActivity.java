package com.android.rental_bot;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.daimajia.swipe.SwipeLayout;
import com.melnykov.fab.FloatingActionButton;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private boolean hideMenuItem = false;
    private String area;
    private String gender;
    private String furniture;
    private int roomSizeMin;
    private int roomSizeMax;
    private int rentalMin;
    private int rentalMax;


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
        //ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewMainActivityMainContent);
        ListView rListView = (ListView) findViewById(R.id.listViewMainActivityResultList);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMainActivity);
        fab.attachToListView((ListView) rListView);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PostMyRoom.class);
                startActivity(intent);
            }
        });
        // FAB code ends

        // Drawer - My Own Unit list view code starts
        final ArrayList<Unit> arrayOfUnits = new ArrayList<>();
        final Unit unit1 = new Unit(R.drawable.room1, "3, Jalan PJS 11/9", "Bandar Sunway", 700, "Fully Furnished", 400, 359, "M");
        final Unit unit2 = new Unit(R.drawable.room2, "A1-7-5, Vista Komanwel A", "Bukit Jalil", 500, "Fully Furnished", 300, 128, "F");
        final Unit unit3 = new Unit(R.drawable.room3, "A1-7-6, Vista Komanwel A", "Bukit Jalil", 600, "Fully Furnished", 350, 243, "M");
        final Unit unit4 = new Unit(R.drawable.room4, "10, Jalan Radin", "Sri Petaling", 550, "Fully Furnished", 340, 298, "M");
        final Unit unit5 = new Unit(R.drawable.room5, "35, Jalan Merah 9", "Taman Pelangi, Johor Bahru", 340, "Partially Furnished", 200, 148, "F");

        arrayOfUnits.add(unit1);
        arrayOfUnits.add(unit2);
        arrayOfUnits.add(unit3);
        arrayOfUnits.add(unit4);
        arrayOfUnits.add(unit5);
        ListAdapter listAdapter = new MyOwnUnitAdapter(this, arrayOfUnits);
        final ListView my_own_unit_listview = (ListView) findViewById(R.id.listViewMainActivityMyOwnUnit);
        my_own_unit_listview.setAdapter(listAdapter);
        my_own_unit_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Unit unit = (Unit) parent.getItemAtPosition(position);
                        //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
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
                        //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                        // Start RoomDetail Intent
                        Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                        startActivity(intent);
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

        // Result list view code starts
        final ListView result_list = (ListView) findViewById(R.id.listViewMainActivityResultList);
        ListAdapter resultListAdapter = new ResultListAdapter(this, arrayOfUnits);
        result_list.setAdapter(resultListAdapter);
        result_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Unit unit = (Unit) parent.getItemAtPosition(position);
                        //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                        // Start RoomDetail Intent
                        Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                        startActivity(intent);
                    }
                }
        );

        /*// Code to set list view height
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        //float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        ViewGroup.LayoutParams params = result_list.getLayoutParams();
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (dpHeight - 58), getResources().getDisplayMetrics());
        params.height = (int) pixels;
        result_list.setLayoutParams(params);*/
        // Result list view code ends

        // SwipeLayout code starts
        final SwipeLayout swipeLayout = (SwipeLayout) findViewById(R.id.swipeLayoutMainActivity);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Top, findViewById(R.id.bottomWrapperSwipeLayoutMainActivity));
        swipeLayout.setRightSwipeEnabled(false);
        // SwipeLayout code ends

        // Search bar code starts
        final EditText searchEditText = (EditText) findViewById(R.id.editTextMainActivitySearchBar);
        final Button searchButton = (Button) findViewById(R.id.btnMainActivitySearchButton);
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Search Button Clicked", Toast.LENGTH_SHORT).show();
                //swipeLayout.close(true);
            }
        });


        // Search filter code starts
        final Button btnSearchNearMe = (Button) findViewById(R.id.btnSearchNearMe);
        final Button btnSearchSearch = (Button) findViewById(R.id.btnSearchSearch);
        final RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        final RadioGroup radioGroupFurniture = (RadioGroup) findViewById(R.id.radioGroupFurniture);


        RangeBar rangeBarSearchRoomSize = (RangeBar) findViewById(R.id.rangeBarSearchRoomSize);
        rangeBarSearchRoomSize.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int i, int i1, String s, String s1) {
                roomSizeMin = Integer.parseInt(s);
                roomSizeMax = Integer.parseInt(s1);

            }

        });
        RangeBar rangeBarSearchRental = (RangeBar) findViewById(R.id.rangebarSearchRental);


        rangeBarSearchRental.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int i, int i1, String s, String s1) {
                rentalMin = Integer.parseInt(s);
                rentalMax = Integer.parseInt(s1);
            }
        });



        btnSearchNearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeLayout.close(true);
            }
        });

        btnSearchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomSizeMin == 0) {
                    roomSizeMin = 100;
                }
                if (roomSizeMax == 0) {
                    roomSizeMax = 500;
                }
                if (rentalMin == 0) {
                    rentalMin= 200;
                }
                if (rentalMax == 0) {
                    rentalMax = 1000;
                }


                area = searchEditText.getText().toString();

                int selectedGender = radioGroupGender.getCheckedRadioButtonId();
                RadioButton radioButtonGender = (RadioButton) findViewById(selectedGender);


                String rbGenderText = radioButtonGender.getText().toString();
                gender = rbGenderText.substring(0,1).toUpperCase();


                int selectedFurniture = radioGroupFurniture.getCheckedRadioButtonId();
                RadioButton radioButtonFurniture = (RadioButton) findViewById(selectedFurniture);
                String rbFurnitureText = radioButtonFurniture.getText().toString();
                furniture = rbFurnitureText.concat(" Furnished");

                arrayOfUnits.clear();
                arrayOfUnits.add(unit1);
                arrayOfUnits.add(unit2);
                arrayOfUnits.add(unit3);
                arrayOfUnits.add(unit4);
                arrayOfUnits.add(unit5);


                if(!unit1.tenantGender.equals(gender) || !unit1.furnitureOption.equals(furniture) || unit1.unitRental < rentalMin || unit1.unitRental > rentalMax || unit1.roomSize < roomSizeMin || unit1.roomSize > roomSizeMax || !unit1.unitArea.contains(area)) {
                    arrayOfUnits.remove(unit1);
                }
                if(!unit2.tenantGender.equals(gender) || !unit2.furnitureOption.equals(furniture) || unit2.unitRental < rentalMin || unit2.unitRental > rentalMax || unit2.roomSize < roomSizeMin || unit2.roomSize > roomSizeMax || !unit2.unitArea.contains(area)) {
                    arrayOfUnits.remove(unit2);
                }
                if(!unit3.tenantGender.equals(gender) || !unit3.furnitureOption.equals(furniture) || unit3.unitRental < rentalMin || unit3.unitRental > rentalMax || unit3.roomSize < roomSizeMin || unit3.roomSize > roomSizeMax || !unit3.unitArea.contains(area)) {
                    arrayOfUnits.remove(unit3);
                }
                if(!unit4.tenantGender.equals(gender) || !unit4.furnitureOption.equals(furniture) || unit4.unitRental < rentalMin || unit4.unitRental > rentalMax || unit4.roomSize < roomSizeMin || unit4.roomSize > roomSizeMax || !unit4.unitArea.contains(area)) {
                    arrayOfUnits.remove(unit4);
                }
                if(!unit5.tenantGender.equals(gender) || !unit5.furnitureOption.equals(furniture) || unit5.unitRental < rentalMin || unit5.unitRental > rentalMax || unit5.roomSize < roomSizeMin || unit5.roomSize > roomSizeMax || !unit5.unitArea.contains(area)) {
                    arrayOfUnits.remove(unit5);
                }

                ListAdapter listAdapter = new MyOwnUnitAdapter(getApplicationContext(), arrayOfUnits);
                my_own_unit_listview.setAdapter(listAdapter);
                my_own_unit_listview.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Unit unit = (Unit) parent.getItemAtPosition(position);
                                //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                                // Start RoomDetail Intent
                                Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                                startActivity(intent);
                            }
                        }
                );

                my_history_listview.setAdapter(listAdapter);
                my_history_listview.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Unit unit = (Unit) parent.getItemAtPosition(position);
                                //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                                // Start RoomDetail Intent
                                Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                                startActivity(intent);
                            }
                        }
                );

                ListAdapter resultListAdapter = new ResultListAdapter(getApplicationContext(), arrayOfUnits);
                result_list.setAdapter(resultListAdapter);
                result_list.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Unit unit = (Unit) parent.getItemAtPosition(position);
                                //Toast.makeText(MainActivity.this, unit.unitAddress, Toast.LENGTH_LONG).show();
                                // Start RoomDetail Intent
                                Intent intent = new Intent(getApplicationContext(), RoomDetail.class);
                                startActivity(intent);
                            }
                        }
                );

                swipeLayout.close(true);
            }
        });
        // Search filter code ends

        // Notification button listener
        ImageView avatar = (ImageView) findViewById(R.id.imageViewMainActivityUserAvatar);
        avatar.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
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
        /*menu.findItem(R.id.action_settings).setVisible(!hideMenuItem);*/

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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}

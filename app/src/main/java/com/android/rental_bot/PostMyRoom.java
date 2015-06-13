package com.android.rental_bot;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rey.material.widget.Spinner;


public class PostMyRoom extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_my_room);

        Spinner spinnerfurniture = (Spinner) findViewById(R.id.spinnerPostMyRoomFurniture);

        ArrayAdapter<CharSequence> adapterFurniture = ArrayAdapter.createFromResource(this, R.array.furnitureArray, android.R.layout.simple_spinner_item);
        adapterFurniture.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfurniture.setAdapter(adapterFurniture);





        Spinner spinnergender = (Spinner) findViewById(R.id.spinnerPostMyRoomGender);

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this, R.array.genderArray, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(adapterGender);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_my_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_upload) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(PostMyRoom.this, "Your room has been posted succesfully!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

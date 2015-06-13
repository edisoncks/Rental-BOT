package com.android.rental_bot;

/**
 * Created by edison on 6/13/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOwnUnitAdapter extends ArrayAdapter <Unit> {

    public MyOwnUnitAdapter(Context context, ArrayList<Unit> units) {
        super(context, R.layout.my_own_unit, units);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater listInflater = LayoutInflater.from(parent.getContext());
            convertView = listInflater.inflate(R.layout.my_own_unit, parent, false);
        }

        Unit unit = getItem(position);
        TextView my_own_unit_title = (TextView) convertView.findViewById(R.id.my_own_unit_title);
        TextView my_own_unit_subtitle = (TextView) convertView.findViewById(R.id.my_own_unit_subtitle);
        ImageView my_own_unit_image = (ImageView) convertView.findViewById(R.id.my_own_unit_image);

        my_own_unit_title.setText(unit.unitAddress.concat(", ").concat(unit.unitArea));
        my_own_unit_subtitle.setText("Rental: ".concat(unit.getUnitRental()));
        my_own_unit_image.setImageResource(unit.imageResource);
        return convertView;
    }
}
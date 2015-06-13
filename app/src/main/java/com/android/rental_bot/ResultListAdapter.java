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

public class ResultListAdapter extends ArrayAdapter <Unit> {

    public ResultListAdapter(Context context, ArrayList<Unit> units) {
        super(context, R.layout.result_list, units);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater listInflater = LayoutInflater.from(parent.getContext());
            convertView = listInflater.inflate(R.layout.result_list, parent, false);
        }

        Unit unit = getItem(position);
        TextView roomAddress = (TextView) convertView.findViewById(R.id.textViewResultListRoomAddress);
        TextView roomRental = (TextView) convertView.findViewById(R.id.textViewResultListRoomRental);
        TextView roomSize = (TextView) convertView.findViewById(R.id.textViewResultListRoomSize);
        TextView roomViews = (TextView) convertView.findViewById(R.id.textViewResultListRoomViews);
        ImageView roomThumbnail = (ImageView) convertView.findViewById(R.id.imageViewResultListRoomThumbnail);

        roomAddress.setText(unit.unitAddress);
        roomRental.setText(unit.getUnitRental());
        roomSize.setText(Integer.toString(unit.roomSize).concat("m²"));
        roomViews.setText(Integer.toString(unit.roomViews).concat(" Views"));
        roomThumbnail.setImageResource(unit.imageResource);
        return convertView;
    }
}
package com.example.spider.loginscreen.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spider.loginscreen.Model.City;
import com.example.spider.loginscreen.R;

import java.util.List;

/**
 * Created by spider on 12/17/2016.
 */
public class CityDropDownAdapter extends ArrayAdapter<City> {

    LayoutInflater flater;

    public CityDropDownAdapter(LayoutInflater inflater, Context context, List<City> list) {

        super(context, R.layout.city_item_block, list);
        this.flater = inflater;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        City rowItem = getItem(position);
        View rowview = flater.inflate(R.layout.city_item_block, parent, false);
        TextView txtCityName = (TextView) rowview.findViewById(R.id.CityName);
        txtCityName.setText(rowItem.CityName);
        return rowview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


}

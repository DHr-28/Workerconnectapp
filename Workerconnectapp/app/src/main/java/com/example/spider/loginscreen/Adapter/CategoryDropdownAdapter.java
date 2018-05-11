package com.example.spider.loginscreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spider.loginscreen.Model.Category;
import com.example.spider.loginscreen.Model.City;
import com.example.spider.loginscreen.R;

import java.util.List;

/**
 * Created by spider on 2/7/2017.
 */
public class CategoryDropdownAdapter extends ArrayAdapter<Category> {
    LayoutInflater flater;

    public CategoryDropdownAdapter(LayoutInflater inflater, Context context, List<Category> list) {

        super(context, R.layout.cworker_category_block, list);
        this.flater = inflater;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        Category rowItem = getItem(position);
        View rowview = flater.inflate(R.layout.cworker_category_block, parent, false);
        TextView txtWorkerCategory = (TextView) rowview.findViewById(R.id.WorkerCategory);
        txtWorkerCategory.setText(rowItem.Worker_Category);
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

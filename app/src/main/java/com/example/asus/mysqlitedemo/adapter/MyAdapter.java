package com.example.asus.mysqlitedemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.mysqlitedemo.R;
import com.example.asus.mysqlitedemo.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/11/8.
 */

public class MyAdapter extends ArrayAdapter{

    private Context mContext;
    private int mResource;
    private ArrayList<User> mlist = new ArrayList<>();
    public MyAdapter(Context context, int resource,  ArrayList objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mlist = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(mResource, null);
        TextView id=view.findViewById(R.id.lv_id);
        TextView name=view.findViewById(R.id.lv_name);
        TextView grade=view.findViewById(R.id.lv_grade);
        id.setText(mlist.get(position).getId().toString());
        name.setText(mlist.get(position).getName().toString());
        grade.setText(mlist.get(position).getGrade().toString());
        return view;
    }
}

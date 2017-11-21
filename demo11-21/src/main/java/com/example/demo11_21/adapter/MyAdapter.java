package com.example.demo11_21.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demo11_21.R;
import com.example.demo11_21.bean.User;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/11/21.
 */

public class MyAdapter extends ArrayAdapter{
    private Context mContext;
    private int mResource;
    private ArrayList<User> mlist;

    public MyAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
        mlist=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        view=layoutInflater.inflate(mResource,null);
        TextView id=view.findViewById(R.id.tv_id);
        TextView name=view.findViewById(R.id.tv_name);
        TextView password=view.findViewById(R.id.tv_password);
        id.setText(mlist.get(position).getId().toString());
        name.setText(mlist.get(position).getName().toString());
        password.setText(mlist.get(position).getPassword().toString());
        return view;
    }
}

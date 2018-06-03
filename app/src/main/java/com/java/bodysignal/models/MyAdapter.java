package com.java.bodysignal.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.java.bodysignal.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private TextView name;
    private ArrayList<workerDetail> workerdetail;
    private LayoutInflater inflater;

    public MyAdapter(Context c,  ArrayList<workerDetail> worker){
        this.context = c;
        this.workerdetail = worker;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return workerdetail.size();
    }

    @Override
    public Object getItem(int i) {
        return workerdetail.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        }
        name = (TextView) view.findViewById(R.id.text_name);
        name.setText(workerdetail.get(i).getName());
        return view;
    }
}

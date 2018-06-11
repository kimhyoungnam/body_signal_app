package com.java.bodysignal.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.java.bodysignal.R;
import com.java.bodysignal.personalActivity;

import java.util.ArrayList;

public class personAdapter extends BaseAdapter {
    private Context context;
    private TextView name,age,number,phoneNumber,pulse,temp;
    private ArrayList<workerDetail> workerdetail;
    private LayoutInflater inflater;

    public personAdapter(Context c, ArrayList<workerDetail> worker){
        this.context = (Context) c;
        this.workerdetail = worker;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return workerdetail.size();
    }

    @Override
    public Object getItem(int i) {
        return workerdetail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person_list,viewGroup,false);
        }
        name = (TextView) view.findViewById(R.id.m_name);
        age = (TextView)view.findViewById(R.id.m_age);
        number = (TextView)view.findViewById(R.id.m_number);
        phoneNumber = (TextView)view.findViewById(R.id.m_phoneNumber);
        pulse = (TextView)view.findViewById(R.id.text_pulsedata);
        temp = (TextView)view.findViewById(R.id.text_temperdata);

        name.setText(workerdetail.get(i).getName());
        Log.d("aaa123",workerdetail.get(i).getName());
        age.setText(workerdetail.get(i).getAge());

        number.setText(workerdetail.get(i).getNumber());
        phoneNumber.setText(workerdetail.get(i).getPhoneNumber());
        pulse.setText(workerdetail.get(i).getPulse());
        temp.setText(workerdetail.get(i).getTemperature());


        return view;
    }

}

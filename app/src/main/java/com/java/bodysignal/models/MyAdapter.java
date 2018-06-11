package com.java.bodysignal.models;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.java.bodysignal.R;
import com.java.bodysignal.personalActivity;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private TextView name,pulse,temperature;
    Button personalBtn;
    private ArrayList<workerDetail> workerdetail;
    private LayoutInflater inflater;
    final workerDetail w = workerDetail.getWorkerObject();
    int num;
    public MyAdapter(Context c,  ArrayList<workerDetail> worker){
        this.context = c;
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
            view = inflater.inflate(R.layout.list_layout,viewGroup,false);
        }
        Log.i("aaa123","aa"+i);
        name = (TextView) view.findViewById(R.id.text_name);
        name.setText(workerdetail.get(i).getName());

        pulse = (TextView)view.findViewById(R.id.pulse);
        pulse.setText(workerdetail.get(i).getPulse());

        temperature = (TextView)view.findViewById(R.id.temp);
        temperature.setText(workerdetail.get(i).getTemperature());
        personalBtn = (Button) view.findViewById(R.id.personalBtn);

        personalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hohoho",name.getText().toString());
                Intent intent = new Intent(context, personalActivity.class);
                //intent.putExtra("name",name.getText().toString());
                context.startActivity(intent);

            }
        });

        return view;
    }


    @Override
    public void onClick(View view) {

    }



}

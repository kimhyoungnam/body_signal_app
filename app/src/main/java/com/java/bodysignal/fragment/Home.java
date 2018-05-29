package com.java.bodysignal.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.java.bodysignal.R;


public class Home extends Fragment {
     public  Button calling, breakButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        calling = (Button)view.findViewById( R.id.calling);
        breakButton =   (Button)view.findViewById( R.id.breakButton);
     //   calling.setOnClickListener(myListener);
       // breakButton .setOnClickListener(myListener);
        return view;
    }




}
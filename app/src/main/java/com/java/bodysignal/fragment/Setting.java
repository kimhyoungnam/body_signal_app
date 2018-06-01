package com.java.bodysignal.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.bodysignal.LoginActivity;
import com.java.bodysignal.R;


import android.widget.Button;


public class Setting extends Fragment {
    Button button1,button2,button3,button4;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_setting,container,false);

        button1 = (Button)view.findViewById( R.id.button1 );
        button2 = (Button)view.findViewById( R.id.button2 );
        button3 = (Button)view.findViewById( R.id.button3 );
        button4 = (Button)view.findViewById( R.id.button4 );

        button1.setOnClickListener(myListener);
        button2.setOnClickListener(myListener);
        button3.setOnClickListener(myListener);
        button4.setOnClickListener(myListener);

        return view;
    }
    View.OnClickListener myListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            button1.setSelected(false);
            button2.setSelected(false);
            button3.setSelected(false);
            button4.setSelected(false);

            switch (v.getId()) {
                case R.id.button1:
                    button1.setSelected(true);
                    moveButton1();
                    break;
                case R.id.button2:
                    button2.setSelected(true);
                    moveButton2();
                    break;
                case R.id.button3:
                    button3.setSelected(true);
                    moveButton3();
                    break;
                case R.id.button4:
                    button4.setSelected(true);

                    moveButton4();
                    break;


            }

        }
    };
    public void moveButton1(){
        getFragmentManager().beginTransaction().replace(R.id.main_frame,new AccountModify()).commit();

    }
    public void moveButton2(){
        getFragmentManager().beginTransaction().replace(R.id.main_frame,new WorkerRegister()).commit();

    }
    public void moveButton3(){
        getFragmentManager().beginTransaction().replace(R.id.main_frame,new Help()).commit();

    }
    public void moveButton4(){

        Intent intent = new Intent(getActivity().getApplication(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

    }


}
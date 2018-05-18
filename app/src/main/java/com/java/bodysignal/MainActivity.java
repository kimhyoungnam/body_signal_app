package com.java.bodysignal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import android.support.v4.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout button1 = (LinearLayout) findViewById(R.id.button1);
        LinearLayout button2 = (LinearLayout) findViewById(R.id.button2);
        LinearLayout button3 = (LinearLayout) findViewById(R.id.button3);
        LinearLayout button4 = (LinearLayout) findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Home()).commit();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new allChart()).commit();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Weather()).commit();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Setting()).commit();

            }
        });
    }
}
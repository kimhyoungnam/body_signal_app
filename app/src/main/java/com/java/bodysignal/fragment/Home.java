package com.java.bodysignal.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;



import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.util.Timer;
import java.util.UUID;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.java.bodysignal.MainActivity;
import com.java.bodysignal.R;
import com.java.bodysignal.models.MyAdapter;
import com.java.bodysignal.models.registerDetail;
import com.java.bodysignal.models.workerDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class Home extends Fragment {

    public Button breakbtn,emergency;

    private Timer update;

    ListView list;
    public MyAdapter adapter;

    final registerDetail r= registerDetail.getRegisterObject();
    final workerDetail w = workerDetail.getWorkerObject();
    public ArrayList<workerDetail> workerdetail;
    String name,age,phoneNum,manager,temp,pulse;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        Log.d("hoho", "Home oncreateview");
        list = (ListView) view.findViewById(R.id.list);
        breakbtn= (Button)view.findViewById( R.id.breakbtn);
        emergency= (Button)view.findViewById( R.id.emergency);
        workerdetail =new ArrayList<workerDetail>();
        return view;

    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        workerdetail=(((MainActivity)getActivity())).workerdetail;
        adapter = new MyAdapter(getActivity(), workerdetail);
        list.setAdapter(adapter);

        breakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                try {
                    c.startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public  void showMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");

        builder.setMessage("break알림을 보내겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MainActivity)getActivity()).sendData("b");
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
package com.java.bodysignal.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;import java.util.Set;
// 3. UUID : Universally Unique IDentifier, 범용 고유 실별자.import java.util.UUID;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
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
import com.java.bodysignal.R;
import com.java.bodysignal.models.MyAdapter;
import com.java.bodysignal.models.registerDetail;
import com.java.bodysignal.models.workerDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Home extends Fragment {

    public Button breakbtn,emergency;
    public DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference data=mDatabase.child("worker");

    private ChildEventListener mChildEventListener;
    ListView list;
    MyAdapter adapter;

    final registerDetail r= registerDetail.getRegisterObject();
    final workerDetail w = new workerDetail();
    ArrayList<workerDetail> workerdetail;
    String name,age,phoneNum,manager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        list = (ListView) view.findViewById(R.id.list);
        breakbtn= (Button)view.findViewById( R.id.breakbtn);
        emergency= (Button)view.findViewById( R.id.emergency);
        workerdetail=new ArrayList<workerDetail>();
        getAllMyData();

        // list = (ListView) view.findViewById(R.id.list);


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

        return view;

    }


    public  void showMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");

        builder.setMessage("break알림을 보내겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getActivity().getApplicationContext(), " 전송되었습니다 ", Toast.LENGTH_LONG).show();

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
    private void getAllMyData(){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    name = snapshot.child("name").getValue(String.class);
                    phoneNum = snapshot.child("number").getValue(String.class);
                    // String num=snapshot.child("number").getValue().toString();
                    age = snapshot.child("age").getValue(String.class);
                    manager=snapshot.child("manager").getValue(String.class);
                    if(r.getId().equals(manager)) {
                        workerdetail.add(new workerDetail(name, phoneNum, age));

                        adapter = new MyAdapter(getActivity(), workerdetail);
                        list.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        data.addValueEventListener(postListener);


    }




}
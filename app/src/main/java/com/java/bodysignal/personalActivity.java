package com.java.bodysignal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.java.bodysignal.fragment.Home;
import com.java.bodysignal.models.MyAdapter;
import com.java.bodysignal.models.personAdapter;
import com.java.bodysignal.models.workerDetail;

import java.util.ArrayList;

public class personalActivity extends AppCompatActivity {

    public DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference data=mDatabase.child("worker");
    private ChildEventListener mChildEventListener;

    final workerDetail w = workerDetail.getWorkerObject();
    public ArrayList<workerDetail> workerdetail;
    public  String name,phoneNum,age,temp,pulse;
    String master;
    ListView list;
    public personAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Intent intent= getIntent();
        master="이정환";
       // master=intent.getStringExtra("name");
        workerdetail =new ArrayList<workerDetail>();
        workerdetail =new ArrayList<workerDetail>();


    }
    @Override
    public void onStart(){
        super.onStart();
        data.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    name = snapshot.child("name").getValue(String.class);
                    phoneNum = snapshot.child("number").getValue(String.class);
                    age = snapshot.child("age").getValue(String.class);
                    temp=snapshot.child("temperature").getValue(String.class);
                    pulse=snapshot.child("pulse").getValue(String.class);
                    Log.d("a123",name);
                    if(master.equals(name)) {
                        workerdetail.add(new workerDetail(name, phoneNum, age,temp,pulse));
                        Log.d("aaa123",name+"/"+phoneNum+"/"+age+"/"+temp+"/"+pulse);
                        break;
                    }
                }
                adapter = new personAdapter( getApplicationContext(), workerdetail);
                list = (ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




    }

}

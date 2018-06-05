package com.java.bodysignal.fragment;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.java.bodysignal.MainActivity;
import com.java.bodysignal.R;
import com.java.bodysignal.models.MyAdapter;
import com.java.bodysignal.models.registerDetail;
import com.java.bodysignal.models.workerDetail;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkerRegister extends Fragment{

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private ChildEventListener mChildEventListener;
    private EditText name,age,number,phoneNumber;
    private Button button;
    ConstraintLayout down;
    final registerDetail r= registerDetail.getRegisterObject();
    final workerDetail w = workerDetail.getWorkerObject();
    int count;
    String newWorker,workerAge,workerNum,workerPhone,manager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.worker_register, container, false);
        down = (ConstraintLayout) view.findViewById(R.id.down);
        name= (EditText) view.findViewById( R.id.name);
        age= (EditText) view.findViewById( R.id.age);
        number= (EditText) view.findViewById( R.id.number);
        phoneNumber= (EditText) view.findViewById( R.id.phoneNumber);
        button= (Button)view.findViewById( R.id.button);
        manager=r.getId();
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newWorker = name.getText().toString();
                workerAge = age.getText().toString();
                workerNum = number.getText().toString();
                workerPhone = phoneNumber.getText().toString();
                showMessage();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

    }

    public  void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");
        builder.setMessage("정보 등록 하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (newWorker.equals("") || workerAge.equals("") || workerNum.equals("") || workerPhone.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), " 생략하지말고 등록하세요 ", Toast.LENGTH_LONG).show();
                } else {
                    w.setName(newWorker);
                    w.setAge(workerAge);
                    w.setPhoneNumber(workerPhone);
                    w.setNumber(workerNum);
                    w.setPulse("78");
                    w.setTemperature("36");
                    w.setManager(r.getId());

                    mDatabase.child("worker").child(newWorker).setValue(w);

                    Toast.makeText(getActivity().getApplicationContext(), " 작업자 등록 ", Toast.LENGTH_LONG).show();

                }

            }
        }).setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Setting()).commit();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }


}
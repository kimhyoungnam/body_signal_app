package com.java.bodysignal.fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.java.bodysignal.R;
import com.java.bodysignal.models.registerDetail;
import com.java.bodysignal.models.workerDetail;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkerRegister extends Fragment {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private ChildEventListener mChildEventListener;
    private EditText name,age,number,phoneNumber;
    private Button button;
    final registerDetail r= registerDetail.getRegisterObject();
    final workerDetail w = new workerDetail();
    String newWorker,workerAge,workerNum,workerPhone,manager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.worker_register, container, false);
        name= (EditText) view.findViewById( R.id.name);
        age= (EditText) view.findViewById( R.id.age);
        number= (EditText) view.findViewById( R.id.number);
        phoneNumber= (EditText) view.findViewById( R.id.phoneNumber);
        button= (Button)view.findViewById( R.id.button);




        manager=r.getId();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                newWorker = name.getText().toString();
                workerAge = age.getText().toString();
                workerNum = number.getText().toString();
                workerPhone = phoneNumber.getText().toString();


                w.setName(newWorker);
                w.setAge(workerAge);
                w.setPhoneNumber(workerPhone);
                w.setNumber(workerNum);

                w.setManager(r.getId());
                mDatabase.child("worker").child(newWorker).setValue(w);
                //Log.d("name",manager);
                showMessage();
            }
        });
        return view;
    }

    public  void showMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");

        builder.setMessage("정보 등록 하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getActivity().getApplicationContext(), " 작업자 등록 ", Toast.LENGTH_LONG).show();

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
                getFragmentManager().beginTransaction().replace(R.id.main_frame,new Setting()).commit();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
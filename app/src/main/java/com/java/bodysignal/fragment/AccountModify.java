package com.java.bodysignal.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.java.bodysignal.R;
import com.java.bodysignal.models.registerDetail;
import com.java.bodysignal.models.workerDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AccountModify extends Fragment{

    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference data=mDatabase.child("users");
    ArrayList<registerDetail> loginArray;
    private ChildEventListener mChildEventListener;

    private EditText name,userid,pwd,checkPwd;
    private Button modifyButton;
    final registerDetail r= registerDetail.getRegisterObject();
    String ogName,ogId,ogPwd;
    String checkName,checkId,newPwd,recheckpwd;
    ConstraintLayout keyboard;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity().getApplicationContext(), " 비밀번호만 변경 가능합니다. ", Toast.LENGTH_LONG).show();
        View view= inflater.inflate(R.layout.fragment_accountmodify,container,false);
        name= (EditText) view.findViewById( R.id.name);
        userid= (EditText) view.findViewById( R.id.userid);
        pwd= (EditText) view.findViewById( R.id.pwd);
        checkPwd= (EditText) view.findViewById( R.id.checkPwd);
        modifyButton= (Button)view.findViewById( R.id.modifyButton);

        checkName = name.getText().toString();
        checkId = userid.getText().toString();
        newPwd = pwd.getText().toString();
        recheckpwd = checkPwd.getText().toString();
        keyboard = (ConstraintLayout) view.findViewById(R.id.down);
        loginArray = new ArrayList<registerDetail>();

        return view;
    }

        public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ogId = snapshot.child("id").getValue(String.class);
                        ogName = snapshot.child("name").getValue(String.class);
                        ogPwd = snapshot.child("password").getValue(String.class);


                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            modifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showMessage();
                }
            });
            keyboard.setOnClickListener(new View.OnClickListener() {
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
        builder.setMessage("개인 정보 수정하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (checkId.equals(ogId)) {

                    mDatabase.child("users").child(checkId).child("password").setValue(ogPwd);
                }
                Toast.makeText(getActivity().getApplicationContext(), " 이름과 아이디는 변경 불가능 ", Toast.LENGTH_LONG).show();

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

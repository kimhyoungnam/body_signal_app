package com.java.bodysignal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.java.bodysignal.models.registerDetail;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText pwd;
    private EditText checkpwd;
    private Button btnRegister;
    String sname,id, password, check;
    // firebase
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // xml에 해당하는것 설정
        name = (EditText) findViewById(R.id.editText1);
        email = (EditText) findViewById(R.id.editText2);
        pwd = (EditText) findViewById(R.id.editText3);
        checkpwd = (EditText) findViewById(R.id.editText4);
        btnRegister = (Button) findViewById(R.id.button);


        btnRegister.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // edittext 문자열로 전환
                sname = name.getText().toString();
                id = email.getText().toString();
                password = pwd.getText().toString();
                check = checkpwd.getText().toString();
                // setting

                registerDetail r = new registerDetail();
                r.setName(sname);
                r.setId(id);
                r.setPassword(password);


                if (password.equals(check)) {
                    if(sname.equals("")||id.equals("")) {
                        Toast.makeText(getApplicationContext(), "이름 혹은 아이디를 생략하지말고 입력해주세요 ", Toast.LENGTH_LONG).show();

                    }else {
                        mDatabase.child("users").child(id).setValue(r);
                        Toast.makeText(getApplicationContext(), " 가입완료 ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다! 다시입력해주세요", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}

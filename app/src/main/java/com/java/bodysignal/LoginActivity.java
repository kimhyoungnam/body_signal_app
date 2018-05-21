package com.java.bodysignal;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FacebookAuthProvider;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {



    private CallbackManager callbackManager;
    private LoginButton fbButton;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();;
    private EditText id;
    private EditText password;
    private Button login;
    private Button Register;
    String newId,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);



        fbButton = findViewById(R.id.fb_login_button);

        id = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);

        login= (Button) findViewById(R.id.button1);
        Register= (Button) findViewById(R.id.button2);


        login.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                newId = id.getText().toString();
                pwd = password.getText().toString();
                if(newId.equals("")||pwd.equals("")) {
                    Toast.makeText(getApplicationContext(), "로그인을 먼저 해주세요!", Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }

        });

        Register.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLogin();
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    public void fbLogin() {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override

            public void onSuccess(final LoginResult result) {

                 Log.d("Tag",  result.getAccessToken().getToken().toString());
                final GraphRequest request;

                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            Log.i("TAG", "user: " + user.toString());
                            Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                            setResult(RESULT_OK);

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                Toast.makeText(LoginActivity.this, "페이스북 로그인 에러, 에러 내용 : "+error, Toast.LENGTH_SHORT).show();
                //finish();
            }

            @Override
            public void onCancel() {
                //finish();

                Log.e("test", "ㅇㅇemfjddhㅇ");

            }
        });
    }
}
package com.lyt.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        EditText phoneET =(EditText) findViewById(R.id.phone_et);
        EditText psdET = findViewById(R.id.psd_et);

        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            Map<String,String> map = new HashMap<>();
            map.put("username",phoneET.getText().toString());
            map.put("password",psdET.getText().toString());
            OkHttpManager.getInstance().post("/agent-gateway/login", map, new OkHttpManager.ResponseCallBack() {
                @Override
                public void onFailure(String e) {
                    Toast.makeText(LoginActivity.this,e,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(Object response) {
                    Log.d(TAG,response.toString());
                }
            });
        });
    }
}

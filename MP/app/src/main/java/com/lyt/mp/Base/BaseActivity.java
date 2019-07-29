package com.lyt.mp.Base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lyt.mp.R;

public class BaseActivity extends AppCompatActivity {

    public ViewGroup container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = (ViewGroup) getLayoutInflater().inflate(R.layout.base_activity_layout,null);
        setContentView(container);

        ImageView backImg = findViewById(R.id.customtool_left_img);
        backImg.setOnClickListener(v -> {
            backAction();
        });
    }

    public void setToolbarTitle(String title){
        if (title != null){
            TextView titleTV = findViewById(R.id.customtool_title_tv);
            titleTV.setText(title);
        }
    }

    public void setToolbarRightTitle(String rightTitle){
        if (rightTitle != null){
            TextView rightTV = findViewById(R.id.customtool_right_tv);
            rightTV.setText(rightTitle);
        }
    }

    public void backAction(){
        finish();
    }
}

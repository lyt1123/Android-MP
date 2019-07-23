package com.lyt.mp.Mine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lyt.mp.MyApplication;
import com.lyt.mp.Profit.MyProfitAdapter;
import com.lyt.mp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MineFragment extends Fragment {
    private String TAG = "MineFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.mine_fragment_layout,container,false);
        initViews(view);
        return view;
    }

    protected void initViews(View view){

        TextView settleTV = view.findViewById(R.id.mine_settle_et);
        TextView expendTV = view.findViewById(R.id.mine_expend_et);
        TextView questionTV = view.findViewById(R.id.mine_question_et);
        TextView serviceTV = view.findViewById(R.id.mine_service_et);
        TextView aboutTV = view.findViewById(R.id.mine_about_et);
        TextView settingTV = view.findViewById(R.id.mine_setting_et);

        settleTV.setOnClickListener(v -> {
            Log.d(TAG,"settle");
        });

        expendTV.setOnClickListener(v -> {
            Log.d(TAG,"expendTV");
        });

        questionTV.setOnClickListener(v -> {
            Log.d(TAG,"questionTV");
        });

        serviceTV.setOnClickListener(v -> {
            Log.d(TAG,"service");
        });

        aboutTV.setOnClickListener(v -> {
            Log.d(TAG,"aboutTV");
        });

        settingTV.setOnClickListener(v -> {
            Log.d(TAG,"settingTV");
        });
    }
}

package com.lyt.mp.Mine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.mine_fragment_layout,container,false);
        initViews(view);
        return view;
    }

    protected void initViews(View view){

    }
}

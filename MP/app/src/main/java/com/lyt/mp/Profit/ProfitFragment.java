package com.lyt.mp.Profit;

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
import com.lyt.mp.R;
import com.lyt.mp.UserDataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfitFragment extends Fragment {

    private ListView listView;
    private MyProfitAdapter myProfitAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.profit_fragment_layout,container,false);
        initViews(view);
        return view;
    }

    public void initViews(View view){
        listView = view.findViewById(R.id.listview_profit);

        ArrayList<Map> listItems = new ArrayList<>();

        for (int i = 0; i <2; i++) {
            Map<String,String> listItem = new HashMap<>();
            if (i == 0){
                listItem.put("title","交易分润");
                listItem.put("money","871.89");
                listItem.put("icon","profit_trade_image");
            }else {
                listItem.put("title","返现分润");
                listItem.put("money","123.89");
                listItem.put("icon","profit_return_image");
            }
            listItems.add(listItem);
        }
        myProfitAdapter = new MyProfitAdapter(MyApplication.getInstance(), listItems);
        listView.setAdapter(myProfitAdapter);

        listView.addHeaderView(getLayoutInflater().inflate(R.layout.profit_header_layout,null,false));

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Log.d("profit",String.valueOf(position));
        });
    }
}

package com.lyt.mp.Home;

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
import java.util.List;
import java.util.Map;

public class MessageSystermFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.message_list_layout,container,false);
        initViews(view);
        return view;
    }

    public void initViews(View view){
      ListView listView = view.findViewById(R.id.listview_message);

        ArrayList<Map> listItems = new ArrayList<>();

        for (int i = 0; i <10; i++) {
            Map<String,String> listItem = new HashMap<>();
            if (i % 2 == 0){
                listItem.put("time","2019-06-0" + i);
                listItem.put("title","系统公告" + i);
                listItem.put("content","撒旦法全额发起违反是的" + i);
            }else {
                listItem.put("time","2019-05-0" + i);
                listItem.put("title","123.89");
                listItem.put("content","注册是注册主线程v新政策v" + i);
            }
            listItems.add(listItem);
        }

        MessageListAdapter adapter = new MessageListAdapter(MyApplication.getInstance(),listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Log.d("profit",String.valueOf(position));
        });
    }
}

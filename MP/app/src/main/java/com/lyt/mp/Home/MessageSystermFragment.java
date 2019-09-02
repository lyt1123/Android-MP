package com.lyt.mp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lyt.mp.LoginActivity;
import com.lyt.mp.MainActivity;
import com.lyt.mp.MyApplication;
import com.lyt.mp.Profit.MyProfitAdapter;
import com.lyt.mp.R;
import com.lyt.mp.UserDataManager;
import com.lyt.mp.network.OkHttpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageSystermFragment extends Fragment {

    private ArrayList<Map> listItems = new ArrayList<>();
    private MessageListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.message_list_layout,container,false);
        initViews(view);
        getSystermData();
        return view;
    }

    public void initViews(View view){
      ListView listView = view.findViewById(R.id.listview_message);

        adapter = new MessageListAdapter(MyApplication.getInstance(),listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Log.d("profit",String.valueOf(position));
        });
    }

    public void getSystermData(){

        HashMap map = new HashMap();
        map.put("currentPage","1");

        OkHttpManager.getInstance().post("/agent-gateway/appNotice/findByPageAll", map,new OkHttpManager.ResponseCallBack() {
            @Override
            public void onSuccess(Map response) {
                if (Double.valueOf(response.get("code").toString()) == 200){
                    listItems.addAll((ArrayList) response.get("object"));
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MyApplication.getInstance(),response.get("message").toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String e) {
                Toast.makeText(MyApplication.getInstance(),e,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

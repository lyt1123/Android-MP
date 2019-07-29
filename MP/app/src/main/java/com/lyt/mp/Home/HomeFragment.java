package com.lyt.mp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lyt.mp.MyApplication;
import com.lyt.mp.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements OnBannerListener {

    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        initBannerView(view);
        initFuncView(view);

        Button messageBtn =  view.findViewById(R.id.btn_message);
        messageBtn.setOnClickListener(v -> {
           startActivity(new Intent(getActivity(), MessageListAcitvity.class));
        });

        return view;
    }

    private void initBannerView(View view) {
        banner = view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        List<String> images = new ArrayList<>();
        images.add("http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg");
        images.add("http://pic41.nipic.com/20140508/18609517_112216473140_2.jpg");
        images.add("http://img.ph.126.net/lCFW6F0A46eced8IjrhWOQ==/2570148012361858430.jpg");
        images.add("http://pic24.nipic.com/20121013/2908050_092109373129_2.jpg");
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
//        banner.setOnBannerListener(position -> {
//            Log.i("OnBannerClick",String.valueOf(position));
//        });
        banner.setOnBannerListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        Log.i("OnBannerClick", String.valueOf(position));
    }

    private void initFuncView(View view){

        int[] icon = {R.drawable.home_netin,
                R.drawable.home_refer,
                R.drawable.home_store,
                R.drawable.home_terminal,
                R.drawable.home_trade,
                R.drawable.home_next,
                R.drawable.home_top,
                R.drawable.home_share};
        String[] name = {"商户入网","商户查询","终端采购","终端管理","交易统计","下级管理","TOP10","商户分享"};

        List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i = 0; i <icon.length ; i++) {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("icon",icon[i]);
            listItem.put("name",name[i]);
            listItems.add(listItem);
        }

        GridView gridView = view.findViewById(R.id.gridView_func);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                MyApplication.getInstance(),
                listItems,
                R.layout.item_function_layout,
                new String[]{"icon","name"},
                new int[]{R.id.item_img,R.id.item_tv});
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(MyApplication.getInstance(),name[position],Toast.LENGTH_SHORT).show();
        });
    }
}

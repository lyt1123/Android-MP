package com.lyt.mp.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.lyt.mp.Base.BaseFragment;
import com.lyt.mp.MyApplication;
import com.lyt.mp.R;

public class MessageListFragment extends BaseFragment {

    private ViewPager viewPager;

    @Override
    public View initView(@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) getLayoutInflater().inflate(R.layout.message_switchbtn_layout,container,false);
        return viewPager;
    }
}

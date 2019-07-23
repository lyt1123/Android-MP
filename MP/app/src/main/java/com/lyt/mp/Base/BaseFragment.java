package com.lyt.mp.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.lyt.mp.R;

public class BaseFragment extends Fragment {

    public ViewGroup toolView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        toolView = (ViewGroup) getLayoutInflater().inflate(R.layout.base_fragment_layout,container,false);
        View subView = initView(container,savedInstanceState);
        if (subView != null){
            toolView.addView(subView);
        }
        ImageView backImg = (toolView).findViewById(R.id.customtool_left_img);
        backImg.setOnClickListener(v -> {
            backAction();
        });
        return toolView;
    }

    public View initView(@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return null;
    }

    public void setToolbarTitle(String title){
        if (title != null){
            TextView titleTV = toolView.findViewById(R.id.customtool_title_tv);
            titleTV.setText(title);
        }
    }

    public void setToolbarRightTitle(String rightTitle){
        if (rightTitle != null){
            TextView rightTV = toolView.findViewById(R.id.customtool_right_tv);
            rightTV.setText(rightTitle);
        }
    }

    public void backAction(){
        getFragmentManager().popBackStack();
    }
}

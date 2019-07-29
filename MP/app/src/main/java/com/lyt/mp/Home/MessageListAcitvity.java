package com.lyt.mp.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lyt.mp.Base.BaseActivity;
import com.lyt.mp.R;

import java.util.ArrayList;

public class MessageListAcitvity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private Button sysBtn;
    private Button perBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.message_switchbtn_layout, null);

        ConstraintLayout constraintLayout = findViewById(R.id.base_top_container);

        ConstraintSet constraintSet = new ConstraintSet();//新建一个ConstraintSet

        constraintLayout.addView(view);

        constraintSet.clone(constraintLayout);

        constraintSet.constrainWidth(view.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
        constraintSet.constrainHeight(view.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);

        constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(view.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(view.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,45);
        constraintSet.applyTo(constraintLayout);


        ViewPager viewPager = (ViewPager) getLayoutInflater().inflate(R.layout.message_viewpage_layout,null);

        container.addView(viewPager);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        MessagePersonalFragment personalFragment = new MessagePersonalFragment();

        datas.add(new MessageSystermFragment());
        datas.add(new MessagePersonalFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return datas.get(position);
            }

            @Override
            public int getCount() {
                return datas.size();
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        sysBtn = (Button)findViewById(R.id.message_systermBtn);
        perBtn = (Button)findViewById(R.id.message_userBtn);

        sysBtn.setOnClickListener(v -> {
            viewPager.setCurrentItem(0);

        });

        perBtn.setOnClickListener(v -> {
            viewPager.setCurrentItem(1);
        });
    }

    private void showSelectBtn(int index){
        if (index == 1){
            sysBtn.setBackgroundResource(R.drawable.message_sysbtn_select_shape);
            perBtn.setBackgroundResource(R.drawable.message_perbtn_dissel_shape);

            sysBtn.setTextColor(getResources().getColor(R.color.colorwhite));
            perBtn.setTextColor(getResources().getColor(R.color.normalBlueColor));

        }else {
            sysBtn.setBackgroundResource(R.drawable.message_sysbtn_dissel_shape);
            perBtn.setBackgroundResource(R.drawable.message_perbtn_select_shape);

            sysBtn.setTextColor(getResources().getColor(R.color.normalBlueColor));
            perBtn.setTextColor(getResources().getColor(R.color.colorwhite));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        showSelectBtn(position + 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}

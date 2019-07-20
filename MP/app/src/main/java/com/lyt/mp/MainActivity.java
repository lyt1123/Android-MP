package com.lyt.mp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lyt.mp.Home.HomeFragment;
import com.lyt.mp.Mine.MineFragment;
import com.lyt.mp.Profit.ProfitFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_MINE = 2;
    private HomeFragment homeFragment;
    private ProfitFragment profitFragment;
    private MineFragment mineFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showFragment(FRAGMENT_HOME);
    }

    public void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    showFragment(FRAGMENT_HOME);
                    break;
                case R.id.action_profit:
                    showFragment(FRAGMENT_PROFILE);
                    break;
                case R.id.action_mine:
                    showFragment(FRAGMENT_MINE);
                    break;
            }
            return true;
        });
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_HOME:
                /*
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.container, homeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(homeFragment);
                }
                break;

            case FRAGMENT_PROFILE:
                if (profitFragment == null) {
                    profitFragment = new ProfitFragment();
                    ft.add(R.id.container, profitFragment, ProfitFragment.class.getName());
                } else {
                    ft.show(profitFragment);
                }
                break;

            case FRAGMENT_MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.container, mineFragment, MineFragment.class.getName());
                } else {
                    ft.show(mineFragment);
                }
                break;
        }

        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (profitFragment != null) {
            ft.hide(profitFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }


}

package com.example.administrator.mygift.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.ui.activity.BaseActivity;
import com.example.administrator.mygift.ui.fragment.CategoryFragment;
import com.example.administrator.mygift.ui.fragment.GuideFragment;
import com.example.administrator.mygift.ui.fragment.HotFragment;
import com.example.administrator.mygift.ui.fragment.MyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private GuideFragment guideFragment;
    private HotFragment hotFragment;
    private CategoryFragment categoryFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        guideFragment = GuideFragment.newInstance(null, null);
        hotFragment = HotFragment.newInstance(null, null);
        categoryFragment = new CategoryFragment();
        myFragment = new MyFragment();
        fragmentTransaction.add(R.id.main_fragment_container, guideFragment);
        fragmentTransaction.add(R.id.main_fragment_container, hotFragment);
        fragmentTransaction.add(R.id.main_fragment_container, categoryFragment);
        fragmentTransaction.add(R.id.main_fragment_container, myFragment);
        fragmentTransaction.hide(hotFragment);
        fragmentTransaction.hide(categoryFragment);
        fragmentTransaction.hide(myFragment);
        fragmentTransaction.commit();
    }

    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()) {
            case R.id.main_guide_rbt:
                    fragmentTransaction.show(guideFragment);
                break;
            case R.id.main_hot_rbt:
                    fragmentTransaction.show(hotFragment);
                break;
            case R.id.main_category_rbt:
                    fragmentTransaction.show(categoryFragment);
                break;
            case R.id.main_my_rbt:
                fragmentTransaction.show(myFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (guideFragment != null) {
            fragmentTransaction.hide(guideFragment);
        }
        if (hotFragment != null) {
            fragmentTransaction.hide(hotFragment);
        }
        if (categoryFragment != null) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
    }
}

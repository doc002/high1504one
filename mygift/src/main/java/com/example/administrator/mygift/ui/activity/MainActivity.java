package com.example.administrator.mygift.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.ui.activity.BaseActivity;
import com.example.administrator.mygift.ui.fragment.GuideFragment;
import com.example.administrator.mygift.ui.fragment.HotFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private GuideFragment guideFragment;
    private HotFragment hotFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        guideFragment = GuideFragment.newInstance(null, null);
        fragmentTransaction.add(R.id.main_fragment_container, guideFragment);
        fragmentTransaction.commit();
    }

    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()) {
            case R.id.main_guide_rbt:
                if (guideFragment == null) {
                    guideFragment = GuideFragment.newInstance(null, null);
                    fragmentTransaction.add(R.id.main_fragment_container, guideFragment);
                } else {
                    fragmentTransaction.show(guideFragment);
                }
                break;
            case R.id.main_hot_rbt:
                if (hotFragment == null) {
                    hotFragment = HotFragment.newInstance(null, null);
                    fragmentTransaction.add(R.id.main_fragment_container, hotFragment);
                } else {
                    fragmentTransaction.show(hotFragment);
                }
                break;
            case R.id.main_category_rbt:
                break;
            case R.id.main_my_rbt:
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
    }
}

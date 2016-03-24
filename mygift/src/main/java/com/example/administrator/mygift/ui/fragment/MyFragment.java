package com.example.administrator.mygift.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.ui.activity.ScannerActivity;

/**
 * Created by Administrator on 16-3-17.
 */
public class MyFragment extends BaseFragment {
    private View view;
    private ImageView settingImg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fagment_my, null);
        settingImg = (ImageView) view.findViewById(R.id.my_scanner_img);
        setListener();
        return view;
    }

    private void setListener() {
        settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}

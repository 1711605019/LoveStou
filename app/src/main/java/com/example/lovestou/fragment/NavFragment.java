package com.example.lovestou.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lovestou.R;
import com.example.lovestou.activity.HmstVideoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends Fragment {
    private View navView;
    private ImageView iv_hmst;

    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        navView = inflater.inflate(R.layout.fragment_nav, container, false);

        initView();
        initData();
        initAnimation();
        return navView;
    }

    private void initData() {

    }

    private void initView() {
        iv_hmst = navView.findViewById(R.id.iv_hmst);
        iv_hmst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HmstVideoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initAnimation() {

    }
}

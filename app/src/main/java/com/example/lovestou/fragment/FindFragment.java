package com.example.lovestou.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lovestou.R;
import com.example.lovestou.activity.IDActivity;
import com.example.lovestou.activity.IPActivity;
import com.example.lovestou.activity.PhoneActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements View.OnClickListener{
    private View findView;
    private LinearLayout ll_phone,ll_ID,ll_IP;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        findView = inflater.inflate(R.layout.fragment_find, container, false);

        initView();
        return findView;
    }

    private void initView() {
        ll_phone = findView.findViewById(R.id.ll_phone);
        ll_ID = findView.findViewById(R.id.ll_sfz);
        ll_IP = findView.findViewById(R.id.ll_ip);
        ll_phone.setOnClickListener(this);
        ll_ID.setOnClickListener(this);
        ll_IP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_phone:
                Intent phone = new Intent(getActivity(), PhoneActivity.class);
                startActivity(phone);
                break;
            case R.id.ll_sfz:
                Intent ID = new Intent(getActivity(), IDActivity.class);
                startActivity(ID);
                break;
            case R.id.ll_ip:
                Intent ip = new Intent(getActivity(), IPActivity.class);
                startActivity(ip);
                break;
        }
    }
}

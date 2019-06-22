package com.example.lovestou.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.lovestou.R;
import com.example.lovestou.activity.HmstVideoActivity;
import com.example.lovestou.adapter.AlbumAdapter;
import com.example.lovestou.bean.AlbumBean;
import com.itheima.coverflow.ui.FeatureCoverFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends Fragment {
    private View navView;
    private ImageView iv_hmst;

    private FeatureCoverFlow featureCoverFlow;
    private TextSwitcher textSwitcher;
    private List<AlbumBean> albumList = new ArrayList<>();

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
        initCoverFlow();
        initAnimation();
        return navView;
    }

    private void initCoverFlow() {
        AlbumAdapter adapter = new AlbumAdapter(getActivity());
        adapter.setData(albumList);
        featureCoverFlow.setAdapter(adapter);
    }

    private void initData() {
        albumList.add(new AlbumBean(R.drawable.haojiang,"濠江区"));
        albumList.add(new AlbumBean(R.drawable.jinping,"金平区"));
        albumList.add(new AlbumBean(R.drawable.longhu,"龙湖区"));
        albumList.add(new AlbumBean(R.drawable.chenghai,"澄海区"));
        albumList.add(new AlbumBean(R.drawable.chaoyang,"潮阳区"));
        albumList.add(new AlbumBean(R.drawable.chaonan,"潮南区"));
        albumList.add(new AlbumBean(R.drawable.nanao,"南澳县"));
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

        featureCoverFlow = navView.findViewById(R.id.featureCoverFlow);
        textSwitcher = navView.findViewById(R.id.textSwitcher);

        featureCoverFlow.setCoverWidth(450);
        featureCoverFlow.setCoverHeight(400);
        featureCoverFlow.setMaxScaleFactor(1.5f);
        featureCoverFlow.setReflectionGap(0);
        featureCoverFlow.setRotationTreshold(0.5f);
        featureCoverFlow.setScalingThreshold(0.5f);
        featureCoverFlow.setSpacing(0.6f);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                TextView view = (TextView) inflater.inflate(R.layout.item_title,null);
                return view;
            }
        });
        featureCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                String [] name = {"濠江区","金平区","龙湖区","澄海区","潮阳区","潮南区","南澳县"};
                textSwitcher.setText(name[position]);
            }

            @Override
            public void onScrolling() {

            }
        });
    }
    private void initAnimation(){
        Animation in  = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_top);
        Animation out  = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_bottom);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
    }
}

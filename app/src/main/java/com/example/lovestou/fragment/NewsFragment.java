package com.example.lovestou.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lovestou.R;
import com.example.lovestou.adapter.DataAdapter;
import com.example.lovestou.adapter.NewsAdapter;
import com.example.lovestou.bean.DataBean;
import com.example.lovestou.utils.NewsInterface;
import com.example.lovestou.utils.OkhttpUntil;
import com.example.lovestou.utils.TostringNetworkListining;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private View newsView;

    public String look_url = NewsInterface.WEB_SITE + NewsInterface.LOOK_URL;
    public String house_url = NewsInterface.WEB_SITE + NewsInterface.HOUSE_URL;
    public String education_url = NewsInterface.WEB_SITE + NewsInterface.EDU_URL;
    public String travel_url = NewsInterface.WEB_SITE + NewsInterface.TRAVEL_URL;
    public String food_url = NewsInterface.WEB_SITE + NewsInterface.FOOD_URL;
    public String cars_url = NewsInterface.WEB_SITE + NewsInterface.CAR_URL;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private View view_look, view_house, view_education, view_travel, view_food, view_cars;
    private XRecyclerView list_look,list_house,list_education,list_travel,list_food,list_cars;

    private List<DataBean.ItemsBean> dataList = new ArrayList<>();

    public int pageCount = 1;

    private DataAdapter dataAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newsView = inflater.inflate(R.layout.fragment_news, container, false);
        initViewPager();
        return newsView;
    }

    private void initViewPager() {
        tabLayout = newsView.findViewById(R.id.tabLayout);
        viewPager = newsView.findViewById(R.id.news_viewPager);

        mTitleList.add("看点");
        mTitleList.add("房产");
        mTitleList.add("教育");
        mTitleList.add("旅游");
        mTitleList.add("美食");
        mTitleList.add("爱车");

        mInflater = LayoutInflater.from(getActivity());
        initLookView();
        initHouseView();
        initEducationView();
        initTravelView();
        initFoodView();
        initCarsView();

        mViewList.add(view_look);
        mViewList.add(view_house);
        mViewList.add(view_education);
        mViewList.add(view_travel);
        mViewList.add(view_food);
        mViewList.add(view_cars);

        NewsAdapter newsAdapter = new NewsAdapter(mViewList,mTitleList);
        viewPager.setAdapter(newsAdapter);

        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(4)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(5)));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initLookView() {
        GetNews(look_url, pageCount);
        view_look = mInflater.inflate(R.layout.vp_look, null);
        list_look = view_look.findViewById(R.id.list_look);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_look.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(), dataList);
        list_look.setAdapter(dataAdapter);
        list_look.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_look.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_look.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_look.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_look.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initLooknews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_look.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(look_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_look.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    private void initHouseView() {
        GetNews(house_url, pageCount);
        view_house = mInflater.inflate(R.layout.vp_house, null);
        list_house = view_house.findViewById(R.id.list_house);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_house.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(),dataList);
        list_house.setAdapter(dataAdapter);
        list_house.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_house.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_house.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_house.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_house.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initHousenews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_house.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(house_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_house.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    private void initEducationView() {
        GetNews(education_url, pageCount);
        view_education = mInflater.inflate(R.layout.vp_education, null);
        list_education = view_education.findViewById(R.id.list_sport);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_education.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(),dataList);
        list_education.setAdapter(dataAdapter);
        list_education.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_education.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_education.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_education.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_education.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initEducationnews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_education.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(education_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_education.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    private void initTravelView() {
        GetNews(travel_url, pageCount);
        view_travel = mInflater.inflate(R.layout.vp_travel, null);
        list_travel = view_travel.findViewById(R.id.list_travel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_travel.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(),dataList);
        list_travel.setAdapter(dataAdapter);
        list_travel.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_travel.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_travel.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_travel.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_travel.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initTravelnews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_travel.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(travel_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_travel.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    private void initFoodView() {
        GetNews(food_url, pageCount);
        view_food = mInflater.inflate(R.layout.vp_food, null);
        list_food = view_food.findViewById(R.id.list_food);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_food.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(),dataList);
        list_food.setAdapter(dataAdapter);
        list_food.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_food.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_food.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_food.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_food.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initFoodnews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_food.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(food_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_food.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    private void initCarsView() {
        GetNews(cars_url, pageCount);
        view_cars = mInflater.inflate(R.layout.vp_cars,null);
        list_cars = view_cars.findViewById(R.id.list_cars);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_cars.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getActivity(),dataList);
        list_cars.setAdapter(dataAdapter);
        list_cars.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_cars.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_cars.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_cars.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        dataAdapter.addData(dataList);
        // 添加数据
//        lookAdapter.addData(lookList());

        // 添加刷新和加载更多的监听
        list_cars.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                lookAdapter.setData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.refreshComplete();
                initCarsnews();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_cars.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
//                lookAdapter.addData(lookList());
                // 为了看效果，加了一个等待效果，正式的时候直接写mRecyclerView.loadMoreComplete();
                GetNews(cars_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_cars.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    public void GetNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                DataBean dataBean = OkhttpUntil.toObject(DataBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (dataBean != null) {
                    for (int i = 0; i < dataBean.getItems().size(); i++) {
                        if (pageCount == 1) {
                            if (i == 0) {
                                continue;
                            }
                        }
                        dataList.add(dataBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    dataAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });

    }

    public void initLooknews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(look_url, pageCount);
    }
    private void initHousenews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(house_url, pageCount);
    }
    private void initEducationnews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(education_url, pageCount);
    }
    private void initTravelnews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(travel_url, pageCount);
    }
    private void initFoodnews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(food_url, pageCount);
    }
    private void initCarsnews() {
        pageCount = 1;
        dataList.clear();
        dataAdapter.notifyDataSetChanged();
        if (dataList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetNews(cars_url, pageCount);
    }
}

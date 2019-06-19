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
import com.example.lovestou.adapter.CarsAdapter;
import com.example.lovestou.adapter.EducationAdapter;
import com.example.lovestou.adapter.FoodAdapter;
import com.example.lovestou.adapter.HouseAdapter;
import com.example.lovestou.adapter.LookAdapter;
import com.example.lovestou.adapter.NewsAdapter;
import com.example.lovestou.adapter.TravelAdapter;
import com.example.lovestou.bean.CarsBean;
import com.example.lovestou.bean.EducationBean;
import com.example.lovestou.bean.FoodBean;
import com.example.lovestou.bean.HouseBean;
import com.example.lovestou.bean.LookBean;
import com.example.lovestou.bean.TravelBean;
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
    private List<LookBean.ItemsBean> lookList = new ArrayList<>();
    private List<HouseBean.ItemsBean> houseList = new ArrayList<>();
    private List<EducationBean.ItemsBean> educationList = new ArrayList<>();
    private List<TravelBean.ItemsBean> travelList = new ArrayList<>();
    private List<FoodBean.ItemsBean> foodList = new ArrayList<>();
    private List<CarsBean.ItemsBean> carsList = new ArrayList<>();

    public int pageCount = 1;

    private LookAdapter lookAdapter;
    private HouseAdapter houseAdapter;
    private EducationAdapter educationAdapter;
    private TravelAdapter travelAdapter;
    private FoodAdapter foodAdapter;
    private CarsAdapter carsAdapter;

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
        GetLookNews(look_url, pageCount);
        view_look = mInflater.inflate(R.layout.vp_look, null);
        list_look = view_look.findViewById(R.id.list_look);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_look.setLayoutManager(layoutManager);
        lookAdapter = new LookAdapter(getActivity(), lookList);
        list_look.setAdapter(lookAdapter);
        list_look.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_look.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_look.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_look.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        lookAdapter.addData(lookList);
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
                GetLookNews(look_url, pageCount);
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
        GetHouseNews(house_url, pageCount);
        view_house = mInflater.inflate(R.layout.vp_house, null);
        list_house = view_house.findViewById(R.id.list_house);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_house.setLayoutManager(layoutManager);
        houseAdapter = new HouseAdapter(getActivity(),houseList);
        list_house.setAdapter(houseAdapter);
        list_house.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_house.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_house.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_house.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        houseAdapter.addData(houseList);
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
                GetHouseNews(house_url, pageCount);
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
        GetEducationNews(education_url, pageCount);
        view_education = mInflater.inflate(R.layout.vp_education, null);
        list_education = view_education.findViewById(R.id.list_sport);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_education.setLayoutManager(layoutManager);
        educationAdapter = new EducationAdapter(getActivity(),educationList);
        list_education.setAdapter(educationAdapter);
        list_education.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_education.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_education.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_education.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        educationAdapter.addData(educationList);
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
                GetEducationNews(education_url, pageCount);
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
        GetTravelNews(travel_url, pageCount);
        view_travel = mInflater.inflate(R.layout.vp_travel, null);
        list_travel = view_travel.findViewById(R.id.list_travel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_travel.setLayoutManager(layoutManager);
        travelAdapter = new TravelAdapter(getActivity(),travelList);
        list_travel.setAdapter(travelAdapter);
        list_travel.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_travel.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_travel.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_travel.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        travelAdapter.addData(travelList);
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
                GetTravelNews(travel_url, pageCount);
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
        GetFoodNews(food_url, pageCount);
        view_food = mInflater.inflate(R.layout.vp_food, null);
        list_food = view_food.findViewById(R.id.list_food);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_food.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter(getActivity(),foodList);
        list_food.setAdapter(foodAdapter);
        list_food.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_food.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_food.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_food.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        foodAdapter.addData(foodList);
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
                GetFoodNews(food_url, pageCount);
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
        GetCarsNews(cars_url, pageCount);
        view_cars = mInflater.inflate(R.layout.vp_cars,null);
        list_cars = view_cars.findViewById(R.id.list_cars);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_cars.setLayoutManager(layoutManager);
        carsAdapter = new CarsAdapter(getActivity(),carsList);
        list_cars.setAdapter(carsAdapter);
        list_cars.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        // 可以设置是否开启加载更多/下拉刷新
        list_cars.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        list_cars.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        list_cars.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        carsAdapter.addData(carsList);
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
                GetCarsNews(cars_url, pageCount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_cars.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    public void GetLookNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                LookBean lookBean = OkhttpUntil.toObject(LookBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (lookBean != null) {
                    for (int i = 0; i < lookBean.getItems().size(); i++) {
                        if (pageCount == 1) {
                            if (i == 0) {
                                continue;
                            }
                        }
                        lookList.add(lookBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    lookAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });

    }
    public void GetHouseNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                HouseBean houseBean = OkhttpUntil.toObject(HouseBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (houseBean != null) {
                    for (int i = 0; i < houseBean.getItems().size(); i++) {
                        houseList.add(houseBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    houseAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });
    }
    public void GetEducationNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                EducationBean educationBean = OkhttpUntil.toObject(EducationBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (educationBean != null) {
                    for (int i = 0; i < educationBean.getItems().size(); i++) {
                        educationList.add(educationBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    educationAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });
    }
    public void GetTravelNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                TravelBean travelBean = OkhttpUntil.toObject(TravelBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (travelBean != null) {
                    for (int i = 0; i < travelBean.getItems().size(); i++) {
                        travelList.add(travelBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    travelAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });
    }
    public void GetFoodNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                FoodBean foodBean = OkhttpUntil.toObject(FoodBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (foodBean != null) {
                    for (int i = 0; i < foodBean.getItems().size(); i++) {
                        foodList.add(foodBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    foodAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });
    }
    public void GetCarsNews(final String url, int page) {
        OkhttpUntil.enqueueGetrequest(url + page, new TostringNetworkListining() {
            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {
             /*   temp temp = OkhttpUntil.toObject(temp.class, responseString);
                Toast.makeText(getActivity(), temp.getItems().size() + "", Toast.LENGTH_SHORT).show();*/
                CarsBean carsBean = OkhttpUntil.toObject(CarsBean.class, responseString);
                // Toast.makeText(activity, lookBean.getItems().size()+"", Toast.LENGTH_SHORT).show();
                if (carsBean != null) {
                    for (int i = 0; i < carsBean.getItems().size(); i++) {
                        carsList.add(carsBean.getItems().get(i));
                    }
                    //   Toast.makeText(activity, lookList.size()+"", Toast.LENGTH_SHORT).show();
                    carsAdapter.notifyDataSetChanged();
                    pageCount += 1;
                }
            }
        });
    }

    public void initLooknews() {
        pageCount = 1;
        lookList.clear();
        lookAdapter.notifyDataSetChanged();
        if (lookList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetLookNews(look_url, pageCount);
    }
    private void initHousenews() {
        pageCount = 1;
        houseList.clear();
        lookAdapter.notifyDataSetChanged();
        if (houseList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetHouseNews(house_url, pageCount);
    }
    private void initEducationnews() {
        pageCount = 1;
        educationList.clear();
        educationAdapter.notifyDataSetChanged();
        if (educationList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetEducationNews(education_url, pageCount);
    }
    private void initTravelnews() {
        pageCount = 1;
        travelList.clear();
        travelAdapter.notifyDataSetChanged();
        if (travelList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetTravelNews(travel_url, pageCount);
    }
    private void initFoodnews() {
        pageCount = 1;
        foodList.clear();
        foodAdapter.notifyDataSetChanged();
        if (foodList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetFoodNews(food_url, pageCount);
    }
    private void initCarsnews() {
        pageCount = 1;
        carsList.clear();
        carsAdapter.notifyDataSetChanged();
        if (carsList.size() < 0 ) {
            Toast.makeText(getActivity(),"服务器未响应",Toast.LENGTH_SHORT).show();
        }
        GetCarsNews(cars_url, pageCount);
    }
}

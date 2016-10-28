package com.shmilysyp.kaishen.weatherapp.activity.main;

import android.os.Bundle;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.shmilysyp.kaishen.weatherapp.BaseResponse;
import com.shmilysyp.kaishen.weatherapp.LoaderUtil;
import com.shmilysyp.kaishen.weatherapp.LogUtil;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.activity.base.AbsActivity;
import com.shmilysyp.kaishen.weatherapp.adapter.WeatherListAdapter;
import com.shmilysyp.kaishen.weatherapp.entity.RecentWeathersEntity;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AbsActivity {

    @Bind(R.id.info_list)
    EasyRecyclerView mInfoListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private WeatherListAdapter mAdapter;

    @Override
    protected void afterCreate(Bundle saveInstanceState) {
        super.afterCreate(saveInstanceState);
        initView();
        reloadData();
        initEvent();
    }

    private void initEvent() {

    }

    @Override
    protected void reloadData() {
        Subscription subscription = LoaderUtil.getLoaderService()
                .getRecentWeathers("杭州", "101210101")
                .compose(LoaderUtil.<BaseResponse<RecentWeathersEntity>>onNext())
                .subscribe(new Observer<BaseResponse<RecentWeathersEntity>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("===", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("===", "error");
                    }

                    @Override
                    public void onNext(BaseResponse<RecentWeathersEntity> recentWeathersEntityBaseResponse) {
                        if (recentWeathersEntityBaseResponse.retData.forecastWeather != null &&
                                recentWeathersEntityBaseResponse.retData.forecastWeather.size() != 0) {
                            mInfoListView.showRecycler();
                            mAdapter.clear();
                            mAdapter.addAll(recentWeathersEntityBaseResponse.retData.historyWeather);
                            mAdapter.addAll(recentWeathersEntityBaseResponse.retData.forecastWeather);
                            for (int i = 0; i < recentWeathersEntityBaseResponse.retData.forecastWeather.size(); i++) {
                                LogUtil.e("forecastDate", recentWeathersEntityBaseResponse.retData.forecastWeather.get(i).date);
                            }
                        }

                    }
                });
    }

    private void initView() {
        mInfoListView.setLayoutManager(getLinearLayoutManager());
//        mInfoListView.setRefreshListener(this);
        mAdapter = new WeatherListAdapter(this, true);
        mInfoListView.setAdapter(mAdapter);
    }
}

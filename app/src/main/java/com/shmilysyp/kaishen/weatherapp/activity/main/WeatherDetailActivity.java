package com.shmilysyp.kaishen.weatherapp.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.shmilysyp.kaishen.weatherapp.BaseResponse;
import com.shmilysyp.kaishen.weatherapp.LoaderUtil;
import com.shmilysyp.kaishen.weatherapp.LogUtil;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.activity.base.AbsActivity;
import com.shmilysyp.kaishen.weatherapp.adapter.WeatherListAdapter;
import com.shmilysyp.kaishen.weatherapp.entity.CityEntity;
import com.shmilysyp.kaishen.weatherapp.entity.RecentWeathersEntity;
import com.shmilysyp.kaishen.weatherapp.enums.WeatherEnum;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;

/**
 * Created by kaishen on 16/10/27.
 */

public class WeatherDetailActivity extends AbsActivity {

    @Bind(R.id.toolbar)
    View mToolbar;

    @Bind(R.id.tv_city_name)
    TextView mCityNameTxt;
    @Bind(R.id.iv_weather_type)
    ImageView mWeatherTypeIv;
    @Bind(R.id.tv_weather_type)
    TextView mWeatherTypeTxt;
    @Bind(R.id.tv_current_temp_value)
    TextView mCurrentTempTxt;
    @Bind(R.id.tv_max_temp)
    TextView mMaxTempTxt;
    @Bind(R.id.tv_min_temp)
    TextView mMinTempTxt;
    @Bind(R.id.tv_humidity_value)
    TextView mHumidityTxt;
    @Bind(R.id.tv_precip_value)
    TextView mPrecipTxt;

    @Bind(R.id.rv_recycle)
    EasyRecyclerView mRecyclerView;

    @OnClick(R.id.btn_setting)
    void setClick() {

    }

    private WeatherListAdapter mAdapter;
    private CityEntity mCityInfo = new CityEntity();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_weather_all;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {
        super.afterCreate(saveInstanceState);
        initView();
        reloadData();
        initEvent();

    }

    private void initView() {
        mToolbar.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mAdapter = new WeatherListAdapter(this, false);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void reloadData() {
        super.reloadData();
        Subscription sb = LoaderUtil.getLoaderService()
                .getCityInfo("杭州")
                .compose(LoaderUtil.<BaseResponse<CityEntity>>onNext())
                .subscribe(new Observer<BaseResponse<CityEntity>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("====!!", "success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("===!!", "error");
                    }

                    @Override
                    public void onNext(BaseResponse<CityEntity> cityEntityBaseResponse) {
                        mCityInfo = cityEntityBaseResponse.retData;
                        LogUtil.e("city", mCityInfo.cityCode);
                        getWeather();
                    }
                });
    }

    private void getWeather() {

        Subscription subscription = LoaderUtil.getLoaderService()
                .getRecentWeathers(mCityInfo.cityName, mCityInfo.cityCode)
                .compose(LoaderUtil.<BaseResponse<RecentWeathersEntity>>onNext())
                .subscribe(new Observer<BaseResponse<RecentWeathersEntity>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("===2", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("===2", "error");
                    }

                    @Override
                    public void onNext(BaseResponse<RecentWeathersEntity> recentWeathersEntityBaseResponse) {
                        if (recentWeathersEntityBaseResponse.retData.forecastWeather != null &&
                                recentWeathersEntityBaseResponse.retData.forecastWeather.size() != 0) {
                            fillData(recentWeathersEntityBaseResponse.retData);
                        }

                    }
                });
    }

    private void fillData(RecentWeathersEntity retData) {
        mCityNameTxt.setText(retData.city);
        mWeatherTypeTxt.setText(retData.todayWeather.type);
        mWeatherTypeIv.setBackgroundResource(WeatherEnum.stringValueOf(retData.todayWeather.type).getIconId());
        mCurrentTempTxt.setText(retData.todayWeather.curTemp.replace("℃", "°"));
        mMaxTempTxt.setText(retData.todayWeather.hightemp.replace("℃", "°"));
        mMinTempTxt.setText(retData.todayWeather.lowtemp.replace("℃", "°"));
        mHumidityTxt.setText("80");
        mPrecipTxt.setText("60");

        mRecyclerView.showRecycler();
        mAdapter.clear();
        mAdapter.addAll(retData.historyWeather);
        mAdapter.addAll(retData.forecastWeather);
    }

    private void initEvent() {
    }
}

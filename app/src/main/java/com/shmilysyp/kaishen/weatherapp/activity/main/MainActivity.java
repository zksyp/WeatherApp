package com.shmilysyp.kaishen.weatherapp.activity.main;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.shmilysyp.kaishen.weatherapp.entity.BaseResponse;
import com.shmilysyp.kaishen.weatherapp.http.LoaderUtil;
import com.shmilysyp.kaishen.weatherapp.utils.LogUtil;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.activity.base.AbsActivity;
import com.shmilysyp.kaishen.weatherapp.adapter.WeatherListAdapter;
import com.shmilysyp.kaishen.weatherapp.entity.RecentWeathersEntity;

import butterknife.Bind;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AbsActivity implements EasyRecyclerView.OnClickListener{

    @Bind(R.id.root_view)
    View mRootView;
    @Bind(R.id.detail_container)
    View mDetailContainer;

    @Bind(R.id.info_list)
    EasyRecyclerView mInfoListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private WeatherListAdapter mAdapter;
    private GestureDetectorCompat mDetector;
    private Point p = new Point();
    private boolean isShow = false;

    @Override
    protected void afterCreate(Bundle saveInstanceState) {
        super.afterCreate(saveInstanceState);
        initView();
        reloadData();
        initEvent();
    }

    private void initEvent() {
        setToolLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShow){
                    mDetailContainer.setVisibility(View.GONE);
                    mRootView.setLeft(p.x / 2);
                    isShow = false;
                }else{
                    mDetailContainer.setVisibility(View.VISIBLE);
                    mRootView.setLeft(0);
                    isShow = true;
                }
            }
        });
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
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
        WindowManager windowManager = this.getWindowManager();
        windowManager.getDefaultDisplay().getSize(p);

        mDetailContainer.setVisibility(View.GONE);
        mRootView.setLeft(p.x / 2);

        mInfoListView.setLayoutManager(getLinearLayoutManager());
//        mInfoListView.setRefreshListener(this);
        mAdapter = new WeatherListAdapter(this, true);
        mInfoListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY){
                mDetailContainer.setVisibility(View.VISIBLE);
                Point p = new Point();
                WindowManager windowManager = MainActivity.this.getWindowManager();
                windowManager.getDefaultDisplay().getSize(p);
                mRootView.setLeft(0);
                return true;
            }
            return true;
        }
    }
}

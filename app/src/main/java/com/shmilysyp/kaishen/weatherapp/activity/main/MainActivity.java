package com.shmilysyp.kaishen.weatherapp.activity.main;

import android.os.Bundle;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.activity.base.AbsActivity;

import butterknife.Bind;

public class MainActivity extends AbsActivity {

    @Bind(R.id.info_list)
    EasyRecyclerView mInfoListView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {
        super.afterCreate(saveInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {

    }

    private void initView() {

    }
}

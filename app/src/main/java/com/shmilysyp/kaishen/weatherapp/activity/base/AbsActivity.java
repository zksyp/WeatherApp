package com.shmilysyp.kaishen.weatherapp.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shmilysyp.kaishen.weatherapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kaishen on 16/10/25.
 */

public abstract class AbsActivity extends HandlerProviderActivity {

    @Bind(R.id.progress_view)
    protected ViewGroup mProgressView;
    @Bind(R.id.empty_view)
    protected ViewGroup mEmptyView;
    @Bind(R.id.error_view)
    protected ViewGroup mErrorView;

    @Bind(R.id.error_load_note)
    protected TextView mReloadNote;

    protected ViewGroup mMainView;

    int mSearchRequestCode = -1;

    @OnClick(R.id.reload)
    protected void reloadData() {

    }

    /**
     * 布局文件xml的resId,无需添加标题栏、加载、错误及空页面
     */
    protected abstract int getContentViewId();

    /**
     * 隐藏除标题栏的所有界面,辅助显示各个页面的
     */
    private void hideAll() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mMainView != null) {
            mMainView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        if(getContentViewId() != 0){
            mMainView = (ViewGroup) getLayoutInflater().inflate(getContentViewId(), null);
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content_view);
            viewGroup.addView(mMainView, 2);
        }
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);

    }

    protected void afterCreate(Bundle saveInstanceState){

    }

    /**
     * 获取一个recycleView使用的竖直排列LinearLayoutManager
     */
    protected LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    /**
     * 获取一个recycleView使用的竖直排列GridLayoutManager
     */
    protected GridLayoutManager getGridLayoutManager(int spanCount) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }
}

package com.shmilysyp.kaishen.weatherapp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.entity.WeatherEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kaishen on 16/10/25.
 * 最近七天天气列表
 */

public class WeatherListAdapter extends RecyclerArrayAdapter<WeatherEntity> {


    public WeatherListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_weather_list);
    }

    private static class ViewHolder extends BaseViewHolder<WeatherEntity> {

        @Bind(R.id.tv_week)
        TextView mWeekTxt;
        @Bind(R.id.iv_weather)
        ImageView mWeatherIv;
        @Bind(R.id.tv_max_temp)
        TextView mMaxTempTxt;
        @Bind(R.id.tv_min_temp)
        TextView mMinTempTxt;
        @Bind(R.id.tv_wind_strong)
        TextView mWindStrongTxt;
        @Bind(R.id.tv_value_pm)
        TextView mPmValueTxt;


        ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(WeatherEntity data) {
            super.setData(data);

        }
    }
}

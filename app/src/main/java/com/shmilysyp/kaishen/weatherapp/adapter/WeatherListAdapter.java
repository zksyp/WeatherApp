package com.shmilysyp.kaishen.weatherapp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.shmilysyp.kaishen.weatherapp.LogUtil;
import com.shmilysyp.kaishen.weatherapp.R;
import com.shmilysyp.kaishen.weatherapp.entity.WeatherEntity;
import com.shmilysyp.kaishen.weatherapp.enums.WeatherEnum;
import com.shmilysyp.kaishen.weatherapp.enums.WeekEnum;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kaishen on 16/10/25.
 * 最近七天天气列表
 */

public class WeatherListAdapter extends RecyclerArrayAdapter<WeatherEntity> {


    private boolean isNeedDetail = true;

    public WeatherListAdapter(Context context, boolean isNeedDetail) {
        super(context);
        this.isNeedDetail = isNeedDetail;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_weather_list, isNeedDetail);
    }

    static class ViewHolder extends BaseViewHolder<WeatherEntity> {

        @Bind(R.id.tv_week)
        TextView mWeekTxt;
        @Bind(R.id.iv_weather_type)
        ImageView mWeatherIv;
        @Bind(R.id.tv_max_temp)
        TextView mMaxTempTxt;
        @Bind(R.id.tv_min_temp)
        TextView mMinTempTxt;
        @Bind(R.id.tv_wind_strong)
        TextView mWindStrongTxt;
        @Bind(R.id.tv_value_pm)
        TextView mPmValueTxt;
        @Bind(R.id.detail_right_container)
        View mRightContainer;


        ViewHolder(ViewGroup parent, @LayoutRes int res, boolean isNeedDetail) {
            super(parent, res);
            ButterKnife.bind(this, itemView);
            this.isNeedDetail = isNeedDetail;
        }

        private boolean isNeedDetail = true;

        @Override
        public void setData(WeatherEntity data) {
            super.setData(data);
            LogUtil.e("date", data.date);
            LogUtil.e("temp", data.hightemp);
            mRightContainer.setVisibility(View.GONE);
            mWeekTxt.setText(WeekEnum.stringValueOf(data.week).getAbbr());
            mWeatherIv.setBackgroundResource(WeatherEnum.stringValueOf(data.type).getIconId());
            mMaxTempTxt.setText(data.hightemp.substring(0, data.hightemp.length() - 2));
            if (isNeedDetail) {
                mRightContainer.setVisibility(View.VISIBLE);
                mMinTempTxt.setText(data.lowtemp.replace("℃", "°"));
                mWindStrongTxt.setText(data.fengli.substring(0, data.fengli.length() - 1));
                mPmValueTxt.setText(data.aqi);
            }

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), WeatherDetailActivity.class);
//                    getContext().startActivity(intent);
//                }
//            });
        }
    }
}

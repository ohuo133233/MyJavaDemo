package com.example.chat.base.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {
    private static final String TAG = "BaseRecyclerViewAdapter";
    @NonNull
    private final Context mContext;
    @LayoutRes
    private final int mLayoutId;
    @NonNull
    private ArrayList<T> mDataList;

    private RecyclerView.ViewHolder mHolder;
    private CommonRecyclerViewAdapterBackCall mCommonRecyclerViewAdapterBackCall;

    private IMutableItemView mIMutableItemView;
    private final Build<T> mBuild;

    private CommonRecyclerViewAdapter(@NonNull Build<T> build) {
        Log.e(TAG, "CommonRecyclerViewAdapter");
        mBuild = build;
        this.mContext = mBuild.mContext;
        this.mLayoutId = mBuild.mLayoutId;
        this.mDataList = mBuild.mDataList;

        if (mBuild.mIMutableItemView != null) {
            Log.e(TAG, "mBuild.mIMutableItemView != null");
            this.mIMutableItemView = mBuild.mIMutableItemView;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mIMutableItemView == null) {
            return super.getItemViewType(position);
        }
        return mIMutableItemView.getItemViewType(position);
    }

    @NonNull
    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        Log.e(TAG, "onCreateViewHolder");
        if (mIMutableItemView != null) {
            int layoutId = mIMutableItemView.onCreateViewHolder(parent, viewType);
            layout = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        } else{
            layout = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        }
        return new CommonRecyclerViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position) {
        mHolder = holder;
        if (mCommonRecyclerViewAdapterBackCall != null) {
            mCommonRecyclerViewAdapterBackCall.onBindViewHolder(holder, position);
        }
    }

    public void setDataList(@NonNull ArrayList<T> dataList) {
        this.mDataList = dataList;
        Log.e(TAG, "mDataList: " + mDataList);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "mDataList size: " + mDataList.size());
        return Math.max(mDataList.size(), 0);
    }


    public void setCommonRecyclerViewAdapterBackCall(CommonRecyclerViewAdapterBackCall mCommonRecyclerViewAdapterBackCall) {
        this.mCommonRecyclerViewAdapterBackCall = mCommonRecyclerViewAdapterBackCall;
    }


    public static class Build<T> {
        @NonNull
        private Context mContext;
        @LayoutRes
        private int mLayoutId;
        @NonNull
        private ArrayList<T> mDataList;

        private IMutableItemView mIMutableItemView;

        public Build<T> setContext(@NonNull Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public Build<T> setLayoutId(@LayoutRes int mLayoutId) {
            this.mLayoutId = mLayoutId;
            return this;
        }

        public Build<T> setDataList(@NonNull ArrayList<T> mDataList) {
            this.mDataList = mDataList;
            return this;
        }

        public Build<T> setMutableItemView(IMutableItemView iMutableItemView) {
            this.mIMutableItemView = iMutableItemView;
            return this;
        }

        public CommonRecyclerViewAdapter<T> build() {
            return new CommonRecyclerViewAdapter<>(this);
        }
    }
}
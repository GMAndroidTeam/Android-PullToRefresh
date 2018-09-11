/*******************************************************************************
 * Copyright 2014 Dean Ding.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.R;

/**
 * Support RecyclerView
 *
 * @author Dean.Ding
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    private boolean isAutoLoadMore = false;

    private boolean hasHeaderView = false;

    private boolean hasFooterView = false;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    /**
     * 设置自动加载更多
     */
    public void setAutoLoadMore() {
        isAutoLoadMore = true;
    }

    /**
     * 是否有headerview
     */
    public void HasHeaderView(){
        hasHeaderView = true;
    }

    /**
     * 是否有footerview
     */
    public void HasFooterView(){
        hasFooterView = true;
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView;
        recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recyclerview);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isAutoLoadMore) {
                    return;
                }
                if (isReadyLoadMore()) {
                    setCurrentMode(Mode.PULL_FROM_END);
                    setState(State.REFRESHING, true);
                }
            }
        });
        return recyclerView;
    }

    @Override
    public boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() <= 0)
            return true;
        int firstVisiblePosition = mRefreshableView.getChildPosition(mRefreshableView.getChildAt(0));
        if (firstVisiblePosition == 0)
            return mRefreshableView.getChildAt(0).getTop() == mRefreshableView.getPaddingTop();
        else
            return false;

    }

    @Override
    public boolean isReadyForPullEnd() {
        int lastVisiblePosition = mRefreshableView.getChildPosition(mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1));
        if (isAutoLoadMore && lastVisiblePosition < mRefreshableView.getAdapter().getItemCount() - 1) {
            return false;
        } else {
            return isReadyLoadMore();
        }
    }

    public boolean isReadyLoadMore() {
        if (null == mRefreshableView || null == mRefreshableView.getAdapter()) {
            return false;
        }
        int firstVisiblePosition = mRefreshableView.getChildPosition(mRefreshableView.getChildAt(0));
        int lastVisiblePosition = mRefreshableView.getChildPosition(mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1));
        int visibleItemCount = lastVisiblePosition - firstVisiblePosition;
        int headerViewCount = hasHeaderView ? 1 : 0;
        int footerViewCount = hasFooterView ? 1 : 0;
        if (visibleItemCount == 0 || visibleItemCount == mRefreshableView.getAdapter().getItemCount() - headerViewCount - footerViewCount){
            //判断是否一屏显示完，如果一屏显示完成就不自动加载更多了
            return false;
        }
        if (lastVisiblePosition >= mRefreshableView.getAdapter().getItemCount() - 1) {
            return mRefreshableView.getChildAt(mRefreshableView.getChildCount() - 1).getBottom() <= mRefreshableView.getBottom();
        }
        return false;
    }

}
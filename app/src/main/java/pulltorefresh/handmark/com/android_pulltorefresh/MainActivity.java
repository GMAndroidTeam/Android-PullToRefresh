package pulltorefresh.handmark.com.android_pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2016-12-13 15:42
 * <p> Version: xx
 * <p> Description: xx
 * <p>
 * <p>***********************************************************************
 */

public class MainActivity extends Activity implements PullToRefreshBase.OnRefreshListener2{

    private PullToRefreshRecyclerView listView;
    private List<String> mBeans = new ArrayList<>();
    private MainRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = (PullToRefreshRecyclerView) findViewById(R.id.refresh_list_view);
//        listView.setAdapter(new MainAdapter(MainActivity.this));
        for (int i = 0; i < 10; i++){
            mBeans.add("初始数据" + i);
        }
        mRecyclerAdapter = new MainRecyclerAdapter(this, mBeans);
        listView.getRefreshableView().setAdapter(mRecyclerAdapter);
        listView.getRefreshableView().setLayoutManager(new LinearLayoutManager(this));
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        listView.setAutoLoadMore();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        for (int i = 0; i < 10; i++) {
            mBeans.add("加载更多" + i);
        }
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerAdapter.notifyDataSetChanged();
                listView.onRefreshComplete();
            }
        }, 3000);

    }
}

package pulltorefresh.handmark.com.android_pulltorefresh;

import android.app.Activity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2016-12-13 15:42
 * <p> Version: xx
 * <p> Description: xx
 * <p>
 * <p>***********************************************************************
 */

public class MainActivity extends Activity {

    private PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.refresh_list_view);
        listView.setAdapter(new MainAdapter(MainActivity.this));
    }
}

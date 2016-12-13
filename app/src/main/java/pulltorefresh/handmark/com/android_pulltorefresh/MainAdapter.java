package pulltorefresh.handmark.com.android_pulltorefresh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2016-12-13 15:54
 * <p> Version: xx
 * <p> Description: xx
 * <p>
 * <p>***********************************************************************
 */

public class MainAdapter extends BaseAdapter {

    private Context context;

    public MainAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.list_item_test, null);
        HorizontalListView listView = (HorizontalListView) view.findViewById(R.id.horizontal_list_view);
        HorizontalAdapter adapter = new HorizontalAdapter();
        listView.setAdapter(adapter);
        return view;
    }
}

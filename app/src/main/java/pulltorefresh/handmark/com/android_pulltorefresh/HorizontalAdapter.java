package pulltorefresh.handmark.com.android_pulltorefresh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * <p>***********************************************************************
 * <p> Author: Michael
 * <p> CreateData: 2016-12-13 16:15
 * <p> Version: xx
 * <p> Description: xx
 * <p>
 * <p>***********************************************************************
 */

public class HorizontalAdapter extends BaseAdapter{

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
        View view = View.inflate(parent.getContext(), R.layout.horizontal_list_item, null);
        return view;
    }
}

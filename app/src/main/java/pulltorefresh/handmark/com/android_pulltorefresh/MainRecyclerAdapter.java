package pulltorefresh.handmark.com.android_pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangjinlong on 2018/9/4.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<String> mBeans;
    public MainRecyclerAdapter(Context context, List<String> beans) {
        super();
        mBeans = beans;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainRecyclerViewHolder(View.inflate(mContext, R.layout.list_item_recycler_test, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainRecyclerViewHolder viewHolder = (MainRecyclerViewHolder)holder;
        viewHolder.tvItem.setText(mBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public class MainRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem = (TextView) itemView.findViewById(R.id.tv_recycler);

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}

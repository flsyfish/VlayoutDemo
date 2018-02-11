package android.client.zxing.google.com.vlayoutdemo.adapter;

import android.client.zxing.google.com.vlayoutdemo.R;
import android.client.zxing.google.com.vlayoutdemo.bean.BanerBean;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class ImageAdapter extends RecyclablePagerAdapter<MainViewHolder> {
    private RecyclerView.RecycledViewPool pool;
    private SubImgsAdapter adapter;
    private List<BanerBean> itemData;
    private Context mContext;


    public ImageAdapter(Context mContext,SubImgsAdapter adapter, RecyclerView.RecycledViewPool pool,List<BanerBean> itemData) {
        super(adapter, pool);
        this.pool=pool;
        this.adapter=adapter;
        this.itemData=itemData;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        // only vertical
        viewHolder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Glide.with(mContext).load(itemData.get(position).getImageUrl()).into((ImageView) viewHolder.itemView.findViewById(R.id.imgs));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}

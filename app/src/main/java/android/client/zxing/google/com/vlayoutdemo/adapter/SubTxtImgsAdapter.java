package android.client.zxing.google.com.vlayoutdemo.adapter;

import android.client.zxing.google.com.vlayoutdemo.R;
import android.client.zxing.google.com.vlayoutdemo.bean.BanerBean;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class SubTxtImgsAdapter extends DelegateAdapter.Adapter<MainViewHolder>{


    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private List<BanerBean> itemData;


    public SubTxtImgsAdapter(Context context, LayoutHelper layoutHelper, List<BanerBean> itemData,int imgsType) {
        this(context, layoutHelper, itemData, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,imgsType));
    }

    public SubTxtImgsAdapter(Context context, LayoutHelper layoutHelper, List<BanerBean> itemData, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.itemData = itemData;
        this.mLayoutParams = layoutParams;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_imgs_small, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        // only vertical
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));
    }


    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
        Glide.with(mContext).load(itemData.get(position).getImageUrl())
                .skipMemoryCache(true)
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into((ImageView) holder.itemView.findViewById(R.id.imgs));
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }
}

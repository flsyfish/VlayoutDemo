package android.client.zxing.google.com.vlayoutdemo;

import android.client.zxing.google.com.vlayoutdemo.adapter.ImageAdapter;
import android.client.zxing.google.com.vlayoutdemo.adapter.MainViewHolder;
import android.client.zxing.google.com.vlayoutdemo.adapter.SubImgsAdapter;
import android.client.zxing.google.com.vlayoutdemo.adapter.SubTxtImgsAdapter;
import android.client.zxing.google.com.vlayoutdemo.bean.BanerBean;
import android.client.zxing.google.com.vlayoutdemo.utils.JsonUtil;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class TextActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<HomeBean.DatasBean.ItemListBean> itemList;
    private List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
    private Context mContext;
    private RecyclerView.RecycledViewPool viewPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        initView();
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);


        Gson mGson = new Gson();
        HomeBean mHomeBean = mGson.fromJson(Constans.mJson, HomeBean.class);

        itemList = mHomeBean.getDatas().getItemList();


        recyclerView.setLayoutManager(layoutManager);


        viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);


        for (HomeBean.DatasBean.ItemListBean itemListBean : itemList) {
            if (!TextUtils.isEmpty(itemListBean.getItemData())) {
                List<BanerBean> itemData = JsonUtil.toBean(itemListBean.getItemData(), new TypeToken<List<BanerBean>>() {
                }.getType());
                if (itemData.size() > 0 && itemData != null) {
                    if ("ad".equals(itemListBean.getItemType())) {
                        BannerView(itemData);
                    } else if ("home8".equals(itemListBean.getItemType()) || "home3".equals(itemListBean.getItemType()) || "home6".equals(itemListBean.getItemType())) {
                        BannerGridView(itemData, "home8".equals(itemListBean.getItemType()) ? 5 : ("home3".equals(itemListBean.getItemType()) ? 2 : 4));
                    } else if ("home1".equals(itemListBean.getItemType()) || "home7".equals(itemListBean.getItemType())
                            || "home11".equals(itemListBean.getItemType())) {
                        BannerBigView(itemData, itemListBean.getItemType(), "home5".equals(itemListBean.getItemType()) ? 3 : 0);
                    } else if ("home2".equals(itemListBean.getItemType()) || "home9".equals(itemListBean.getItemType())) {
                        BannerLeft1Right2View(itemData);
                    } else if ("home10".equals(itemListBean.getItemType()) || "home5".equals(itemListBean.getItemType())) {
                        BannerGridView(itemData, "home5".equals(itemListBean.getItemType()) ? 3 : 4);
                    } else if ("home4".equals(itemListBean.getItemType())) {
                        BannerLeft2Right1View(itemData);
                    }
                }
            }

        }


        delegateAdapter.setAdapters(adapters);


    }

    //左2右1
    private void BannerLeft2Right1View(List<BanerBean> itemData) {

        BannerLeft1Right2View(itemData);
    }

    //左1右2
    private void BannerLeft1Right2View(List<BanerBean> itemData) {
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper(3);
        helper.setBgColor(0xff876384);
        adapters.add(new SubTxtImgsAdapter(this, helper, itemData, JsonUtil.dip2px(mContext,90f)));
    }

    //单列单张大图模块
    private void BannerBigView(List<BanerBean> itemData, String itemType, int count) {
        LinearLayoutHelper layoutHelper;
        layoutHelper = new LinearLayoutHelper(count);
        adapters.add(new SubTxtImgsAdapter(this, layoutHelper, itemData, "home7".equals(itemType) ? JsonUtil.dip2px(mContext,45f) : JsonUtil.dip2px(mContext,140f)));
    }

    //N列单行小图模块
    private void BannerGridView(List<BanerBean> itemData, int count) {
        GridLayoutHelper layoutHelper;
        layoutHelper = new GridLayoutHelper(count);
        if (count == 5) {
            layoutHelper.setPadding(2, 2, 2, 2);
        }
        layoutHelper.setBgColor(0xffffffff);
        adapters.add(new SubTxtImgsAdapter(this, layoutHelper, itemData, (count == 5) ? JsonUtil.dip2px(mContext,55f) : JsonUtil.dip2px(mContext,140f)));
    }


    //轮播图
    private void BannerView(final List<BanerBean> itemData) {
        adapters.add(new SubImgsAdapter(this, new LinearLayoutHelper(), 1) {

            @Override
            public void onViewRecycled(MainViewHolder holder) {
                if (holder.itemView instanceof ViewPager) {
                    ((ViewPager) holder.itemView).setAdapter(null);
                }
            }

            @Override
            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1)
                    return new MainViewHolder(
                            LayoutInflater.from(mContext).inflate(R.layout.view_pager, parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                if (holder.itemView instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) holder.itemView;

                    viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, JsonUtil.dip2px(mContext,140f)));

                    // from position to get adapter
                    viewPager.setAdapter(new ImageAdapter(mContext, this, viewPool, itemData));
                }
            }
        });


    }
}

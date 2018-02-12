# VlayoutDemo
Vlayout测试的demo

项目中用到大量的图片，快速滑动造成图片混乱
#####  ImageView设置tag
 
 ```bash
((ImageView)holder.itemView.findViewById(R.id.imgs)).setTag(R.id.imgs,itemData.get(position).getImageUrl());
  ```
 ```bash
 if((itemData.get(position).getImageUrl()).equals(((ImageView) holder.itemView.findViewById(R.id.imgs)).getTag(R.id.imgs).toString())) {
            Glide.with(mContext).load(itemData.get(position).ge  tImageUrl())
                .skipMemoryCache(true)
                    .placeholder(R.drawable.no_banner)//重点  占位符
                    .error(R.drawable.no_banner)
                    .diskCacheStrategy( DiskCacheStrategy.ALL )
                    .into((ImageView)             holder.itemView.findViewById(R.id.imgs));
        }
 ```


##### 滑动静止的时候Glide恢复请求
 ```bash
  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING 
                || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(mContext).pauseRequests();
                } else if(newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling == true) {
                        Glide.with(mContext).resumeRequests();

                    }
                    sIsScrolling = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
```

##### 效果图

![3.png](C:/Users/Administrator/Desktop/3.png "")

package android.client.zxing.google.com.vlayoutdemo.bean;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/11.
 */

public class BanerBean implements Serializable {
    private String imageUrl;
    private String type;  //操作类型
    private String data;  //操作数据

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



}

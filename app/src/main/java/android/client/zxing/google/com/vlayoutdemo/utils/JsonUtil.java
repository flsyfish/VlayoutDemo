package android.client.zxing.google.com.vlayoutdemo.utils;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by qyf on 2016/3/1.
 */
public class JsonUtil {
    public static final Gson gson = new Gson();

    public static <T> T toBean(String json, String urg1, String urg2, Type type) {
        try {
            JSONObject object = new JSONObject(json);
            String s1 = object.getString(urg1);
            JSONObject obj = new JSONObject(s1);
            String s2 = obj.getString(urg2);
            return gson.fromJson(s2, type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T toBean(String json, String urg1, Type type) {
        try {
            JSONObject object = new JSONObject(json);
            String s1 = object.optString(urg1);
            return gson.fromJson(s1, type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <T> T toBean(String json, Type type) {
        return gson.fromJson(json, type);
    }


    public static Integer toInteger(String json, String urg1) {
        try {
            JSONObject object = new JSONObject(json);
            return Integer.valueOf(object.optString(urg1));
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String toString(String json, String urg1) {
        try {
            JSONObject object = new JSONObject(json);
            return object.optString(urg1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String toString(String json, String urg1, String urg2) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject obj1 = new JSONObject(object.optString(urg1));
            return obj1.optString(urg2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getError(String json) {
        String error = null;
        try {
            JSONObject object = new JSONObject(json);
            error = object.optString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }





    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }




}

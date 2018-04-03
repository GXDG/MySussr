package com.example.mysussrx.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

import com.example.mysussrx.App;


/**
 * Created by hzg on 2018/1/2 11:04
 * Utils 辅助类,如果其它工具类需要额外的数据支持,
 * 比如尺寸转换需要Context信息,可以使用 getApp()方法代替,避免工具类与App直接关联
 * 需要移植到其它项目时只要修改该类即可
 */

public class Utils {
    public static Context getApp() {
        return App.getInstance();
    }

    public static void requestFocus(View view) {
        view.setFocusableInTouchMode(true);
        view.setFocusable(true);
        view.requestFocus();
    }

    /**
     * Drawable 颜色转化类
     *
     * @param drawable
     * @param color    资源
     * @return 改变颜色后的Drawable
     */
    public static Drawable tintDrawable(@NonNull Drawable drawable, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }
}

package com.example.mysussrx.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * Created by DELL on 2017年6月21日 021.
 * Toast 工具类
 */

public class ToastUtil {

    private static Toast sToast = null;

    //toast需要在主线程show
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void toastS(@NonNull String info) {
        showToast(info, Toast.LENGTH_SHORT);
    }

    public static void toastS(@StringRes int info) {
        showToast(info, Toast.LENGTH_SHORT);
    }

    public static void toastL(@NonNull String info) {
        showToast(info, Toast.LENGTH_LONG);
    }

    public static void toastL(@StringRes int info) {
        showToast(info, Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    private static void showToast(String info, int duration) {
        HANDLER.post(() -> {
            if (sToast != null) {
                sToast.setText(info);
                sToast.setDuration(duration);
            } else {
                sToast = Toast.makeText(Utils.getApp(), info, duration);
            }
            sToast.show();
        });
    }

    @SuppressLint("ShowToast")
    private static void showToast(int info, int duration) {
        HANDLER.post(() -> {
            if (sToast != null) {
                sToast.setText(info);
                sToast.setDuration(duration);
            } else {
                sToast = Toast.makeText(Utils.getApp(), info, duration);
            }
            sToast.show();
        });
    }

}

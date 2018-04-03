package com.example.mysussrx;

import android.app.Application;
import android.content.Context;

import com.example.mysussrx.config.AppConfig;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

/**
 * Created by hzg on
 */

public class App extends Application {
    private static App sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Bugly.init(this, AppConfig.BUGLY_APP_ID, BuildConfig.DEBUG);
        LeakCanary.install(this);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        //share sdk
//        MobSDK.init(this, AppConfig.MOB_APPKEY, AppConfig.MOB_APPSECRET);
//        SImagePicker.init(new PickerConfig.Builder().setAppContext(this)
//                .setImageLoader(new GlideImageLoader())
//                .build());
    }

    public static App getInstance() {
        return sInstance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //  MultiDex.install(this);
    }
}

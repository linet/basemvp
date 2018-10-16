package com.baseljc.mvp.base;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.baseljc.mvp.BuildConfig;
import com.baseljc.mvp.dagger.components.ApplicationComponent;
import com.baseljc.mvp.dagger.components.DaggerApplicationComponent;
import com.baseljc.mvp.dagger.modules.ApplicationModule;
import com.baseljc.mvp.tools.timber.CrashlyticsTree;
import com.baseljc.mvp.util.other.PropertiesManager;
//import com.resourcestorelib.YunStoreHelper;
//import com.umeng.analytics.MobclickAgent;

import net.danlew.android.joda.JodaTimeAndroid;

import dagger.android.HasActivityInjector;
import timber.log.Timber;

//import com.soyute.tools.util.LogUtils;


/**
 * 由于dagger注解要用，我先建一下
 */
public abstract class BaseApplication extends MultiDexApplication implements HasActivityInjector {

    public static Application sApplication;
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
//        initTimber();
        sContext = getApplicationContext();
        sApplication = this;

        //初始化日志,由BaseApplication/onCreate完成
        initTimber();

        initDagger();

        //初始化应用属性,,由BaseApplication/onCreate完成
//        initProperiesManager();

        //初始化时间工具,由BaseApplication/onCreate完成
        JodaTimeAndroid.init(this);

        //初始化sharepreferences
//        SharedPreferencesUtils.initSharedPreferences(this);

    }



    /**
     * Setup Timber. We only enable the timber logcat tree in debug builds. Release builds tend to not have have logs:
     * a) As logs are not accessible to developers.
     * b) For security reasons.
     * c) For performance reasons.
     *
     * Having said that, we plant a second tree that takes {@link Timber#wtf} calls and posts them to crashlytics (but not logcat).
     */
    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(initCrashlyticsTree());
        }
    }

    protected CrashlyticsTree initCrashlyticsTree() {
        return new CrashlyticsTree();
    }

    protected static ApplicationComponent applicationComponent;

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    public static PropertiesManager propertiesManager;

}

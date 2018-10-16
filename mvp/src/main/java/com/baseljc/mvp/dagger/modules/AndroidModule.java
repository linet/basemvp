package com.baseljc.mvp.dagger.modules;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linet on 2017/8/21.
 */

@Module
public class AndroidModule {


    private AndroidModule() {
        throw new AssertionError();
    }

    @Provides
    @Singleton
    public static Resources providesResources(Application application) {
        return application.getResources();
    }

    @Provides
    @Singleton
    public static AssetManager providesAssetManager(Resources resources) {
        return resources.getAssets();
    }

}

package com.baseljc.mvp.util.other;


import android.content.res.AssetManager;


import com.baseljc.mvp.networking.GlobalValue;
import com.baseljc.mvp.util.nullability.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Nullable;

import timber.log.Timber;

/**
 * Created by linet on 2017/8/21.
 * //TODO::这个类有啥作用
 * 专门用来管理project.properties配置好的属性值，
 * 这里要改为单例的形式，不要放在Module中；
 * 通过添加hashmap，管理一些动态的内容
 * 1、如域名、
 * 2、token
 * 3、BuildConfig.DEBUG
 */
public class PropertiesManager {


    private static PropertiesManager mPropertiesManager;
    private static final String PROPERTIES_FILENAME = "project.properties";

    private final Properties properties;
    private final Map<String, Object> properties2 = new HashMap<>();

    public static PropertiesManager share() {
        return mPropertiesManager;
    }

    public static PropertiesManager share(AssetManager assetManager) {
        mPropertiesManager = new PropertiesManager(assetManager);
        return mPropertiesManager;
    }

    private PropertiesManager(AssetManager assetManager) {

        properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = assetManager.open(PROPERTIES_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            Timber.e(e, "Failed to close input stream");
        }
    }

    public String getDribleClientAccessToken() {
        @Nullable String authToken = properties.getProperty(Property.DRIBBLE_CLIENT_ACCESS_TOKEN.getPropertyKey());
        Preconditions.checkNotNull(authToken);
        return authToken;
    }

    public boolean getBuildConfig() {
        @Nullable Boolean buildconfig = (Boolean) properties2.get(Property.BUILD_CONFIG.getPropertyKey());
        if (buildconfig == null) {
            //做activity单元测试的时候必须添加
            buildconfig = false;
        }
        Preconditions.checkNotNull(buildconfig);
        return buildconfig;
    }

    public void setBuildConfig(boolean buildConfig) {
        setProperty(Property.BUILD_CONFIG.getPropertyKey(), Boolean.valueOf(buildConfig));
    }

    public String getBaseUrl() {
        @Nullable String baseUrl = (String) properties2.get(Property.BASE_URL.getPropertyKey());
        if (baseUrl == null) {
            // 做activity单元测试的时候必须添加
            baseUrl = GlobalValue.BASE_API_URL;
        }
        Preconditions.checkNotNull(baseUrl);
        return baseUrl;
    }

    public void setBaseUrl(@Nullable String baseUrl) {
        setProperty(Property.BASE_URL.getPropertyKey(), baseUrl);
    }


    public void setProperty(@Nullable String key, @Nullable Object value) {
        properties2.put(key, value);
    }

    private enum Property {

        DRIBBLE_CLIENT_ACCESS_TOKEN("dribbleClientAccessToken"),
        BUILD_CONFIG("BuildConfig"),
        BASE_URL("dribbleBaseUrl");

        private final String propertyKey;

        Property(String authToken) {

            this.propertyKey = authToken;
        }

        public String getPropertyKey() {

            return propertyKey;
        }
    }

}

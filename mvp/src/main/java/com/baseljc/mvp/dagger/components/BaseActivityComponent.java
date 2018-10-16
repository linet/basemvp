package com.baseljc.mvp.dagger.components;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by linet on 2017/8/21.
 */

public interface BaseActivityComponent<ACTIVITY extends AppCompatActivity> {

    void inject(ACTIVITY activity);

}

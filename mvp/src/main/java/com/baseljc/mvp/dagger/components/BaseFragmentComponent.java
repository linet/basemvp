package com.baseljc.mvp.dagger.components;

import android.support.v4.app.Fragment;

/**
 * Created by linet on 2017/8/21.
 */

public interface BaseFragmentComponent<FRAGMENT extends Fragment> {
    
    void inject(FRAGMENT fragment);

}

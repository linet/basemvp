package com.baseljc.mvp.view;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by linet on 2017/8/21.
 */

public final class ButterknifeActions {

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_GONE = (view, index) -> view.setVisibility(View.GONE);

    public static final ButterKnife.Action<View> SET_VISIBILITY_TO_VISIBLE = (view, index) -> view.setVisibility(View.VISIBLE);

    private ButterknifeActions() {
        throw new AssertionError();
    }

}

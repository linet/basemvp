package com.baseljc.mvp.base;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

public class BaseViewState<T extends MvpView> implements ViewState<T> {

    @Override
    public void apply(T view, boolean retained) {

    }
}

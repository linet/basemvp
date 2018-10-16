package com.baseljc.mvpdemo;

import com.baseljc.mvp.base.BaseActivity;
import com.baseljc.mvp.base.BaseApplication;
import com.baseljc.mvp.base.BaseViewState;
import com.baseljc.mvpdemo.contact.MainActivity2View;
import com.baseljc.mvpdemo.dagger.components.DaggerMain2Component;
import com.baseljc.mvpdemo.dagger.components.Main2Component;
import com.baseljc.mvpdemo.presenter.MainPresenter2;

public class MainActivity2
        extends BaseActivity<
        Main2Component,
        MainActivity2View,
        MainPresenter2,
        BaseViewState<MainActivity2View>> {


    @Override
    protected Main2Component constructComponent() {
        return DaggerMain2Component.builder()
                .applicationComponent(BaseApplication.getApplicationComponent())
                .build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }
}

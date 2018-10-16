package com.baseljc.mvpdemo.dagger.modules;

import com.baseljc.mvp.base.BaseViewState;
import com.baseljc.mvpdemo.contact.MainActivity2View;
import com.baseljc.mvpdemo.contact.MainView;
import com.baseljc.mvpdemo.presenter.MainPresenter2;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule2 {


    @Provides
    public MainPresenter2 providedMainPresenter() {
        return new MainPresenter2();
    }


    @Provides
    public BaseViewState<MainActivity2View> providedBaseViewStateMainView() {
        return new BaseViewState<MainActivity2View>();
    }
}

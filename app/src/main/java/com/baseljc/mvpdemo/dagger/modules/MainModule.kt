package com.baseljc.mvpdemo.dagger.modules

import com.baseljc.mvp.base.BaseViewState
import com.baseljc.mvpdemo.contact.MainView
import com.baseljc.mvpdemo.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {


    @Provides
    fun providedMainPresenter() : MainPresenter {
        return MainPresenter()
    }


    @Provides
    fun providedBaseViewStateMainView() : BaseViewState<MainView> {
        return BaseViewState<MainView>()
    }



}
package com.baseljc.mvp.base;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.baseljc.mvp.dagger.components.BaseActivityComponent;

/**
 * Created by linet on 2017/11/17.
 * 权限封装类
 * 继承 BaseActivity
 */

public abstract class BasePermissionActivity<
        COMPONENT extends BaseActivityComponent,
        VIEW extends MvpView,
        PRESENTER extends MvpPresenter<VIEW>,
        VIEW_STATE extends ViewState<VIEW>>
        extends BaseActivity<COMPONENT, VIEW, PRESENTER, VIEW_STATE> {




}

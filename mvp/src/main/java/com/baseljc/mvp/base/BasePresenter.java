package com.baseljc.mvp.base;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 0200030 on 2017/8/30.
 */

public class BasePresenter<T extends MvpView> extends MvpNullObjectBasePresenter<T> {

    protected CompositeDisposable composite;

    @Override
    public void attachView(@NonNull T view) {
        super.attachView(view);
        this.composite = new CompositeDisposable();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance && composite != null && !composite.isDisposed()) {
            composite.dispose();
        }
    }
}

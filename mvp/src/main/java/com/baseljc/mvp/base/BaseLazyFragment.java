package com.baseljc.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baseljc.mvp.base.action.ActionView;
import com.baseljc.mvp.base.action.BaseActionActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.baseljc.mvp.dagger.components.BaseFragmentComponent;
//import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 0200030 on 2017/9/2.
 */
public abstract class BaseLazyFragment<
        COMPONENT extends BaseFragmentComponent
        , VIEW extends MvpView
        , PRESENTER extends MvpPresenter<VIEW>
        , VIEW_STATE extends ViewState<VIEW>>
        extends Fragment
        implements MvpViewStateDelegateCallback<VIEW, PRESENTER, VIEW_STATE>, LoadMvpView, ActionView {

    protected Context mContext;
    protected View rootView;
    protected Unbinder unbinder;
    protected Activity activity;
    protected boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    protected boolean hasFetchData; // 标识已经触发过懒加载数据

    /**
     * Can't inject directly, as the presenter instantiation needs to happen by mosby in {@link this#createPresenter()}.
     */
    @Inject
    Provider<PRESENTER> presenterProvider;
    private PRESENTER presenter;

    /**
     * Can't inject directly, as the presenter instantiation needs to happen by mosby in {@link this#createViewState()}.
     */
    @Inject
    Provider<VIEW_STATE> viewStateProvider;
    private VIEW_STATE viewState;


    // Delegate propagation ****************************************************************************************************************

    protected FragmentMvpDelegate<VIEW, PRESENTER> mvpDelegate;

    //    @Override
    protected FragmentMvpDelegate<VIEW, PRESENTER> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this, this, true, true);
        }
        return mvpDelegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
//        initView(inflater);
//        EventBus.getDefault().register(this);
//        setTitleHeight(rootView);
        return rootView;
    }

    protected abstract COMPONENT constructComponent();

    protected abstract int getLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        unbinder = ButterKnife.bind(this, view);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
//        KL.d(this.getClass(), getName() + "------>onDestroyView");
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false;
        isViewPrepared = false;
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
        COMPONENT component = constructComponent();
        component.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
//        if (unbinder != null)
//            unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
//        MobclickAgent.onPageEnd(getClass().getSimpleName() + "");//友盟统计当前fragment停留时间
//        Timber.d("友盟统计----------->>>>>>>>-----------onPageEnd,该页面名称:" + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
//        MobclickAgent.onPageStart(getClass().getSimpleName() + "");//友盟统计当前fragment停留时间
//        Timber.d("友盟统计----------->>>>>>>>-----------onPageStart,该页面名称:" + getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getMvpDelegate().onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMvpDelegate().onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    // View state related ******************************************************************************************************************

    /**
     * A simple flag that indicates if the restoring ViewState  is in progress right now.
     */
    private boolean restoringViewState = false;


    @Override
    public VIEW_STATE getViewState() {
        return viewState;
    }

    @Override
    public void setViewState(VIEW_STATE viewState) {
        this.viewState = viewState;
    }

    @Override
    public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override
    public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // not needed. You could override this is subclasses if needed
    }

    @Override
    public VIEW_STATE createViewState() {
        return viewStateProvider.get();
    }

    // MVP related *************************************************************************************************************************


    /**
     * Creates a new presenter instance, if needed. Will reuse the previous presenter instance if
     * {@link #setRetainInstance(boolean)} is set to true. This method will be called from
     * {@link #onViewCreated(View, Bundle)}
     */
    public PRESENTER createPresenter() {
        return presenterProvider.get();
    }

    @NonNull
    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull PRESENTER presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public VIEW getMvpView() {
        return (VIEW) this;
    }

    //懒加载相关*********************************************************************************************************
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.v(TAG, getClass().getName() + "------>isVisibleToUser = " + isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    protected void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

//    protected void initView(LayoutInflater inflater) {
//    }

//    protected void initEvent() {
//    }

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
//        KL.d(this.getClass(), getName() + "------>onAttach");
        if (mContext != null) {
            this.mContext = mContext;
        } else {
            this.mContext = getActivity();
        }
    }

    @Override
    public void onNewViewStateInstance() {
        lazyFetchDataIfPrepared();
    }

    /**
     * 如果发现没有没有走此方法,可以看看该fragment所依附的activity 是否重写了此方法导致的
     */
    @Override
    public void showLoadingBar() {
        ((BaseAppCompatActivity) getActivity()).showLoadingBar();
    }

    @Override
    public void dismissLoadingBar() {
        ((BaseAppCompatActivity) getActivity()).dismissLoadingBar();
    }

    @Override
    public void showLoadingFailureError() {
        ((BaseAppCompatActivity) getActivity()).showLoadingFailureError();
    }

    @Override
    public void action(String actionContent) {
        if (BaseLazyFragment.this.getActivity() instanceof BaseActionActivity) {
            BaseActionActivity actionActivity = (BaseActionActivity) BaseLazyFragment.this.getActivity();
            actionActivity.action(actionContent);
        }
    }
}

package com.baseljc.mvp.base;

import android.Manifest;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;
import com.baseljc.mvp.dagger.components.BaseFragmentComponent;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by linet on 2017/11/17.
 * 继承 BaseLazyFragment
 */
@RuntimePermissions
public abstract class BasePermissionFragment<
        COMPONENT extends BaseFragmentComponent
        , VIEW extends MvpView
        , PRESENTER extends MvpPresenter<VIEW>
        , VIEW_STATE extends ViewState<VIEW>>
        extends BaseLazyFragment<COMPONENT, VIEW, PRESENTER, VIEW_STATE> {

    private String type;
    private int tag;

    /**
     * 统一请求
     *
     * @param type 请求权限类型 @see Manifest.permission.READ_CONTACTS
     * @param tag  给回调方法用到的标记
     */
    public void requestPersion(String type, int tag) {
        this.type = type;
        this.tag = tag;
        if (TextUtils.equals(type, Manifest.permission.READ_CONTACTS)) {
//            BasePermissionFragmentPermissionsDispatcher.requestPermissionForContactWithPermissionCheck(this);
        }
    }

    /**
     * 请求哪些权限,且当用户允许权限后调用此方法
     */
    @NeedsPermission({Manifest.permission.READ_CONTACTS})
    public void requestPermissionForContact() {
        requestPerssionSuccess(type, tag);
    }

    /**
     * 为什么要请求该权限,给用户提示(这个只有在用户第一次拒绝后,再次请求时才会调用此方法),本APP就不用这么讲究了
     *
     * @param request
     */
//    @OnShowRationale({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
//    public void whyRequestContactPermission(final PermissionRequest request) {
//    }

    /**
     * 用户拒绝该权限回调
     */
    @OnPermissionDenied({Manifest.permission.READ_CONTACTS})
    public void requestContactFail() {
        Toast.makeText(getContext(), "将不能获取通讯录", Toast.LENGTH_SHORT).show();
    }

    /**
     * 当用户点击拒绝权限且不再弹出此窗口,掉用此方法
     */
    @OnNeverAskAgain({Manifest.permission.READ_CONTACTS})
    public void requestContackAskFail() {
        Toast.makeText(getContext(), "你点击了不再提示窗口", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        BasePermissionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 当获取该权限后,回调的方法
     *
     * @param type
     * @param tag
     */
    public abstract void requestPerssionSuccess(String type, int tag);
}

package com.baseljc.mvp.base.action;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


import timber.log.Timber;

/**
 * Created by linet on 2017/11/29.
 * 集中统一发送事件
 */

public class BaseActionActivity extends AppCompatActivity implements ActionView {

    @Override
    public void action(String actionContent) {
        if (TextUtils.isEmpty(actionContent)) {
            return;
        }
        Timber.d("----------->>>>>>>>-----------事件:" + actionContent);

//        //登录登出APP
//        if (TextUtils.equals(actionContent, LogIn_App)) {
//            //登录IM
//            IOpenimService serviceInterface = new OpenimProxy().getServiceInterface();
//            if (serviceInterface != null) {
//                serviceInterface.loginOpenIM();
//            }
//            //更新当前登陆者的关注/粉丝人
//            IContactService serviceInterface1 = new ContactProxy().getServiceInterface();
//            if (serviceInterface1 != null) {
//                serviceInterface1.startAttentionIntentService(BaseActionActivity.this);
//            }
//
//            return;
//        }
//        //获取baseinfo信息
//        if (TextUtils.equals(actionContent, Get_BaseInfo)) {
//            //登录IM
//            IOpenimService serviceInterface = new OpenimProxy().getServiceInterface();
//            if (serviceInterface != null) {
//                serviceInterface.loginOpenIM();
//            }
//        }
//        if (TextUtils.equals(actionContent, LogOut_App)) {
//            //登出IM
//            IOpenimService serviceInterface = new OpenimProxy().getServiceInterface();
//            if (serviceInterface != null) {
//                serviceInterface.logoutOpenIM();
//            }
//            return;
//        }

//        //与关注/取消关注相关
//        if (actionContent.contains(Attention_Follow) || actionContent.contains(Attention_CancleFollow)) {
//            EventBus.getDefault().post(new CongenailEvent(actionContent));
//            IContactService serviceInterface = new ContactProxy().getServiceInterface();
//            if (serviceInterface != null) {
//                serviceInterface.startAttentionIntentService(BaseActionActivity.this);
//            }
//            return;
//        }

//        //与新增圈子/修改圈子信息相关
//        if (TextUtils.equals(actionContent, Congenial_Add) ||
//                TextUtils.equals(actionContent, Congenial_Delete) ||
//                TextUtils.equals(actionContent, Congenial_UpdateInfo)
//                ) {
//            EventBus.getDefault().post(new CongenailEvent(actionContent));
//            return;
//        }

//        //自营商品相关
//        if (TextUtils.equals(actionContent, Mall_Cart_Add_Good) ||
//                TextUtils.equals(actionContent, Mall_Order_Add) ||
//                TextUtils.equals(actionContent, Mall_Order_Cancel) ||
//                TextUtils.equals(actionContent, Mall_Cart_Delete_Good) ||
//                TextUtils.equals(actionContent, Mall_Cart_Clear_Good)
//                ) {
//            EventBus.getDefault().post(new MallEvent(actionContent));
//            return;
//        }
//        //订单详情取消订单
//        if (TextUtils.equals(actionContent, Mall_Order_Detail_Cancel)) {
//            EventBus.getDefault().post(new BaseListEvents.MyOrderDetailCancelEvent(actionContent));
//        }
    }
}

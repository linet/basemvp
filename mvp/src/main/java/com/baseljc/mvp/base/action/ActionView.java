package com.baseljc.mvp.base.action;

/**
 * Created by linet on 2017/11/29.
 */

public interface ActionView {

//    //与登录相关
//    String LogIn_App = "LogIn_App";
//    String LogOut_App = "LogOut_App";
//
//    //与通讯录相关
//
//    //与关注/取消关注相关
//    String Attention_Follow = "Attention_Follow";
//    String Attention_CancleFollow = "Attention_CancleFollow";

//    //与新增圈子/修改圈子信息相关
//    String Congenial_UpdateInfo = "Congenial_UpdateInfo";//修改圈子信息
//    String Congenial_Add = "Congenial_Add";//新增圈子
//    String Congenial_Delete = "Congenial_Delete";//删除圈子
//
//    //自营商品相关
//    String Mall_Cart_Add_Good = "Mall_Cart_Add_Good";//添加商品至购物车
//    String Mall_Cart_Delete_Good = "Mall_Cart_Delete_Good";//删除购物车商品
//    String Mall_Cart_Clear_Good = "Mall_Cart_Clear_Good";//清除购物车商品
//    String Mall_Order_Add = "Mall_Order_Add";//新增订单
//    String Mall_Order_Cancel = "Mall_Order_Add";//取消增订单
//    String Mall_Order_Detail_Cancel = "Mall_Order_Detail_Add";//订单详情 取消订单
//
//    //收藏相关
//    String Collect_Cancel = "Collect_Cancel";//取消收藏
//
//    //获取baseinfo信息
//    String Get_BaseInfo = "Get_BaseInfo";

    /**
     * 方便统一管理发送EventBus事件
     *
     * @param actionContent
     */
    void action(String actionContent);
}

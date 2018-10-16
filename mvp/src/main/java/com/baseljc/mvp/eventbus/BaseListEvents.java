package com.baseljc.mvp.eventbus;

/**
 * Created by linet on 16/12/30.
 */


/**
 * 基本事件
 * 注：为了便于事件管理添加和维护。避免新建很多不必要的类和重载过多的事件处理方法。
 */
public interface BaseListEvents {
    /**
     * Created by gxj on 2017/11/29.
     * 订单详情 取消订单
     */

    class MyOrderDetailCancelEvent {
        public String operateContent;

        public MyOrderDetailCancelEvent(String operateContent) {
            this.operateContent = operateContent;
        }
    }

}

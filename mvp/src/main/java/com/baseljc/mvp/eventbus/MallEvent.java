package com.baseljc.mvp.eventbus;

/**
 * Created by linet on 2017/11/29.
 */

public class MallEvent {
//    public static final String Add_Cart_Success = "Add_Cart_Success";
    public String operateContent;

    public MallEvent(String operateContent) {
        this.operateContent = operateContent;
    }
}

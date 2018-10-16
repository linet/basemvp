package com.baseljc.mvp.eventbus;

/**
 * Created by linet on 2017/11/29.
 */

public class CongenailEvent {
    public String actionType;//操作类型
    public String actionDesc;//操作类型
    public int groupId;//圈子ID

    public CongenailEvent(String actionType) {
        this.actionType = actionType;
    }

    public CongenailEvent(String actionType, String actionDesc, int groupId) {
        this.actionType = actionType;
        this.actionDesc = actionDesc;
        this.groupId = groupId;
    }
}

package com.hpe.dataload.generator.utils;

import com.hpe.dataload.generator.model.dataloadmodel.GroupType;

public class NextGroupReminder {
    String id;

    String callerGroupName;
    int callerGroupOrder;
    GroupType.Indexes.Index callerIndex;
    String actionName;
    String calledGroupName;
    String calledGroupOrder;
    String nextGroupName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallerGroupName() {
        return callerGroupName;
    }

    public void setCallerGroupName(String callerGroupName) {
        this.callerGroupName = callerGroupName;
    }

    public int getCallerGroupOrder() {
        return callerGroupOrder;
    }

    public void setCallerGroupOrder(int callerGroupOrder) {
        this.callerGroupOrder = callerGroupOrder;
    }

    public GroupType.Indexes.Index getCallerIndex() {
        return callerIndex;
    }

    public void setCallerIndex(GroupType.Indexes.Index callerIndex) {
        this.callerIndex = callerIndex;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCalledGroupName() {
        return calledGroupName;
    }

    public void setCalledGroupName(String calledGroupName) {
        this.calledGroupName = calledGroupName;
    }

    public String getCalledGroupOrder() {
        return calledGroupOrder;
    }

    public void setCalledGroupOrder(String calledGroupOrder) {
        this.calledGroupOrder = calledGroupOrder;
    }

    public void setNextGroupName(String nextGroupName) {
        this.nextGroupName = nextGroupName;
    }

    public String getNextGroupName() {
        return nextGroupName;
    }
}

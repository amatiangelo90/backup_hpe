package com.hpe.dataload.generator.utils;

import com.hpe.dataload.generator.model.dataloadmodel.GroupType;

import java.util.List;

public class Checkpoint {
    String id;

    String groupName;
    int groupOrder;
    List<String> stateVector;

    String blockName;
    String nextGroupCalled;
    String nextGroupName;
    GroupType.Indexes.Index index;


    public String getNextGroupName() {
        return nextGroupName;
    }

    public void setNextGroupName(String nextGroupName) {
        this.nextGroupName = nextGroupName;
    }

    @Override
    public String toString() {
        return "Checkpoint{" +
                "id='" + id + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupOrder=" + groupOrder +
                ", stateVector=" + stateVector +
                ", blockName='" + blockName + '\'' +
                ", nextGroupCalled='" + nextGroupCalled + '\'' +
                ", index=" + index +
                '}';
    }

    public GroupType.Indexes.Index getIndex() {
        return index;
    }

    public void setIndex(GroupType.Indexes.Index index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getStateVector() {
        return stateVector;
    }

    public void setStateVector(List<String> stateVector) {
        this.stateVector = stateVector;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getNextGroupCalled() {
        return nextGroupCalled;
    }

    public void setNextGroupCalled(String nextGroupCalled) {
        this.nextGroupCalled = nextGroupCalled;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }
}

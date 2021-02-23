package com.hpe.dataload.generator.orm.model;

import com.hpe.dataload.generator.model.dataloadmodel.GroupType;

public class GroupTypeWrap {

    protected String name;
    protected GroupType.States states;
    protected GroupType.Indexes indexes;
    protected int order;
    protected String groupXmlString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupType.States getStates() {
        return states;
    }

    public void setStates(GroupType.States states) {
        this.states = states;
    }

    public GroupType.Indexes getIndexes() {
        return indexes;
    }

    public void setIndexes(GroupType.Indexes indexes) {
        this.indexes = indexes;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getGroupXmlString() {
        return groupXmlString;
    }

    public void setGroupXmlString(String groupXmlString) {
        this.groupXmlString = groupXmlString;
    }
}

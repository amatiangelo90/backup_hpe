package com.hpe.dataload.generator.orm.model;

import java.util.Objects;

public class ActionDetails {
    //    @JsonProperty("name")
    private String name;
    //    @JsonProperty("proceed")
    private boolean proceed;
    //    @JsonProperty("reccomended")
    private boolean reccomended;
    //    @JsonProperty("possible")
    private boolean possible;
    //    @JsonProperty("excluded")
    private boolean excluded;

    public ActionDetails(){}

    public ActionDetails(String name) {
        this.name = name;
        this.reccomended = true;
        this.proceed = false;
        this.excluded = false;
        this.possible = false;
    }

    public ActionDetails(String name, boolean proceed, boolean reccomended, boolean possible, boolean excluded) {
        this.name = name;
        this.proceed = proceed;
        this.reccomended = reccomended;
        this.possible = possible;
        this.excluded = excluded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }

    public boolean isReccomended() {
        return reccomended;
    }

    public void setReccomended(boolean reccomended) {
        this.reccomended = reccomended;
    }

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionDetails)) return false;
        ActionDetails that = (ActionDetails) o;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ActionDetails{" +
                "name='" + name + '\'' +
                ", proceed=" + proceed +
                ", reccomended=" + reccomended +
                ", possible=" + possible +
                ", excluded=" + excluded +
                '}';
    }
}

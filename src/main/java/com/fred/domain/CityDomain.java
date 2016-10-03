package com.fred.domain;

public class CityDomain extends AbstractDomain {

    private String name;
    private StateDomain state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateDomain getState() {
        return state;
    }

    public void setState(StateDomain state) {
        this.state = state;
    }
}

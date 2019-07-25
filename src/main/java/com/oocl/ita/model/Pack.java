package com.oocl.ita.model;

import javax.persistence.*;

@Entity
@Table(name = "pack")
public class Pack {

    @Id
    @Column(columnDefinition = "char(12)")
    private String id;
    private String name;
    @Column(columnDefinition = "char(11)")
    private String phone;
    private String state;
    private double weight;
    private long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

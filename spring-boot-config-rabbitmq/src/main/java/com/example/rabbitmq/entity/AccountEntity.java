package com.example.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author
 */

public class AccountEntity implements Serializable{
    private static final long serialVersionUID = 4938771119652125114L;
    private int id;
    private String name;
    private double money;

    public AccountEntity(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}

package com.liren.model;

public class RealHouseSubject implements HouseSubject{
    @Override
    public void rentHouse() {
        System.out.println("我是房东, 我出租房子");
    }
}

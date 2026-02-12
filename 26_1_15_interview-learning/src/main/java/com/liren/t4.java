package com.liren;

import com.liren.model.HouseSubject;
import com.liren.model.RealHouseSubject;

import java.lang.reflect.Proxy;

public class t4 {
    public static void main(String[] args) {
        HouseSubject target = new RealHouseSubject();
        HouseSubject proxy = (HouseSubject)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JDKInvocationHandler(target)
        );
        proxy.rentHouse();
    }
}

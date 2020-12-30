package com.company;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class MyCircle implements IShape {

    // notice the 'implements' keyword followed by an interface name
    // this means that our MyCircle class must have implementations of the
    // two methods in  the Interface IShape, namely getArea and getPerimeter

    public static final double S_PI=3.1415;
    // static member variable common across all objects of class MyCircle
    // the final indicates its value can't be changed

    private double m_radius;

    public MyCircle(double radius){
        // constructor must have the same name as teh Class
        this.m_radius = radius;
    }

    public double getArea(){
        return S_PI*m_radius*m_radius;
    }

    public double getPerimeter(){
        return S_PI*2*m_radius;
    }
}

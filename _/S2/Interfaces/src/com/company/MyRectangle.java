package com.company;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class MyRectangle implements IShape{

    private double m_length;
    private double m_breadth;

    public MyRectangle(double length, double breadth){
        // constructor with same name as the class - takes in values for both dimensions
        this.m_breadth=breadth;
        this.m_length=length;
    }

    public double getArea(){ return m_breadth*m_length;};

    public double getPerimeter(){
        return 2*(m_length+m_breadth);
    }
}

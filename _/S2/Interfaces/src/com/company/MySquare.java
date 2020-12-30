package com.company;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class MySquare extends  MyRectangle {
    // notice the keyword extends. This means that any object of class
    // MySquare is-an object of Class MyRectangle

    private double m_side;
    public MySquare(double side){
        super(side,side);
        // more on this later - but basically 'super' is a way of calling the constructor of MyRectangle
        this.m_side=side;
    }

    // why is inheritance magical? because the class MySquare did not implement the methods in the interface
    // IShape (namely getArea and getPerimeter) and even so MySquare is an IShape
}

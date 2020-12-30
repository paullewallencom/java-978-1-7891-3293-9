package com.company;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

       // now we can instantiate a few objects of type MyCircle and MyRectangle
        MyRectangle myRec=new MyRectangle(5,4);
        MyCircle myCircle = new MyCircle(10);

        // note that we cannot instantiate an object of an interface!

        // ERROR: IShape myShape = new IShape();
        // hovering over the red underlining tells us 'IShape' is abstract and can't be instantiated

        // notice how we can create a list of shapes using the interface

        List<IShape> myShapeList= new ArrayList<IShape>();
        myShapeList.add(myRec);
        myShapeList.add(myCircle);

        for (IShape someShape:myShapeList){
            System.out.println("Area of this shape = "+someShape.getArea()+","+
             " Perimeter of this shape="+someShape.getPerimeter());
        }

        // and guess what is even more interesting
        // you can't instantiate an object of type IShape since that is merely an interface
        // but you can instantiate an object of type MyRectangle or MyCircle and put it in a variable of
        // type IShape

        IShape someOtherShape = new MyRectangle(12,12);
        System.out.println("Area of this shape = "+someOtherShape.getArea()
                +", Perimeter of this shape="+someOtherShape.getPerimeter());

        IShape mySquare = new MySquare(4);
        System.out.println("Area of this shape = "+ mySquare.getArea()+
                        ", Perimeter of this shape="+mySquare.getPerimeter());





    }
}

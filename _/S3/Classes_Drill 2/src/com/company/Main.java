package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// Ok - now let's create and use objects of class Musketeer that we jsut created

        // Call to the first constructor, which takes in both name and age
        Musketeer m1 = new Musketeer("Athos",57);
        // instantiate the Musketeer class, ie create an object of this type

        // Call to the second constructor, which takes in only the name, and assigns a default age
        Musketeer m2 = new Musketeer("Porthos");
        // instantiate yet another object of the Musketeer class


        Musketeer m3 = new Musketeer();

        // Hmmm.. ok this is an atempt to instantiate an object of the Musketeer class by
        // calling a constructor that does not take in any arguments
        // But - this won't work, because we have only created 2 constructors, both which take in
        // arguments (2, or 1)
        // That explains the red underlining the line above - this code yields a complite - time error

        // to fix, let's create a third constructor, with a default value for the name as well as the age

        // once finished - the red underlining vanishes - the code is OK to compile

        // OK, let's create a list of Musketeers.


        List<Musketeer> musketeerList = new ArrayList<>();
        musketeerList.add(m1);
        musketeerList.add(m2);
        musketeerList.add(m3);

        // note how we can create lists of Musketeers just like we created lists of strings or numbers
        // and can use the '.add' method to insert objects of the Musketeer class to that list

        // similarly, we can iterate over this list like we would any other list

        for (Musketeer m:musketeerList){
            System.out.println("Name of this musketeer = "+m.getName()+" , Age of this musketeer="+ m.getAge());
        }




























    }
}

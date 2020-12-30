package com.company;
// when you open up your Java IDE (here I am using IntelliJ), this is what you see first -
// a blank class with a static method called main

// Remember that in Java everything needs to be in a class, unlike say in Python, no code can be
// floating outside a class
// this import statement was magically entered by IntelliJ when we hit 'Alt+Enter' on the error
// below
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Also note that a class must be in a file with the same name. For instance, here the class is named Main
// and is in a file called Main.java
// If you rename your class you must also rename your file
public class Main {

    // a static method called main. Recall that static methods are common to all instances (obects)
    // of a class

    // the static function named 'main' is special. Java when it looks to run code, looks for a function
    // named 'main' and will execute whatever is in it. 'main' is an entry point for code.
    // Note that every class can have a main function, and we can choose which one to run using the IDE

    public static void main(String[] args) {

    // first, let's print something to screen

        System.out.println("Hello World!!");

        // btw, all statements in Java must end with a semi-colon; (unless they start a complex block of code
        // in which case you omit the semi-colon and use curly braces instead

        // OK, now let's create variables of a few different types
        // As you recall, there are 2 types of variables in Java - primitive types and object/reference types
        // primitive types include integers, individual characters, bytes etc
        // They are created in a section of memory called the stack
        // object reference types - which include strings, arrays, dictionaries and lists-
        // are all objects (instances) of classes. They exist in an area of memory called the heap.

        // Java takes care of memory management for both types of variables, but other languages like C or C++
        // would only do memory management for primitive types
        // (and not for object reference types)


        int i=0;
        String someString="some String";

        // Now let's print these out to screeen
        System.out.println("This is a number "+i+" this is a string: " +someString);

        // Cool! Let's try running this code.

        // Ok - moving on to arrays and lists
        // Unlike in python, arrays and lists are different types of containers in Java
        // Arrays are similar to the syntax of older languages (C/C++) and can hold both primitive and
        // reference types

        // Lists on the other hand can only hold object reference types. this might seem weird - and it is
        // But Java has wrapper types for all the primitive types, so in reality
        // there is almost no use case for an array over a list

        // Again - when in doubt Use a list, not an array


        // create an array of 4 integers
        int [] arrayOfIntegers = new int[4];

        // this line above tells us why lists are preferable to arras -
        // arrays have a fixed - pre-determined length

        arrayOfIntegers[0]=55;
        arrayOfIntegers[1]=67;
        arrayOfIntegers[2]=34;
        arrayOfIntegers[3]=22;

        // notice how we used the square brackets to assign values to the elements of the array

        // let's create an array of 4 strings, using a slightly different syntax
        String[] arrayOfStrings = {"Athos","Pothos","Aramis","D'Artagnan"};

        // this is a single line declaration + initialization of the array
        // notice how the array is initialized using curly braces - this is different from say Python
        // where square brackets initialized lists(arrays) and curly braces initialize dictionaries

        // OK - this bears repeating - in Java all variables must have types declared and adhered to. For instance - the array above
        // is explicitly defined as an array of integers
        // If we tried to assign a string to an element of this array, an error would result. This again is diffferent
        // from Python - where arrays can hold variables of different types


        for (String mName:arrayOfStrings) {
            // this is our first for-loop in Java. Notice how we have an iterator variable
            // that will walk over an array of Strings
            System.out.println("Inside our first for-loop:" +mName);
        }

        for (int x=0; x<arrayOfIntegers.length; x++){
            // this is our second for-loop in Java
            // this one has a different syntax. It has an iterator variable
            // (called 'x'), which is iniialized to some value (here '0')
            // The for-loop then continues executing, incrementing he value of x by 1 in each iteration
            // of the loop, until the condition in the for loop ('x<arrayOfIntegers.length')
            // is not true anymore

            // Note also - arrays in java know their own length.
            // how? because they are objects of a class, and the '.length' is a member variable that
            // keeps track of the length of an array
            System.out.println("Inside our second for-loop:"+arrayOfIntegers[x] +"is the "+x+"-th element");
        }

        // Cool! No let's create our lists of integers and numbers

        List<Integer> listOfIntegers = new ArrayList<>();
        List<String> listOfStrings = new ArrayList<>();

        // Notice again how the types of variables are super-important. When we create a list,
        // we have to specify what type of element will go into that list. We do this using the '<Integer>'
        // notation.
        // Also note how, since Lists can no hold integers, which are primitive types, we need to create a
        // list meant to hold 'Integer' (upper-case I) type variables instead.
        // In general, for a given primitive type , the corresponding java wrapper is a class with the same
        // name and upper case first character

        for(int y =0; y<arrayOfIntegers.length; y++){
            // note that we are inside our third for-loop, in which
            // we will populate the listis from the corresponding elements
            //of the arrays.
            listOfIntegers.add(arrayOfIntegers[y]);
            listOfStrings.add(arrayOfStrings[y]);
            // notice that the square breackets are used to access the 'y-th' element of an array
        }


        for (int z=0; z<listOfIntegers.size(); z++){
            //notice how the length of an array is obtained using '.length', while
            // that of a list is obtained via the '.size()' member function

            // this is a for loop that iterates over the strings in our list of strings
            System.out.println(listOfStrings.get(z));
            // notice also how we use square brackets to get a specific element of an array
            // but the 'get()' member function to get a specific element of a list
        }


        // OK, on to dictionaries, aka maps, which are containers inside which key-value pairs can be stored
        Map<String, Integer> mapOfStringIntPairs=new HashMap<>();

        //Again - types are important. When we create a dictionary (map) in java, we need to
        // specify the type of both the key and the value

        for (int j=0; j<arrayOfIntegers.length;j++){
            mapOfStringIntPairs.put(arrayOfStrings[j],arrayOfIntegers[j]);
            // notice how we used the '.put()' method to insert a key-value pair into the map
            // here the key is the j-th element of arrayOfStrings, and the value is the
            // j-th element of the arrayOfIntegers
        }

        // Now let's try and check for the presence of some keys
        String someKey="Athos";
        if(mapOfStringIntPairs.containsKey(someKey)){
            // this is our first if-else statement in Java
            // notice how the .containsKey member funtion checks if a particular key
            // is in the dictionary

            // if yes -print out the corresponding value

            System.out.println("Key="+someKey+" is in the map and the corresponding value="+mapOfStringIntPairs.get(someKey));

        }
        else {
            System.out.println("Key "+someKey+" is not in the map");
        }





        //Finally let's iterate over each string in set of keys in our dictionary

        for (String oneKey:mapOfStringIntPairs.keySet()){
            System.out.println("One key in the map="+oneKey+",corresponding value="+mapOfStringIntPairs.get(oneKey));
            // notice how the '.get(key)' member function returns the value corresponding to a particular key
        }









































































    }
}

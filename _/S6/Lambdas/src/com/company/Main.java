package com.company;

import java.util.List;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        // we will use a simple example to do something with a list of names
        // in two different ways
        // 1. Imperatively (i.e. using for loops)
        // 2. Functionally (i.e. using lambda expressions and aggregation operators)

        // The simple operation we will try and do is - given a list of names
        // of US presidential candidates - sort them alphabetically, and then print
        // to screen the names of the Republican candidates
        // Oh, do not forget, if the name Donald Trump appears on the list of candidates then his name should
        // automatically be first, no matter the alphabetical ordering

        // Step 1: Create  a list of the candidates names
        List<String> candidateNames = new ArrayList<>();
        candidateNames.add("Hillary");
        candidateNames.add("Jeb");
        candidateNames.add("Marco");
        candidateNames.add("Donald Trump");
        candidateNames.add("Bernie");

        // Step 2: Create a map with the candidates party affiliations
        Map<String,String> partyAffiliations = new HashMap<>();
        partyAffiliations.put("Hillary","D");
        partyAffiliations.put("Jeb","R");
        partyAffiliations.put("Marco","R");
        partyAffiliations.put("Donald Trump","R");
        partyAffiliations.put("Bernie","D");

        // Step 3: Implement the Imperative solution. Let's do this in a method so that we have two
        // different methods one of the imperative solution and one for he functional solution
        imperativeSolution(candidateNames, partyAffiliations);

        // Step 4: Implement the same code using the functional solution. Let's once again do  it in a separate
        // function so that our code looks nice and clean

        functionalSolution(candidateNames, partyAffiliations);





    }

    public static void imperativeSolution(List<String> candidateNames, Map<String,String> partyAffiliations) {
        // 3.a sort the array list so that it has order we want - do this
        // using an anonymous inner class
        Collections.sort(candidateNames, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals("Donald Trump") && !o2.equals("Donald Trump")){
                    return -1;
                } else if (o2.equals("Donald Trump") && !o1.equals("Donald Trump")){
                    return 1 ;
                }
                return o1.compareTo(o2);
            }
        });
        // notice how we used an anonymous inner ckass which implements a
        // single-interface-method (aka functional interface).
        // This ought to be a sign to us that we can also do this using lambda expressions.
        // In any case, more on that later

        // 3.b use a for - loop to walk through the list of candidates, test if they are
        // REpublican, and if yes, print a message to screen
        for(String oneCandidate:candidateNames) {
            if(partyAffiliations.get(oneCandidate).equals("R")){
                System.out.println(oneCandidate + "(" + partyAffiliations.get(oneCandidate)+")");
            }
        }

    }

    public static void functionalSolution(List<String> candidateNames,Map<String,String> partyAffiliations){
        // Step 4a: Sort the arraylist so that it is the order that we want - but do this using a lambda
        // expression rather than an anonymous inner function
        Collections.sort(candidateNames, (o1,o2) ->{
            if (o1.equals("Donald Trump") && !o2.equals("Donald Trump")){
                return -1;
            } else if (o2.equals("Donald Trump") && !o1.equals("Donald Trump")){
                return 1 ;
            }

            return o1.compareTo(o2);
        });

        // the code you see above is very funky indeed. Especially if you are used to the syntax of Java and
        // haven't used any functional programming language in the past. Notice that the general form of the lambda expression
        // is as follows
        // (param1,param2) -> {logic of the lambda here}
        // What is particularly disorienting is that this is the Java code but the types are not declared
        // Our IDE Intellij takes some time to understand that we're actually implementing a lambda function
        // and the statements are valid - as reflected in the red underline that keeps appearing and disappearing as we
        // type

        // Step 4b: Use a set of chained operations (aggregation operations) to test if the candidates are
        // REpublicans or Democrats

        candidateNames.
                //We start with an array list of candidate names, these are sorted in the order that we want
                stream().
                // convert this list to a stream object using the .stream() method
                filter(candidateName ->partyAffiliations.get(candidateName).equals("R")).
                // Now apply the "filter" aggregation to only get an output stream of the candidates we want ie
                        // the republican candidates. Notice how the filter aggregate operation takes in a lambda
                        // which returns either true or false
                map(republicanCandidate -> republicanCandidate + "(" + partyAffiliations.get(republicanCandidate) + ")").
                // At this point after the filter we should have a stream of Republican candidate names - explicitly add
                        // their party affiliations to their names
                forEach(republicanCandidate -> System.out.println(republicanCandidate));
                // Finally we have the output where each candidate name that we have at this point will be printed to screen

        // Ok there is seriously a lot going on here. let's see what each step does. Breaking it down will make it clearer

    }



}
































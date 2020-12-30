package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class OneDayMarketAction {

    // we will use this class to represent the NSE market action on one given day

    // within the class for market action on 1 day, we will create an 'inner' class
    // to represent the market action for 1 stock on this 1 day
    // note that this inner class is declared static , which means that one version of the class
    // exists for the entire outer class. If that did not make too much sense, its fine - just
    // remember that its possible to have an inner class insider an outer class

    public static class OneTickerOneDay{
        // inside this inner class, lets define a load of member variables (all private)
        // holding information for this one stock

        private String m_ticker;
        private String m_series;
        private double m_open;
        private double m_close;
        private double m_high;
        private double m_low;
        private double m_prevClose;


        public OneTickerOneDay(String[]  oneQuote){
            // this is the constructor that we used above - we now need to actually implement it
            // we just went back and generated getters and setters for these member variables
            setM_ticker(oneQuote[0]);
            setM_series(oneQuote[1]);
            setM_open(Double.parseDouble(oneQuote[2]));// we take in a string, need to convert to a Double
            setM_close(Double.parseDouble(oneQuote[3]));// we take in a string, need to convert to a Double
            setM_high(Double.parseDouble(oneQuote[4]));// we take in a string, need to convert to a Double
            setM_low(Double.parseDouble(oneQuote[5]));// we take in a string, need to convert to a Double
            setM_prevClose(Double.parseDouble(oneQuote[7]));// we take in a string, need to convert to a Double
           // notice how the Double.parseDouble function is a handy way of taking in a string and getting a
            // double . Note also how we call this function using 'ClassName.FunctionName' and not
            // 'ObjectName.FunctionName'. this is how static functions are called.

        }


        public String getM_ticker() {
            return m_ticker;
        }

        public void setM_ticker(String m_ticker) {
            this.m_ticker = m_ticker;
        }

        public String getM_series() {
            return m_series;
        }

        public void setM_series(String m_series) {
            this.m_series = m_series;
        }

        public double getM_open() {
            return m_open;
        }

        public void setM_open(double m_open) {
            this.m_open = m_open;
        }

        public double getM_close() {
            return m_close;
        }

        public void setM_close(double m_close) {
            this.m_close = m_close;
        }

        public double getM_high() {
            return m_high;
        }

        public void setM_high(double m_high) {
            this.m_high = m_high;
        }

        public double getM_low() {
            return m_low;
        }

        public void setM_low(double m_low) {
            this.m_low = m_low;
        }

        public double getM_prevClose() {
            return m_prevClose;
        }

        public void setM_prevClose(double m_prevClose) {
            this.m_prevClose = m_prevClose;
        }


        // finally, lets also create a member function that returns the % change in this ticker
        // on this day

        public double getPctChange(){
            if(this.getM_prevClose()!=0){
                return (this.getM_close()-this.getM_prevClose())/this.getM_prevClose();
            }
            // just need to watch against the eventuality of the previous day's closing being 0
            // in which case the division above would have thrown a divide by zero error
            return Double.NaN;
        }

    }

    private Map<String,OneTickerOneDay> mapOfTickers = new HashMap<>();
    // internally , we want an easy way to say 'please give me the market action today for ticker=XYZ'
    // A map - which is a key-value pair associative container - gives us exactly what we need.

    // Cool! Let's get on with how to represent the market action for a day
    // We can always add member variables or member functions
    // to the inner classes as we go along if we need to

    private String m_fileName;
    // what file did this data come from? Will be handy to know

    public OneDayMarketAction(String csvFile){
        // this is the constructor. What this constructor will do is this
        // 1. parse the CSV file (which came from calling UnZipUtility)
        // 2. go through that file line-by-line and create objects of class OneTickerOneDay
        // 3. populate the internal map we just set up

        // 1. Parse the CSV file

        this.m_fileName=csvFile;
        BufferedReader br = null;
        String line = null;
        // define the character we wish to split the lines by (CSV, so use ',')
        String csvSplitBy = ",";
        // define an integer variable so that we can use it to keep track of the line number
        int lineNum = 0;

        try {
            // create a file handle to be able to read the CSV file
            br = new BufferedReader(new FileReader(csvFile));

            // next set up a while loop that will keep reading lines from the file until the file
            // is exhausted
            // here 'line' is the iterator variable, and the loop will keep executing br.readLine
            // and storing the value in the line variable, until that value becomes null
            while ((line=br.readLine())!= null){
                // we want to keep track of the line number to make sure that we skip the header
                lineNum++;
                if(lineNum>1){
                    // if the linenumber is 1, then don't try to create an object - this is merely the
                    // header
                    String[] oneQuote=line.split(csvSplitBy);
                    // this line above will split the line into an array of strings, separated by the character
                    // we specified (here ',')
                    OneDayMarketAction.OneTickerOneDay otod=
                            new OneDayMarketAction.OneTickerOneDay(oneQuote);
                    //Ok this line currently will now compile, we just implemented the constructor
                    if(otod.getM_series().compareTo("EQ")==0){
                        // now btw, we only care about market action in equities, not in more complicated
                        // types of instruments like warrants etc. So let's skip all rows that deal with
                        // securities other than equities.
                        // notice how string comparisions work in Java. Use the '.compareTo' method, and test if the
                        // value returned is 0
                        mapOfTickers.put(oneQuote[0],otod);
                    }
                }
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        finally{
            // this is something new! the finally clause is a way of making sure hat some code gets executed
            // even if an exception is thrown. Here we will use this clause to make sure we close our file
            // handle

            try{
                br.close();
                // as it happens, closing a file itself can throw an exception, so we had to make sure we
                // handle that
            } catch(IOException e){
                    e.printStackTrace();
                }
        }

        // now let's create a member function that returns tickers for that date, sorted in order
        // of market action.
        // How will we do this? Simple :) by using the comparator class we just set up




    }

    public List<OneTickerOneDay> getSortedMovers(){
        // Create an unsorted list
        List<OneTickerOneDay> listOfMarketAction = new ArrayList<>(this.mapOfTickers.values());
        // what did we just do?
        // remember how we have a map(key-value pairs container) of ticker, OneTickerOneDay object
        // we took all of the values (which is a set of OneTickerOneDay objects)
        // and then used that set to create a list. this is pretty normal way of going from a map
        // to a list of values

        Collections.sort(listOfMarketAction,new StockMoveComparator());

        // Magic! The Collections class (an inbuilt Java class) has a method called sort, which takes
        // and a comparator object and uses the comparator to sort the values in that collection.
        // Note how the Collections.sort method is static ( and hence is invoked as Collections.sort ie
        // by classname not objectname)

        // sort that list (remember to tell the sorting code to use an object of the 'StockMoveComparator'
        // class to do so , since that class defines how we would like to rank these objects
        return listOfMarketAction;
        // the collection is now sorted -return this!
    }

    // We forgot to set up the comparator class earlier, let's go ahead and do that now
    // This class was inside the method we were just setting up which is why there was an error.

    public static class StockMoveComparator implements Comparator<OneTickerOneDay> {
        // what does this mean
        // the 'implements' tells us that this class will implement (i.e. adhere to)
        // an interface - namely the Comparator interface
        // this interface, when implemented, signals to the outside world that this class
        // can be used to compare 2 objects of a certain type. Specificially that means
        // that it will have a function called compare, which takes in 2 objects
        // and returns 1 if the first is 'greater than' the second.,
        // returns -1 if the second is 'greater than' the first
        // returns 0 if the 2 objects are 'equal'
        // Question - what does it mean for 2 objecs to be greater than/less than/equal to one
        // another. That's exactly what this class will need to decide :)
        // In this implementation we will judge he classes by the % change in stock that day.
        // Oh, one last but very important note. Check out the <OneTickerOneDay>
        // The stuff between the angle brackets is the 'template parameter'. It means
        // that we could have had Comparators to compare all kinds of objects, but this
        // comparator specifically compares objects of class OneTickerOneDay
        public int compare(OneTickerOneDay t1, OneTickerOneDay t2){
            double pctChange1 = t1.getPctChange();
            double pctChange2 = t2.getPctChange();
            if(pctChange1>pctChange2){
                return 1;
                // t1 moved more than t2 in % terms, so t1 is 'greater than' t2 (by this definition)
            } else if (pctChange1<pctChange2){
                return -1;
                // t1 moved less than t2 in % terms today, so t1 is 'less than' t2 (by this definition)
            }
            return 0;
            // they both moved by the same amount, they are equal (by this definition)
        }
    }




}

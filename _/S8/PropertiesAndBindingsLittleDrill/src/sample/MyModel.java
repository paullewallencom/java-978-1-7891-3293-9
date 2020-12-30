package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyModel {

    private StringProperty simpleString = new SimpleStringProperty();
    // a property variable

    // getter and setter for the underlying value of the property
    public String getSimpleString() {return simpleString.get();}

    public void setSimpleString(String simpleString){
        this.simpleString.set(simpleString);
    }

    // a getter for the property itself
    public StringProperty SimpleString() {return simpleString;}





}

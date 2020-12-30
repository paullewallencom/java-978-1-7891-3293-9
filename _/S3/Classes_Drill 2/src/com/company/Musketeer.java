package com.company;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class Musketeer {

    // Cool! We just created our first class, called 'Musketeer'. Note that the class is marked
    // 'public', which means that it can be created and used by code outside this class.
    // In contrast, had it been marked 'private', it would not have been so easily and freely
    // create-able

    // Also- note how the filename and the classname match. Musketeer.java is the file that contains
    // the class Musketeer

    // Ok, let's create a few member variables.


    private String m_name;
    private int m_age;

    // note that the private means that the member variables will not be directly accessible outside
    // this class. We will need to create member functions in order to read and write the values of these
    // member variables

    // OK - let's create two constructors
    public Musketeer(String name , int age){
        // note again the 'public', which implies that this constructor can be called outside this class.
        this.m_age=age;
        this.setName(name);

        // note how the 'this.' is used to disambiguate member variables of this calss.
        // for instance, had the arguments been named 'm_name' and 'm_age', instead of
        // 'name' and 'age', a statement like 'this.m_age=m_age' would have taken the value of the
        // function argument 'm_age' and assigned to the member variable
        // 'this.m_age'. That's when the 'this.' is really important. Here in the usage above
        // it's merely optional
    }
    // end of the constructor that takes in both name and age

    // Create a default age for all musketeers. This is the statistical average, and is a 'static' variable
    // which means that it is shared across all objects of the class

    public static final int S_DEFAULT_AGE=35;

    // note the keyword 'final' which means that this variable can't be changed once it has been
    // initialized.

    // OK - let's create the second constructor, this one takes in only the name, and assigns a
    // default age.

    public Musketeer(String name) {
        this.setName(name);
        this.m_age=S_DEFAULT_AGE;

        // since the constructor took in only the name (i.e. the calling code does not have an age
        // that it wishes to specify) use the static value S_DEFAULT_AGE instead
    }

    public static final String S_DEFAULT_NAME="Unknown Soldier";
    // note the 'static' which means that this default name apply to all objects of the Musketeer class

    public Musketeer(){
        // no argument constructor , populates all mmber variables
        this.setAge(S_DEFAULT_AGE);
        this.setName(S_DEFAULT_NAME);
    }


    // Hmmm.. cool.. we are almost done with our class - but - in order for the name and age to be available
    // for use outside this class, we need member functions that will allow outside
    // code ot read or write these. These functions are called 'getters' and 'setters'

    public void setAge(int age){
        this.m_age=age;
    }

    public int getAge()
    {
        return this.m_age;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }


    // BTW , IntelliJ is an awesome IDE because it allows a UI way to create getters and setters.































}

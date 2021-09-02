package com.example.students.Domain;

public class Student {

    // Is it better to set as public or use getters?
    public String fname;
    public String lname;

    public Student(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /*
    public void setFname(String fname) { this.fname = fname; }
    public void setLname(String lname) { this.lname = lname; }

    public String getFname() { return fname; }
    public String getLname() { return lname; }
    */

}

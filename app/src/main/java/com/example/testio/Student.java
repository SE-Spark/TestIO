package com.example.testio;

public class Student {
    String code;
    String StudentName;
    String ParentName;
    String level;
    String ParentPhone;
    //getters
    public String getCode(){return code;}
    public String getStudentName(){return StudentName;}
    public String getParentName(){return ParentName;}
    public String getLevel(){return level;}
    public String getParentPhone() {return ParentPhone;}
    //setters
   public void setCode(String code) {this.code = code;}
   public void setStudentName(String studentName) { this.StudentName = studentName; }
   public void setLevel(String level) { this.level = level;}
   public void setParentName(String ParentName){this.ParentName = ParentName;}
    public void setParentPhone(String parentPhone) {ParentPhone = parentPhone;}
    //constructor
    public Student(String code, String StudentName, String ParentName, String level, String ParentPhone){
        this.code = code;
        this.StudentName = StudentName;
        this.ParentName = ParentName;
        this.level=level;
        this.ParentPhone = ParentPhone;
    }
    public Student(){

    }
}

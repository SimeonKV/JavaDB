package entities;

import javax.persistence.*;

@Entity
@Table(name = "students")
@DiscriminatorValue(value = "student")
public class Student extends Person {

    private int grade;

    public Student(){
    }

    public Student(  int grade,String fName,String lName) {
       super(fName,lName);
        this.grade = grade;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

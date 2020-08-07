package entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@DiscriminatorValue(value = "teacher")
public class Teacher extends Person {

    private int degree;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, int degree) {
        super(firstName,lastName);
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}

package enteties;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id",referencedColumnName = "id")
    private Instructor instructor;

    public Course(){}
    public Course(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString(){
        return this.getName();
    }
}

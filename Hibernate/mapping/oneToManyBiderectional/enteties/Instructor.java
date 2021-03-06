package enteties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String email;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id",referencedColumnName = "id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "instructor", cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Course> courses;

    public Instructor(){}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        courses.forEach(course -> course.setInstructor(this));
    }

    public void addCourse(Course course){
        if(this.courses == null){
            this.courses = new ArrayList<>();
        }
        this.courses.add(course);
        course.setInstructor(this);

    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%d %s %s with %s",getId(),getFirstName(),getLastName(),getEmail()))
                .append(System.lineSeparator());
                //.append(instructorDetail.toString());
        return builder.toString();
    }
}

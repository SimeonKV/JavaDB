package entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "questions")
public class Question {
    private long id;
    private String questionName;
    private List<Answer> answers;

    public Question(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "question_name")
    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }


    @ManyToMany(targetEntity = Answer.class,cascade = CascadeType.ALL)
    @JoinTable(name = "questons_answers",
    joinColumns = @JoinColumn(name = "question_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "answer_id",referencedColumnName = "id"))
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

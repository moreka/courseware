package models.user;

import javax.persistence.*;

@Entity
public class TeachingAssistance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public Student student;

    public String course;

}

package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Class used to specify a Tutor
 *
 * @see JPAEntity
 */
@Entity
@Table(name="tutor")
public class Tutor extends JPAEntity<Integer>{

    @Valid
    @NotNull( message = "NotNull.Tutor.student" )
    @OneToOne(fetch= FetchType.EAGER , cascade = {CascadeType.REFRESH, CascadeType.MERGE} )
    @JoinColumn(name="student_id")
    private Student student;

    @Valid
    @NotNull( message = "NotNull.Tutor.course")
    @ManyToMany (fetch= FetchType.EAGER , cascade = {CascadeType.REFRESH, CascadeType.MERGE} )
    @JoinTable( name="tutor_course",
                joinColumns={@JoinColumn(name="tutor_id", referencedColumnName="id")},
                inverseJoinColumns={@JoinColumn(name="course_id", referencedColumnName="id")})
    private Set<Course> courses;

    @Valid
    @NotNull(message = "NotNull.Tutor.lessons")
    @OneToMany(mappedBy="tutor", fetch= FetchType.EAGER , cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE} )
    private Set<Lesson> lessons;

    /**
     * Default empty constructor for JPA Entities
     */
    public Tutor(){}

    /**
     * Constructor for a tutor entity
     *
     * @param student   The student information of the tutor
     * @param courses   The set of courses the tutor may tutor
     */
    public Tutor(Student student, Set<Course> courses){
        this.student=student;
        this.courses=courses;
    }

    /**
     * Gets the student information of the tutor
     *
     * @return The student object
     * @see Student
     */
    public Student getStudent() {
        return student;
    }



    /**
     * Gets the courses that the tutor may tutor
     *
     * @return The set containing all the course objects
     * @see Course
     * @see Set
     */
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the student information of the tutor
     *
     * @param  student The student information
     * @see Student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the courses that the tutor may tutor
     *
     * @param courses The set containing all the course objects
     * @see Course
     * @see Set
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}

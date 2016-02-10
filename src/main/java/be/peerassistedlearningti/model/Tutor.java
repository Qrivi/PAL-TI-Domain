package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

/**
 * Class used to specify a Tutor
 *
 * @see JPAEntity
 */
public class Tutor extends JPAEntity<Integer>{

    @Valid
    @NotEmpty( message = "NotEmpty.Tutor.student" )
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="student_tutor")
    private Student student;

    @Valid
    @NotEmpty( message = "NotEmpty.Tutor.course")
    @ManyToMany
    @JoinTable( name="tutor_course",
                joinColumns={@JoinColumn(name="tutor_id", referencedColumnName="id")},
                inverseJoinColumns={@JoinColumn(name="course_id", referencedColumnName="id")})
    private Set<Course> courses;

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
     * @return The set containing all the course objects
     * @see Course
     * @see Set
     */
    public Set<Course> getCourses() {
        return courses;
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

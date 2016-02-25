package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used to specify a Tutor
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "tutor" )
public class Tutor extends JPAEntity<Integer>
{

    @Valid
    @NotNull( message = "{NotNull.Tutor.student}" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Tutor.course}" )
    @ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinTable( name = "tutor_course",
            joinColumns = { @JoinColumn( name = "tutor_id", referencedColumnName = "id" ) },
            inverseJoinColumns = { @JoinColumn( name = "course_id", referencedColumnName = "id" ) } )
    private Set<Course> courses;

    @Valid
    @OneToMany( mappedBy = "tutor", fetch = FetchType.EAGER )
    private Set<Lesson> lessons;

    /**
     * Default empty constructor for a tutor entity
     */
    public Tutor() {}

    /**
     * Constructor for a tutor entity
     *
     * @param student The student information of the tutor
     * @param courses The set of courses the tutor may tutor
     */
    public Tutor( Student student, Set<Course> courses )
    {
        this.student = student;
        this.courses = courses;
    }

    /**
     * Adds a given course to a tutor
     *
     * @param course The given course to be added to the set
     * @return true if this set did not already contain the specified course
     */
    public boolean addCourse( Course course )
    {
        return courses.add( course );
    }

    /**
     * Removes a given course of a tutor
     *
     * @param course The given course to be removed
     * @return true if this set contained the specified course
     */
    public boolean removeCourse( Course course )
    {
        return courses.remove( course );
    }

    /**
     * Adds a given lesson to a tutor
     *
     * @param lesson The given lesson to be added
     * @return true if this set did not already contain the specified lesson
     */
    public boolean addLesson( Lesson lesson )
    {
        return lessons.add( lesson );
    }

    /**
     * Removes a given lesson of a tutor
     *
     * @param lesson The given lesson to be removed
     * @return true if this set contained the specified lesson
     */
    public boolean removeLesson( Lesson lesson )
    {
        return lessons.remove( lesson );
    }


    /**
     * Gets the student information of the tutor
     *
     * @return The student object
     * @see Student
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * Sets the student information of the tutor
     *
     * @param student The student information
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
    public Set<Course> getCourses()
    {
        return courses;
    }

    /**
     * Gets the courses that the tutor may tutor
     *
     * @param courses The set containing all the course objects
     * @see Course
     * @see Set
     */
    public void setCourses(Set<Course> courses)
    {
        this.courses = courses;
    }

    /**
     * Gets the set of lessons of the tutor
     *
     * @return The set of lessons
     * @see Lesson
     * @see Set
     */
    public Set<Lesson> getLessons()
    {
        return lessons;
    }

    /**
     * Sets the set of lessons of the tutor
     *
     * @param lessons The set of lessons
     * @see Lesson
     * @see Set
     */
    public void setLessons( Set<Lesson> lessons )
    {
        this.lessons = lessons;
    }

    /**
     * @return The past lessons of the tutor
     */
    public Set<Lesson> getPastLessons() {
        Set<Lesson> result = new HashSet<Lesson>();
        for (Lesson lesson : lessons) {
            if (lesson.getDate()
                    .before(new Date())) {
                result.add(lesson);
            }
        }
        return result;
    }

    /**
     * @return The upcoming lessons of the tutor
     */
    public Set<Lesson> getUpcomingLessons() {
        Set<Lesson> result = new HashSet<Lesson>();
        for (Lesson lesson : lessons) {
            if (lesson.getDate()
                    .after(new Date())) {
                result.add(lesson);
            }
        }
        return result;
    }
}

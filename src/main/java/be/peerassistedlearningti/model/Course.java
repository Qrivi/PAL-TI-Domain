package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * Class used to specify a Course
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "course" )
public class Course extends JPAEntity<Integer>
{

    @NotEmpty( message = "{NotEmpty.Course.code}" )
    @Column( name = "code", unique = true, nullable = false )
    private String code;

    @NotEmpty( message = "{NotEmpty.Course.name}" )
    @Column( name = "name", unique = true, nullable = false )
    private String name;

    @NotEmpty( message = "{NotEmpty.Course.shortName}" )
    @Column( name = "short_name", unique = true, nullable = false )
    private String shortName;

    @ManyToMany( mappedBy = "courses", cascade = { CascadeType.REFRESH , CascadeType.MERGE , CascadeType.REMOVE } )
    private Set<Tutor> tutors;

    @NotEmpty(message = "{NotEmpty.Course.curriculum}")
    @Column(name = "curriculum", nullable = false)
    private String curriculum;

    @Min(value = 1, message = "{Min.Course.year}")
    @Column(name = "year", nullable = false)
    private int year;

    @ManyToMany( mappedBy = "courses", cascade = { CascadeType.REFRESH , CascadeType.MERGE , CascadeType.REMOVE } )
    private Set<Student> subscribers;

    /**
     * Default constructor for Course
     */
    public Course() {}

    /**
     * Constructor for Course
     *
     * @param code      The code of the course
     * @param name      The full name of the course
     * @param shortName The short name of the course
     */
    public Course( String code, String name, String shortName, String curriculum, int year)
    {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.curriculum = curriculum;
        this.year = year;
    }

    /**
     * Sets the code of the course
     *
     * @param code The new code of the course
     */
    public void setCode( String code )
    {
        this.code = code;
    }

    /**
     * Sets the full name of the course
     *
     * @param name The new full name of the course
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the short name of the course
     *
     * @param shortName The new short name of the course
     */
    public void setShortName( String shortName )
    {
        this.shortName = shortName;
    }

    /**
     * Sets the curriculum of the course
     *
     * @param curriculum The new curriculum of the course
     */
    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * Sets the year of the course
     *
     * @param year The new year of the course
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return The code of the course
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @return The full name of the course
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The short name of the course
     */
    public String getShortName()
    {
        return shortName;
    }

    /**
     * Gets the available tutors for a course
     *
     * @return The set with available tutors or null if none
     */
    public Set<Tutor> getTutors()
    {
        return tutors;
    }

    /**
     * @return The curriculum of the course
     */
    public String getCurriculum() {
        return curriculum;
    }

    /**
     * @return The year of the course
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the available tutors for a course
     *
     * @return The set with available tutors or null if none
     */
    public Set<Student> getSubscribers()
    {
        return subscribers;
    }

    public void subscribe(Student student) {
        subscribers.add(student);
    }

    public void unsubscribe(Student student) {
        subscribers.remove(student);
    }

    /**
     * Equals functionality for a course
     *
     * @param o The object to check if it is equal
     * @return if the object equals the course, courses are equals when there code is equal
     */
    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        Course course = (Course) o;

        return code.equals( course.code );
    }

    /**
     * Hashcode functionality for a course
     *
     * @return the hashcode of the code
     */
    @Override
    public int hashCode()
    {
        return ( code != null ? code.hashCode() : 0 );
    }
}

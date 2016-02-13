package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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
    public Course( String code, String name, String shortName )
    {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
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
     * @return The set with available tutors or null if non
     */
    public Set<Tutor> getTutors()
    {
        return tutors;
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

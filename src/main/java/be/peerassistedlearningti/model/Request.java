package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Class used to specify a Request
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "request" )
public class Request extends JPAEntity<Integer>
{

    @Min( value = 0, message = "{Min.Request.upvotes}" )
    @Column( name = "upvotes" )
    private int upvotes;

    @NotNull(message = "{NotNull.Request.title}")
    @Size( min = 3, max = 50, message = "{Size.Request.text}" )
    @Column( name = "title" )
    private String title;

    @NotNull( message = "{NotNull.Request.description}" )
    @Size( min = 10, max = 300, message = "{Size.Request.text}" )
    @Column( name = "description" )
    private String description;

    @Valid
    @NotNull( message = "{NotNull.Request.course}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Valid
    @NotNull( message = "{NotNull.Request.student}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @NotNull( message = "{NotNull.Request.creationDate}" )
    @Column( name = "creation_date" )
    private Date creationDate;

    /**
     * Default constructor for Request
     */
    public Request() {}

    /**
     * Constructor for Request
     *
     * @param upvotes     The amount of upvotes of the request
     * @param title       The title of the request
     * @param description The description of the request
     * @param course      The course of the request
     * @param student     The student of the request
     */
    public Request( int upvotes, String title, String description, Course course, Student student )
    {
        this.upvotes = upvotes;
        this.title = title;
        this.description = description;
        this.course = course;
        this.student = student;
        this.creationDate = new Date();
    }

    /**
     * Constructor for Request
     *
     * @param upvotes      The amount of upvotes of the request
     * @param title       The title of the request
     * @param description  The description of the request
     * @param course       The course of the request
     * @param student      The student of the request
     * @param creationDate The creation date of the request
     */
    public Request( int upvotes, String title, String description, Course course, Student student, Date creationDate )
    {
        this( upvotes, title, description, course, student );
        this.creationDate = creationDate;
    }

    /**
     * @return the upvotes of the request
     */
    public int getUpvotes()
    {
        return upvotes;
    }

    /**
     * Sets the upvotes of the request
     *
     * @param upvotes the new amount of upvotes
     */
    public void setUpvotes( int upvotes )
    {
        this.upvotes = upvotes;
    }

    /**
     * @return the title of the request
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the request
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of the request
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description of the request
     *
     * @param description the new description of the request
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @return the course of the request
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * Sets the course of the request
     *
     * @param course the new course of the request
     */
    public void setCourse( Course course )
    {
        this.course = course;
    }

    /**
     * @return the student of the request
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * Sets the student of the request
     *
     * @param student the new student of the request
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

    /**
     * @return the creation date of the request
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Sets the creation date of the request
     *
     * @param creationDate the new creation date of the request
     */
    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }
}

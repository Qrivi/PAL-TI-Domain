package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class used to specify an Application of student as tutor for a course
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "application" )
public class Application extends JPAEntity<Integer>
{
    @Valid
    @NotNull( message = "{NotNull.Application.student}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Application.course}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Lob
    @NotEmpty( message = "{NotEmpty.Application.screenshot}" )
    @Column( name = "screenshot", nullable = false )
    private byte[] screenshot;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Application.state}" )
    @Column( name = "state", nullable = false )
    private ApplicationState state;

    @NotNull( message = "{NotNull.Application.beginDate}" )
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "begin_date", nullable = false )
    private Date beginDate;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "end_date", nullable = true )
    private Date endDate;

    /**
     * Default constructor
     */
    public Application() {}

    /**
     * All-parameter constructor for Application
     *
     * @param student    The student of the application
     * @param course     The course of the application
     * @param screenshot The screenshot of the application
     * @param state      The state of the application
     * @param beginDate  The begin date of the application
     * @param endDate    The end date of the application
     */
    public Application( Student student, Course course, byte[] screenshot, ApplicationState state, Date beginDate, Date endDate )
    {
        this.student = student;
        this.course = course;
        this.screenshot = screenshot;
        this.state = state;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for a pending Application
     *
     * @param student    The student of the application
     * @param course     The course of the application
     * @param screenshot The screenshot of the application
     */
    public Application( Student student, Course course, byte[] screenshot )
    {
        this.student = student;
        this.course = course;
        this.screenshot = screenshot;
        this.beginDate = new Date();
        this.state = ApplicationState.PENDING;
    }

    /**
     * Accepts the application
     */
    public void approve()
    {
        this.state = ApplicationState.APPROVED;
        this.endDate = new Date();
    }

    /**
     * Declines the application
     */
    public void reject()
    {
        this.state = ApplicationState.REJECTED;
        this.endDate = new Date();
    }

    /**
     * Sets the student of the application
     *
     * @param student The student of the application
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

    /**
     * Sets the course of the application
     *
     * @param course The course of the application
     */
    public void setCourse( Course course )
    {
        this.course = course;
    }

    /**
     * Sets the screenshot URL of the application
     *
     * @param screenshot The screenshot of the application
     */
    public void setScreenshot( byte[] screenshot )
    {
        this.screenshot = screenshot;
    }

    /**
     * Sets the state of the application
     *
     * @param state The state of the application
     */
    public void setState( ApplicationState state )
    {
        this.state = state;
    }

    /**
     * Sets the begin date of the application
     *
     * @param beginDate The begin date of the application
     */
    public void setBeginDate( Date beginDate )
    {
        this.beginDate = beginDate;
    }

    /**
     * Sets the end date of the application
     *
     * @param endDate The end date of the application
     */
    public void setEndDate( Date endDate )
    {
        this.endDate = endDate;
    }

    /**
     * @return The student of the application
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * @return The course of the application
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * @return The screenshot of the application
     */
    public byte[] getScreenshot()
    {
        return screenshot;
    }

    /**
     * @return The state of the application
     */
    public ApplicationState getState()
    {
        return state;
    }

    /**
     * @return The begin date of the application
     */
    public Date getBeginDate()
    {
        return beginDate;
    }

    /**
     * @return The end date of the application
     */
    public Date getEndDate()
    {
        return endDate;
    }
}

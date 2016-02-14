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
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Application.course}" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @NotEmpty( message = "{NotEmpty.Application.screenshotURL}" )
    @Column( name = "screenshot_url", nullable = false )
    private String screenshotURL;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Application.state}" )
    @Column( name = "state", nullable = false )
    private ApplicationState state;

    @NotNull( message = "{NotNull.Application.beginDate}" )
    @Column( name = "begin_date", nullable = false )
    private Date beginDate;

    @Column( name = "end_date", nullable = true )
    private Date endDate;

    /**
     * Default constructor
     */
    public Application() {}

    /**
     * All-parameter constructor for Application
     *
     * @param student       The student of the application
     * @param course        The course of the application
     * @param screenshotURL The screenshot url of the application
     * @param state         The state of the application
     * @param beginDate     The begin date of the application
     * @param endDate       The end date of the application
     */
    public Application( Student student, Course course, String screenshotURL, ApplicationState state, Date beginDate, Date endDate )
    {
        this.student = student;
        this.course = course;
        this.screenshotURL = screenshotURL;
        this.state = state;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for a pending Application
     *
     * @param student       The student of the application
     * @param course        The course of the application
     * @param screenshotURL The screenshot url of the application
     */
    public Application( Student student, Course course, String screenshotURL )
    {
        this.student = student;
        this.course = course;
        this.screenshotURL = screenshotURL;
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
     * @return The screenshot URL of the application
     */
    public String getScreenshotURL()
    {
        return screenshotURL;
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
     * @param screenshotURL The screenshot URL of the application
     */
    public void setScreenshotURL( String screenshotURL )
    {
        this.screenshotURL = screenshotURL;
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
}

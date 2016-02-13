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
public class Application extends JPAEntity<Integer> {
    @Valid
    @NotNull(message = "{NotNull.Application.student}")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH , CascadeType.MERGE })
    @JoinColumn(name= "student_id")
    private Student student;

    @Valid
    @NotNull(message = "{NotNull.Application.course}")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH , CascadeType.MERGE })
    @JoinColumn(name = "course_id")
    private Course course;

    @NotEmpty(message = "{NotEmpty.Application.screenshotURL}")
    @Column(name = "screenshot_url", nullable = false)
    private String screenshotURL;

    @Column(name = "pending")
    private boolean pending;

    @NotNull(message = "{NotNull.Application.beginDate}")
    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    /**
     * Default constructor
     */
    public Application(){}

    /**
     * All-parameter constructor for Application
     *
     * @param student
     * @param course
     * @param screenshotURL
     * @param pending
     * @param beginDate
     * @param endDate
     */
    public Application(Student student, Course course, String screenshotURL, boolean pending, Date beginDate, Date endDate) {
        this.student = student;
        this.course = course;
        this.screenshotURL = screenshotURL;
        this.pending = pending;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for a pending Application
     *
     * @param student
     * @param course
     * @param screenshotURL
     */
    public Application(Student student, Course course, String screenshotURL) {
        this.student = student;
        this.course = course;
        this.screenshotURL = screenshotURL;
        this.beginDate = new Date();
        this.pending = true;
    }

    /**
     * Accepts the application from the current date
     */
    public void accept() {
        this.pending = false;
        this.beginDate = new Date();
    }

    /**
     * Accepts the application from the current date to the specified end date
     *
     * @param endDate
     */
    public void accept(Date endDate){
        accept();
        setEndDate(endDate);
    }

    /**
     * Gets the student of the application
     *
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Gets the course of the application
     *
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Gets the screenshot URL of the application
     *
     * @return screenshotURL
     */
    public String getScreenshotURL() {
        return screenshotURL;
    }

    /**
     * Gets if the application is pending
     *
     * @return pending
     */
    public boolean isPending() {
        return pending;
    }

    /**
     * Gets the begin date of the application
     *
     * @return begin date
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Gets the end date of the application
     *
     * @return end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the student of the application
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Sets the course of the application
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Sets the screenshot URL of the application
     *
     * @param screenshotURL
     */
    public void setScreenshotURL(String screenshotURL) {
        this.screenshotURL = screenshotURL;
    }

    /**
     * Sets if the application is pending
     *
     * @param pending
     */
    public void setPending(boolean pending) {
        this.pending = pending;
    }

    /**
     * Sets the begin date of the application
     *
     * @param beginDate
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Sets the end date of the application
     *
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

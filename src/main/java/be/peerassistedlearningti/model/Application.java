/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Application.course}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "course_id" )
    private Course course;

    @NotNull( message = "{NotNull.Application.screenshot}" )
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @LazyToOne( value = LazyToOneOption.NO_PROXY )
    private Image screenshot;

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
        this.screenshot = ( screenshot != null ) ? new Image( screenshot, beginDate ) : null;
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
        this.beginDate = new Date();
        this.screenshot = ( screenshot != null ) ? new Image( screenshot, beginDate ) : null;
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
    public void setScreenshot( Image screenshot )
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
    public Image getScreenshot()
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

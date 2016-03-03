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

package be.peerassistedlearning.model;

import be.peerassistedlearning.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Course.curriculum}" )
    @Column( name = "curriculum", nullable = false )
    private Curriculum curriculum;

    @Min( value = 1, message = "{Min.Course.year}" )
    @Column( name = "year", nullable = false )
    private int year;

    @Valid
    @ManyToMany( mappedBy = "courses" )
    private Set<Tutor> tutors;

    @Valid
    @ManyToMany( mappedBy = "subscriptions", fetch = FetchType.EAGER )
    private Set<Student> subscribers;

    @Valid
    @OneToMany( mappedBy = "course", orphanRemoval = true )
    private Set<Request> requests;

    /**
     * Default constructor for Course
     */
    public Course() {}

    /**
     * Constructor for Course
     *
     * @param code       The code of the course
     * @param name       The full name of the course
     * @param shortName  The short name of the course
     * @param curriculum The curriculum of the course
     * @param year       The year of the course
     */
    public Course( String code, String name, String shortName, Curriculum curriculum, int year )
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
    public void setCurriculum( Curriculum curriculum )
    {
        this.curriculum = curriculum;
    }

    /**
     * Sets the year of the course
     *
     * @param year The new year of the course
     */
    public void setYear( int year )
    {
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
     * @return The curriculum of the course
     */
    public Curriculum getCurriculum()
    {
        return curriculum;
    }

    /**
     * @return The year of the course
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @return The tutors for the course
     */
    public Set<Tutor> getTutors()
    {
        return tutors;
    }

    /**
     * @return The requests for the course
     */
    public Set<Request> getRequests()
    {
        return requests;
    }

    /**
     * @return The subscribed students for this course
     */
    public Set<Student> getSubscribers()
    {
        return subscribers;
    }
}

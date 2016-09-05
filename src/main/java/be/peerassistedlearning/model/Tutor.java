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

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Class used to specify a Tutor
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "tutor" )
public class Tutor extends JPAEntity<Integer>{

    @Valid
    @NotNull( message = "{NotNull.Tutor.student}" )
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Tutor.course}" )
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "tutor_course",
            joinColumns = {@JoinColumn( name = "tutor_id", referencedColumnName = "id" )},
            inverseJoinColumns = {@JoinColumn( name = "course_id", referencedColumnName = "id" )} )
    private Set<Course> courses;

    @Valid
    @OneToMany( mappedBy = "tutor", orphanRemoval = true )
    private Set<Lesson> lessons;

    /**
     * Default empty constructor for a tutor entity
     */
    public Tutor(){
    }

    /**
     * Constructor for a tutor entity
     *
     * @param student The student information of the tutor
     * @param courses The set of courses the tutor may tutor
     */
    public Tutor( Student student, Set<Course> courses ){
        this.student = student;
        this.courses = courses;
    }

    /**
     * Adds a given course to a tutor
     *
     * @param course The given course to be added to the set
     * @return true if this set did not already contain the specified course
     */
    public boolean addCourse( Course course ){
        return courses.add( course );
    }

    /**
     * Removes a given course of a tutor
     *
     * @param course The given course to be removed
     * @return true if this set contained the specified course
     */
    public boolean removeCourse( Course course ){
        return courses.remove( course );
    }

    /**
     * Gets the student information of the tutor
     *
     * @return The student object
     * @see Student
     */
    public Student getStudent(){
        return student;
    }

    /**
     * Sets the student information of the tutor
     *
     * @param student The student information
     * @see Student
     */
    public void setStudent( Student student ){
        this.student = student;
    }

    /**
     * Gets the courses that the tutor may tutor
     *
     * @return The set containing all the course objects
     * @see Course
     * @see Set
     */
    public Set<Course> getCourses(){
        return courses;
    }

    /**
     * Gets the courses that the tutor may tutor
     *
     * @param courses The set containing all the course objects
     * @see Course
     * @see Set
     */
    public void setCourses( Set<Course> courses ){
        this.courses = courses;
    }

    /**
     * Gets the lessons of the tutor
     *
     * @return The set containing all the lesson objects
     * @see Lesson
     * @see Set
     */
    public Set<Lesson> getLessons(){
        return lessons;
    }
}

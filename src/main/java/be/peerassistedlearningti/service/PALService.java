package be.peerassistedlearningti.service;

import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;

/**
 * Interface used to determine the backend functionalities
 *
 * @see PALService
 */
public interface PALService
{

    /**
     * Adds a course to the database
     *
     * @param course The course to be added to the database
     */
    void addCourse( Course course );

    /**
     * Removes the specified course from the database
     *
     * @param course The course to be removed from the database
     */
    void removeCourse( Course course );

    /**
     * Gets the course with the specified id
     *
     * @param id The id of the course
     * @return The course with the specified id
     */
    Course getCourseById( int id );

    /**
     * Adds a student to the database
     *
     * @param student The student to be added to the database
     */
    void addStudent( Student student );

    /**
     * Removes the specified student from the database
     *
     * @param student The student to be removed from the database
     */
    void removeStudent( Student student );

    /**
     * Gets the student with the specified id
     *
     * @param id The id of the student
     * @return The student with the specified id
     */
    Student getStudentById( int id );

    /**
     * Gets the student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    Student getStudentByEmail( String email );

}

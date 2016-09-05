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

package be.peerassistedlearning.repository;

import be.peerassistedlearning.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Interface for Lesson specific database operations
 *
 * @see Lesson
 * @see CrudRepository
 */
@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer>{
    /**
     * Gets a lesson from a student
     *
     * @param id      The id of the lesson
     * @param student The student to get the lessons from
     * @return the lessons of that student
     */
    @Query( "SELECT l FROM Lesson l WHERE l.id = :id AND :student MEMBER OF l.bookings" )
    Lesson findByIdForStudent( @Param( "id" ) Integer id, @Param( "student" ) Student student );

    /**
     * Gets lessons filtered by course
     *
     * @param course to be filtered on
     * @return the lessons of that course
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course ORDER BY l.date DESC" )
    Collection<Lesson> findByCourse( @Param( "course" ) Course course );

    /**
     * Gets lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons of that tutor
     */
    @Query( "SELECT l FROM Lesson l WHERE l.tutor = :tutor ORDER BY l.date DESC" )
    Collection<Lesson> findByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets upcoming lessons available for the student
     *
     * @param curriculum The curriculum to get the available lessons from
     * @return The lessons available for the student
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course.curriculum = :curriculum ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByCurriculum( @Param( "curriculum" ) Curriculum curriculum );

    /**
     * Gets the upcoming lessons from a course
     *
     * @param course The course to get the upcoming lessons from
     * @return A collection containing the upcoming lessons from that course
     */
    @Query( "SELECT l FROM Lesson l WHERE l.course = :course and l.date > current_timestamp ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByCourse( @Param( "course" ) Course course );

    /**
     * Gets the past lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the past lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date < current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findPastByStudent( @Param( "student" ) Student student );

    /**
     * Gets the past bookings for the student from the offset with the limit as size
     *
     * @param student  The student to get the bookings from
     * @param pageable The page to select from
     * @return The past bookings of the student from the offset with size limit
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date < current_timestamp ORDER BY l.date ASC" )
    List<Lesson> findPastByStudent( @Param( "student" ) Student student, Pageable pageable );

    /**
     * Gets the future lessons for a given student
     *
     * @param student The student to get the lessons from
     * @return The list with the future lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :student MEMBER OF l.bookings AND l.date > current_timestamp ORDER BY l.date DESC" )
    Collection<Lesson> findUpcomingByStudent( @Param( "student" ) Student student );

    /**
     * Gets the past lessons for a given tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return The list with the past lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date < current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findPastByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets past lessons from the specified tutor from the offset with the limit as size
     *
     * @param tutor    The tutor to get the lessons from
     * @param pageable The page to select from
     * @return the lessons in the past of that tutor from the offset with the limit as size
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date < current_timestamp ORDER BY l.date ASC" )
    List<Lesson> findPastByTutor( @Param( "tutor" ) Tutor tutor, Pageable pageable );

    /**
     * Gets the future lessons for a given tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return The list with the future lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE :tutor = l.tutor AND l.date > current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findUpcomingByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets all the upcoming lessons
     *
     * @return A collection containing the upcoming lessons
     */
    @Query( "SELECT l FROM Lesson l WHERE l.date > current_timestamp ORDER BY l.date ASC" )
    Collection<Lesson> findUpcoming();

    /**
     * Checks if the specified student has the specified booking
     *
     * @param student The student to check if it has the booking
     * @param lesson  The booking to check if it has the student
     * @return If the student has the booking
     */
    @Query( "SELECT l FROM Lesson l WHERE l = :lesson AND :student MEMBER OF l.bookings" )
    Lesson hasBooking( @Param( "student" ) Student student, @Param( "lesson" ) Lesson lesson );
}

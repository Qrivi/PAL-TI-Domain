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

import be.peerassistedlearning.model.Course;
import be.peerassistedlearning.model.Request;
import be.peerassistedlearning.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * Interface for Request specific database operations
 *
 * @see Request
 * @see CrudRepository
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Integer>
{
    /**
     * Gets all the requests that aren't associated with a lesson
     *
     * @return A collection containing all the requests
     */
    @Query( "SELECT r FROM Request r LEFT JOIN r.lesson l WHERE l IS NULL" )
    Collection<Request> findAllWithoutLesson();

    /**
     * Gets all the requests from the specified course
     *
     * @param course The course to get the requests from
     * @return A collection containing all the requests from the specified course
     */
    @Query( "SELECT r FROM Request r WHERE :course = r.course" )
    Collection<Request> findAll( @Param( "course" ) Course course );

    /**
     * Gets all the requests from the specified course who don't have a lesson
     *
     * @param course The course to get the requests from
     * @return A collection containing all the requests from the specified course
     */
    @Query( "SELECT r FROM Request r LEFT JOIN r.lesson l WHERE l IS NULL AND :course = r.course" )
    Collection<Request> findAllWithoutLesson( @Param( "course" ) Course course );

    /**
     * Gets all the requests from the specified student
     *
     * @param student The student to get the requests from
     * @return A collection containing all the requests from the specified student
     */
    @Query( "SELECT r FROM Request r WHERE :student MEMBER OF r.upvotes" )
    Collection<Request> findAll( @Param( "student" ) Student student );

    /**
     * Gets all the requests filtered by given set of courses
     *
     * @param courses The set of courses to filter the requests on
     * @return A collection containing all the requests filtered by given courses
     */
    @Query( "SELECT r FROM Request r WHERE r.course IN :courses" )
    Collection<Request> findAllRequests( @Param( "courses" ) Set<Course> courses );
}

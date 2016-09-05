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
import be.peerassistedlearning.model.Curriculum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for Course specific database operations
 *
 * @see Course
 * @see CrudRepository
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{
    /**
     * Gets a course with the specified code
     *
     * @param code The code of the course
     * @return The course with the specified code
     */
    @Query( "SELECT c FROM Course c WHERE c.code = :code" )
    Course findByCode( @Param( "code" ) String code );

    /**
     * Gets a course with the specified name
     *
     * @param name The name of the course
     * @return The course with the specified name
     */
    @Query( "SELECT c FROM Course c WHERE c.name = :name" )
    Course findByName( @Param( "name" ) String name );

    /**
     * Gets a course with the specified short name
     *
     * @param shortName The short name of the course
     * @return The course with the specified short name
     */
    @Query( "SELECT c FROM Course c WHERE c.shortName = :shortName" )
    Course findByShortName( @Param( "shortName" ) String shortName );

    /**
     * Gets the top subscribed courses
     *
     * @param pageable The page to select from
     * @return The top subscribed courses
     */
    @Query( "SELECT c FROM Course c ORDER BY c.subscribers.size DESC" )
    List<Course> findTopSubscribed( Pageable pageable );

    /**
     * Gets all the courses available for the specified student
     *
     * @param curriculum The curriculum to get the available courses from
     * @return A collection containing all the courses available for the specified student
     */
    @Query( "SELECT c FROM Course c WHERE c.curriculum = :curriculum" )
    List<Course> findByCurriculum( @Param( "curriculum" ) Curriculum curriculum );
}

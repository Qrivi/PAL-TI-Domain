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

import be.peerassistedlearning.model.Application;
import be.peerassistedlearning.model.ApplicationState;
import be.peerassistedlearning.model.Image;
import be.peerassistedlearning.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Interface for Application specific database operations
 *
 * @see Application
 * @see CrudRepository
 */
@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer>
{
    /**
     * Gets all the applications with the specified state
     *
     * @param state The state of the application
     * @return All the applications with the specified state
     */
    @Query( "SELECT a FROM Application a WHERE a.state = :state" )
    Collection<Application> findAll( @Param( "state" ) ApplicationState state );

    /**
     * Gets all the applications with the specified student and state
     *
     * @param student The student of the application
     * @param state   The state of the application
     * @return All the applications with the specified state
     */
    @Query( "SELECT a FROM Application a WHERE a.student = :student AND a.state = :state" )
    Collection<Application> findAll( @Param( "student" ) Student student, @Param( "state" ) ApplicationState state );

    /**
     * Gets the last applications with the specified student
     *
     * @param student  The student of the application
     * @param pageable The page to select from
     * @return The last applications with the specified student
     */
    @Query( "SELECT a FROM Application a WHERE a.student = :student ORDER BY a.beginDate DESC" )
    List<Application> findLastByStudent( @Param( "student" ) Student student, Pageable pageable );

    /**
     * Gets the application screenshot
     *
     * @param application The application of the screenshot
     * @return The application screenshot
     */
    @Query( "SELECT a.screenshot FROM Application a WHERE :application = a" )
    Image findScreenshotByApplication( @Param( "application" ) Application application );
}

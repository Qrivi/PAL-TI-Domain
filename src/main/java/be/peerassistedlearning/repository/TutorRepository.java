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
import be.peerassistedlearning.model.Student;
import be.peerassistedlearning.model.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Tutor specific database operations
 *
 * @see Tutor
 * @see CrudRepository
 */
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer>{
    /**
     * Gets all the tutors from the specified course
     *
     * @param course The course to get the tutors from
     * @return A collection containing all the tutors from the specified course
     */
    @Query( "SELECT t FROM Tutor t WHERE :course MEMBER OF t.courses" )
    Collection<Tutor> findAll( @Param( "course" ) Course course );

    /**
     * Gets the tutor with the specified student
     *
     * @param student The student of the tutor
     * @return The tutor with the specified student
     */
    @Query( "SELECT t FROM Tutor t WHERE t.student = :student" )
    Tutor findByStudent( @Param( "student" ) Student student );
}

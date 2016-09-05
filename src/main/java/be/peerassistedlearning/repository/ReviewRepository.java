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

import be.peerassistedlearning.model.Lesson;
import be.peerassistedlearning.model.Review;
import be.peerassistedlearning.model.Student;
import be.peerassistedlearning.model.Tutor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Interface for Review specific database operations
 *
 * @see Review
 * @see CrudRepository
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
    /**
     * Gets reviews filtered by tutor
     *
     * @param tutor to be filtered on
     * @return A collection containing the reviews for that tutor
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson.tutor = :tutor ORDER BY r.date DESC" )
    Collection<Review> findByTutor( @Param( "tutor" ) Tutor tutor );

    /**
     * Gets reviews filtered by tutor from the offset with the limit as size
     *
     * @param tutor    to be filtered on
     * @param pageable The page to select from
     * @return A collection containing the reviews for that tutor from the offset with the limit as size
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson.tutor = :tutor ORDER BY r.date DESC" )
    List<Review> findByTutor( @Param( "tutor" ) Tutor tutor, Pageable pageable );

    /**
     * Gets reviews filtered by lesson
     *
     * @param lesson to be filterd on
     * @return A collection containing the reviews of that lesson
     */
    @Query( "SELECT r FROM Review r WHERE r.lesson = :lesson ORDER BY r.date DESC" )
    Collection<Review> findByLesson( @Param( "lesson" ) Lesson lesson );

    /**
     * Gets the reviews made by the given student
     *
     * @param student The given student
     * @return A collection containing the reviews of that student
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.anonymous = false ORDER BY r.date DESC" )
    Collection<Review> findByStudent( @Param( "student" ) Student student );

    /**
     * Gets the reviews made by the given student from the offset with the limit as size
     *
     * @param student  The given student
     * @param pageable The page to select from
     * @return A collection containing the reviews of that student from the offset with the limit as size
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.anonymous = false ORDER BY r.date DESC" )
    List<Review> findByStudent( @Param( "student" ) Student student, Pageable pageable );

    /**
     * Gets reviews filtered by student and lesson
     *
     * @param student The student that gave the review
     * @param lesson  The lesson of the review
     * @return A collection containing the reviews of that student
     */
    @Query( "SELECT r FROM Review r WHERE r.student = :student AND r.lesson = :lesson" )
    Review findByStudentAndLesson( @Param( "student" ) Student student, @Param( "lesson" ) Lesson lesson );
}

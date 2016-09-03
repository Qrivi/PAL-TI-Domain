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

import be.peerassistedlearning.config.ApplicationConfig;
import be.peerassistedlearning.model.*;
import be.peerassistedlearning.util.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class ReviewRepositoryTest implements RepositoryTest
{
    private Student s1, s2;
    private Lesson l;

    private final String text = "The l was great, I learned alot!";

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Before
    public void before()
    {
        s1 = new Student( "r9876543", "Koen", "password", "koen1992@hotmail.com", Curriculum.TI, "koen", UserType.NORMAL );
        s2 = new Student( "r3456789", "Matthias", "password", "matthias@hotmail.com", Curriculum.TI, "matthias", UserType.NORMAL );
        Student s3 = new Student( "r0123456", "David", "password", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.NORMAL );

        s1 = studentRepository.save( s1 );
        s2 = studentRepository.save( s2 );
        s3 = studentRepository.save( s3 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );
        assertNotNull( s3.getId() );

        Course course = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );
        course = courseRepository.save( course );

        assertNotNull( course.getId() );

        Set<Course> courses = new HashSet<Course>();
        courses.add( course );

        Room room = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room roomBackup = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        room = roomRepository.save( room );
        roomBackup = roomRepository.save( roomBackup );

        assertNotNull( room.getId() );
        assertNotNull( roomBackup.getId() );

        Tutor tutor = new Tutor( s3, courses );
        tutor = tutorRepository.save( tutor );

        assertNotNull( tutor.getId() );

        l = new Lesson( new Date(), "Test l", "Test description", 120L, course, 25, tutor, room, roomBackup );
        lessonRepository.save( l );

        assertNotNull( l.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Review review = new Review( text, s1, l, 5, 5, 5, 5, false );

        reviewRepository.save( review );

        assertNotNull( review.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Review review = new Review( text, s1, l, 5, 5, 5, 5, false );

        reviewRepository.save( review );

        review.setAtmosphereScore( 10 );

        reviewRepository.save( review );

        Review updated = reviewRepository.findOne( review.getId() );

        assertEquals( review, updated );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Review review = new Review( text, s1, l, 5, 5, 5, 5, false );

        reviewRepository.save( review );
        reviewRepository.delete( review );

        assertNull( reviewRepository.findOne( review.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Review review = new Review( text, s1, l, 5, 5, 5, 5, false );

        reviewRepository.save( review );

        assertEquals( review, reviewRepository.findOne( review.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Review review = new Review( text, s1, l, 5, 7, 6, 4, false );
        Review review1 = new Review( text, s2, l, 5, 4, 8, 6, true );

        reviewRepository.save( review );
        reviewRepository.save( review1 );

        assertNotNull( review.getId() );
        assertNotNull( review1.getId() );

        Collection<Review> list = Utils.makeCollection( reviewRepository.findAll() );

        assertNotNull( list );
        Assert.assertEquals( 2, list.size() );
    }
}

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

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class LessonRepositoryTest implements RepositoryTest{

    private Lesson l1, l2;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Before
    public void before(){
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );
        c1 = courseRepository.save( c1 );

        assertNotNull( c1 );

        Set<Course> courses = new HashSet<Course>();
        courses.add( c1 );

        Student s1 = new Student( "Koen", "paswoord", "koen1992@hotmail.com", Curriculum.TI, "koen", UserType.ADMIN );
        s1 = studentRepository.save( s1 );

        assertNotNull( s1.getId() );

        Tutor t1 = new Tutor( s1, courses );
        t1 = tutorRepository.save( t1 );

        assertNotNull( t1.getId() );

        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomRepository.save( r1 );
        r2 = roomRepository.save( r2 );

        assertNotNull( r1.getId() );
        assertNotNull( r2.getId() );

        l1 = new Lesson( new Date(), "Test lesson", "Test description", 120L, c1, 25, t1, r1, r2 );
        l2 = new Lesson( new Date(), "Test lesson", "Test description", 15L, c1, 5, t1, r1, r2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd(){
        lessonRepository.save( l1 );
        assertNotNull( l1.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate(){
        lessonRepository.save( l1 );

        assertNotNull( l1.getId() );

        l1.setDuration( 90L );

        Lesson l3 = lessonRepository.findOne( l1.getId() );

        assertEquals( l1, l3 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove(){
        lessonRepository.save( l1 );
        lessonRepository.delete( l1 );
        assertNull( lessonRepository.findOne( l1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById(){
        lessonRepository.save( l1 );

        assertNotNull( l1.getId() );
        assertNotNull( lessonRepository.findOne( l1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll(){
        lessonRepository.save( l1 );
        lessonRepository.save( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );

        Collection<Lesson> list = Utils.makeCollection( lessonRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

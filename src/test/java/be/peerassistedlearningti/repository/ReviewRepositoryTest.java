package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.util.Utils;
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
        s1 = new Student( "Koen", "password", "koen1992@hotmail.com", "koen", UserType.NORMAL );
        s2 = new Student( "Matthias", "password", "matthias@hotmail.com", "matthias", UserType.NORMAL );
        Student s3 = new Student( "David", "password", "davidopdebeeck@hotmail.com", "david", UserType.NORMAL );

        s1 = studentRepository.save( s1 );
        s2 = studentRepository.save( s2 );
        s3 = studentRepository.save( s3 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );
        assertNotNull( s3.getId() );

        Course course = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
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

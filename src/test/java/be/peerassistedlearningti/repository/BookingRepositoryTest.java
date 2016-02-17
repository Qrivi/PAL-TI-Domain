package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.util.Utils;
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
public class BookingRepositoryTest implements RepositoryTest
{

    private Student s1, s2;
    private Lesson l1;

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

    @Autowired
    private BookingRepository bookingRepository;

    @Before
    public void before()
    {
        s1 = new Student( "Koen", "passwoord", "koen1992@hotmail.com", UserType.ADMIN );
        s2 = new Student( "Jan", "secret", "jan2016@hotmail.com", UserType.ADMIN );

        s1 = studentRepository.save( s1 );
        s2 = studentRepository.save( s2 );

        assertNotNull( s1.getId() );
        assertNotNull( s2.getId() );

        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        r1 = roomRepository.save( r1 );
        r2 = roomRepository.save( r2 );

        assertNotNull( r1.getId() );
        assertNotNull( r2.getId() );

        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
        Course c2 = new Course( "MBI81x", "Communicatie in het Frans Deel 3", "Frans 3", "TI", 3 );

        Set<Course> courses = new HashSet<Course>();
        courses.add( c1 );
        courses.add( c2 );

        c1 = courseRepository.save( c1 );
        c2 = courseRepository.save( c2 );

        assertNotNull( c1.getId() );
        assertNotNull( c2.getId() );

        Tutor t = new Tutor( s1, courses );
        t = tutorRepository.save( t );

        assertNotNull( t.getId() );

        l1 = new Lesson( new Date(), "Test lesson", "Test description", 60000, c1, 25, t, r1, r2 );
        Lesson l2 = new Lesson( new Date(), "Test lesson", "Test description", 60000, c2, 30, t, r2, r1 );

        l1 = lessonRepository.save( l1 );
        l2 = lessonRepository.save( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Booking b = new Booking( l1, s1 );

        bookingRepository.save( b );

        assertNotNull( b.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Booking b1 = new Booking( l1, s1 );

        bookingRepository.save( b1 );

        b1.setStudent( s2 );

        bookingRepository.save( b1 );

        Booking b2 = bookingRepository.findOne( b1.getId() );

        assertEquals( b1, b2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Booking b = new Booking( l1, s1 );

        bookingRepository.save( b );
        bookingRepository.delete( b );

        assertNull( bookingRepository.findOne( b.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Booking b1 = new Booking( l1, s1 );

        bookingRepository.save( b1 );

        Booking b2 = bookingRepository.findOne( b1.getId() );

        assertNotNull( b1 );
        assertEquals( b1, b2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Booking b1 = new Booking( l1, s1 );
        Booking b2 = new Booking( l1, s2 );

        bookingRepository.save( b1 );
        bookingRepository.save( b2 );

        Collection<Booking> list = Utils.makeCollection( bookingRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

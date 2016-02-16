package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Class used to test ReviewJPADAO
 */
public class ReviewJPADAOTest extends JPADAOTest
{

    private static ReviewJPADAO reviewJPADAO;
    private Student student, student1;
    private Lesson lesson;
    private static final String SHORT_TEXT = "Good", NORMAL_TEXT = "The lesson was great, I learned alot!", LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet tortor maximus, scelerisque ex eu, bibendum purus. Aenean lectus sed.";

    /**
     * Constructor for ReviewJPADAOTest
     */
    public ReviewJPADAOTest() {super( "PAL" );}

    /**
     * Assign the factory to the dao
     */
    @Before
    public void before()
    {
        super.before();

        // Set JPADAO

        reviewJPADAO = new ReviewJPADAO();
        reviewJPADAO.setEntityManagerFactory( factory );

        StudentJPADAO studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory( factory );

        CourseJPADAO courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory( factory );

        RoomJPADAO roomJPADAO = new RoomJPADAO();
        roomJPADAO.setEntityManagerFactory( factory );

        TutorJPADAO tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory( factory );

        LessonJPADAO lessonJPADAO = new LessonJPADAO();
        lessonJPADAO.setEntityManagerFactory( factory );


        // Make Student object and add to database
        student = new Student( "Koen", "password", "koen1992@hotmail.com", UserType.NORMAL );
        student1 = new Student( "Matthias", "password", "matthias@hotmail.com", UserType.NORMAL );
        Student smartStudent = new Student( "David", "password", "davidopdebeeck@hotmail.com", UserType.NORMAL );
        studentJPADAO.add( student );
        studentJPADAO.add( student1 );
        studentJPADAO.add( smartStudent );

        assertNotNull( student.getId() );
        assertNotNull( student1.getId() );
        assertNotNull( smartStudent.getId() );

        // Make course object and add to database & Set
        Course course = new Course( "MBI80x", ".NET Programmeren", ".NET" );
        course = courseJPADAO.add( course );
        assertNotNull( course );

        Set<Course> courses = new HashSet<Course>();
        courses.add( course );

        // Make room object and add to database
        Room room = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room roomBackup = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );
        room = roomJPADAO.add( room );
        roomBackup = roomJPADAO.add( roomBackup );
        assertNotNull( room.getId() );
        assertNotNull( roomBackup.getId() );

        // Make tutor object and add to database
        Tutor tutor = new Tutor( smartStudent, courses );
        tutorJPADAO.add( tutor );
        assertNotNull( tutor.getId() );

        // Make lesson object and add to database
        lesson = new Lesson( new Date(), "Test lesson", "Test description", 120L, course, 25, tutor, room, roomBackup );

        lessonJPADAO.add( lesson );
        assertNotNull( lesson.getId() );
    }

    @Override
    public void testAdd()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 5, 5, 5, false);
        reviewJPADAO.add( review );
        assertNotNull( review.getId() );
    }

    @Override
    public void testUpdate()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 5, 5, 5, false);
        reviewJPADAO.add( review );
        assertNotNull( review.getId() );
        review.setAtmosphereScore( 10 );
        Review updated = reviewJPADAO.update( review );
        assertEquals( review, updated );
    }

    @Override
    public void testRemove()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 5, 5, 5, false);
        reviewJPADAO.add( review );
        assertNotNull( review.getId() );
        reviewJPADAO.remove( review );
        assertNull( reviewJPADAO.getById( review.getId() ) );
    }

    @Override
    public void testGetById()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 5, 5, 5, false);
        reviewJPADAO.add( review );
        assertNotNull( review.getId() );
        assertEquals( review, reviewJPADAO.getById( review.getId() ) );
    }

    @Override
    public void testGetAll()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 7, 6, 4, false);
        Review review1 = new Review( NORMAL_TEXT, student1, lesson, 5, 4, 8, 6, true);
        reviewJPADAO.add( review );
        reviewJPADAO.add( review1 );
        assertNotNull( review.getId() );
        assertNotNull( review1.getId() );

        Collection<Review> list = reviewJPADAO.getAll();

        assertNotNull( list );
        Assert.assertEquals( 2, list.size() );
    }

    @Override
    public void testGetLast()
    {
        Review review = new Review( NORMAL_TEXT, student, lesson, 5, 7, 6, 4, true);
        Review review1 = new Review( NORMAL_TEXT, student1, lesson, 5, 4, 8, 6, false);

        reviewJPADAO.add( review );
        reviewJPADAO.add( review1 );

        assertNotNull( review.getId() );
        assertNotNull( review1.getId() );

        Review last = reviewJPADAO.getLast();
        Assert.assertEquals( review1, last );
    }
}

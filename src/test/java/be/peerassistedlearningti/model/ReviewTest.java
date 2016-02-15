package be.peerassistedlearningti.model;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class ReviewTest extends ValidationTest
{

    private static final String SHORT_TEXT = "Good";
    private static final String NORMAL_TEXT = "The lesson was great, I learned a lot!";
    private static final String LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet tortor maximus, scelerisque ex eu, bibendum purus. Aenean lectus sed.";

    private static Tutor t;
    private static Lesson l;
    private static Course c;
    private static Room r1, r2;
    private static Student s1, s2;

    @BeforeClass
    public static void setUp()
    {
        ValidationTest.setUp();

        s1 = new Student( "Koen", "password", "koen1992@hotmail.com", UserType.NORMAL );
        s2 = new Student( "Matthias", "password", "matthias@hotmail.com", UserType.NORMAL );

        c = new Course( "MBI80x", ".NET Programmeren", ".NET" );

        r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        t = new Tutor( s1, new HashSet<Course>()
        {{ add( c ); }} );

        l = new Lesson( new Date(), "Test lesson", "Test description", 120L, c, 25, t, r1, r2 );
    }

    @Test
    public void testTextTooShort()
    {
        Review r = new Review( SHORT_TEXT, s1, l, 5, 5, 5, 5 );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testTextTooLong()
    {
        Review r = new Review( LONG_TEXT, new Student(), new Lesson(), 5, 5, 5, 5 );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScoreTooLow()
    {
        Review r = new Review( NORMAL_TEXT, new Student(), new Lesson(), 0, 5, 5, 5 );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "contentScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review content score should not be less than 1!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScoreTooHigh()
    {
        Review r = new Review( NORMAL_TEXT, new Student(), new Lesson(), 5, 5, 5, 11 );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "atmosphereScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review atmosphere score should not be greater than 10!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }


}
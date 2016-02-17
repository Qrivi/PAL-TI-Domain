package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class BookingTest extends ValidationTest
{
    @Test
    public void testLessonIsNull()
    {
        Booking b = new Booking( null, new Student() );

        Set<ConstraintViolation<Booking>> constraintViolations = validator.validateProperty( b, "lesson" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Booking lesson should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testStudentIsNull()
    {
        Booking b = new Booking( new Lesson(), null );

        Set<ConstraintViolation<Booking>> constraintViolations = validator.validateProperty( b, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Booking student should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
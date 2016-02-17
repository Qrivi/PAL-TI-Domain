package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

public class TutorTest extends ValidationTest
{

    @Test
    public void testStudentIsNull()
    {
        Tutor t = new Tutor( null, new HashSet<Course>() );

        Set<ConstraintViolation<Tutor>> constraintViolations = validator.validateProperty( t, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Tutor student should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCoursesIsNull()
    {
        Tutor t = new Tutor( new Student(), null );

        Set<ConstraintViolation<Tutor>> constraintViolations = validator.validateProperty( t, "courses" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Tutor courses should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
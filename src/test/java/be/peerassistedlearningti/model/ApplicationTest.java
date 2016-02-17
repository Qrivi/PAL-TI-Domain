package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.Set;

public class ApplicationTest extends ValidationTest
{
    @Test
    public void testStudentIsNull()
    {
        Application a = new Application( null, new Course(), new byte[ 2 ], ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application student should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCourseIsNull()
    {
        Application a = new Application( new Student(), null, new byte[ 2 ], ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "course" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application course should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScreenshotIsNull()
    {
        Application a = new Application( new Student(), new Course(), null, ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "screenshot" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application screenshot should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testStateIsNull()
    {
        Application a = new Application( new Student(), new Course(), new byte[ 2 ], null, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "state" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application state should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testBeginDateIsNull()
    {
        Application a = new Application( new Student(), new Course(), new byte[ 2 ], ApplicationState.PENDING, null, new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "beginDate" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application begin date should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
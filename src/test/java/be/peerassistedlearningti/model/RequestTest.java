package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class RequestTest extends ValidationTest
{
    @Test
    public void testUpvotesIsNegative()
    {
        Request request = new Request( -1, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "upvotes" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Upvotes should not be less than 0!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionIsNull()
    {
        Request request = new Request( 0, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionSizeIsTooSmall()
    {
        Request request = new Request( 0, "abc", null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should be between 10 and 300 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionSizeIsTooBig()
    {
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu maximus metus, vel imperdiet nunc. Cras commodo vestibulum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin ultrices magna ut nisl venenatis, quis pretium urna fermentum. In aliquam velit id enim porta convallis. Vestibulum vel efficitur dui. Curabitur vitae neque nullam.";

        Request request = new Request( 0, desc, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should be between 10 and 300 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testCourseIsNull()
    {
        Request request = new Request( 0, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "course" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request course should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testStudentIsNull()
    {
        Request request = new Request( 0, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "student" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request student should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testCreationDateIsNull()
    {
        Request request = new Request( 0, null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "creationDate" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request creation date should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

}

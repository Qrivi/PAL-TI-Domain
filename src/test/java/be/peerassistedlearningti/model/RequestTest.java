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
        Request request = new Request(-1,"",null,null);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty(request, "upvotes");
        Assert.assertEquals(1,constraintViolations.size());
        Assert.assertEquals("Upvotes cannot beless than 0!", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionIsNull()
    {
        Request request = new Request(0,null,null,null);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty(request, "description");
        Assert.assertEquals(1,constraintViolations.size());
        Assert.assertEquals("Request description cannot be empty!", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testCourseIsNull()
    {
        Request request = new Request(0,null,null,null);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty(request, "course");
        Assert.assertEquals(1,constraintViolations.size());
        Assert.assertEquals("Request course cannot be empty!", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testStudentIsNull()
    {
        Request request = new Request(0,null,null,null);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty(request, "student");
        Assert.assertEquals(1,constraintViolations.size());
        Assert.assertEquals("Request student cannot be empty!", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testCreationDateIsNull()
    {
        Request request = new Request(0,null,null,null,null);
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty(request, "creationDate");
        Assert.assertEquals(1,constraintViolations.size());
        Assert.assertEquals("Request creation date cannot be empty!", constraintViolations.iterator().next().getMessage());
    }
}

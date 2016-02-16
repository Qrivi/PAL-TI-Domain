package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReviewTest extends ValidationTest
{

    private static final String SHORT_TEXT = "Good";
    private static final String NORMAL_TEXT = "The lesson was great, I learned a lot!";
    private static final String LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet tortor maximus, scelerisque ex eu, bibendum purus. Aenean lectus sed.";

    @Test
    public void testTextTooShort()
    {
        Review r = new Review( SHORT_TEXT, new Student(), new Lesson(), 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testTextTooLong()
    {
        Review r = new Review( LONG_TEXT, new Student(), new Lesson(), 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScoreTooLow()
    {
        Review r = new Review( NORMAL_TEXT, new Student(), new Lesson(), 0, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "contentScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review content score should not be less than 1!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScoreTooHigh()
    {
        Review r = new Review( NORMAL_TEXT, new Student(), new Lesson(), 5, 5, 5, 11, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "atmosphereScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review atmosphere score should not be greater than 10!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

}
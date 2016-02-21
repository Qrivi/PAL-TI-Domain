package be.peerassistedlearningti.model;

import be.peerassistedlearningti.service.PALService;
import be.peerassistedlearningti.service.PALServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CourseTest extends ValidationTest
{

    @Test
    public void testCodeIsNull()
    {
        Course c = new Course( null, ".NET Programmeren", ".NET", "Toegepaste Informatica", 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "code" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course code should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testNameIsNull()
    {
        Course c = new Course( "MX2506", null, ".NET", "Toegepaste Informatica", 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "name" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testShortNameIsNull()
    {
        Course c = new Course( "MX2506", ".NET Programmeren", null, "Toegepaste Informatica", 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "shortName" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course short name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCurriculumIsNull()
    {
        Course c = new Course( "MX2506", ".NET Programmeren", ".NET", null, 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "curriculum" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course curriculum should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testYearIsTooLow() {
        Course c = new Course("MX2506", ".NET Programmeren", ".NET", "Toegepaste Informatica", 0);

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty(c, "year");

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("The course year should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage());
    }
}
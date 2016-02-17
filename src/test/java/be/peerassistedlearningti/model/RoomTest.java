package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class RoomTest extends ValidationTest
{

    @Test
    public void testNameIsNull()
    {
        Room r = new Room( null, Campus.PROXIMUS, RoomType.PLAIN );

        Set<ConstraintViolation<Room>> constraintViolations = validator.validateProperty( r, "name" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Room name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCampusIsNull()
    {
        Room r = new Room( "", null, RoomType.PLAIN );

        Set<ConstraintViolation<Room>> constraintViolations = validator.validateProperty( r, "campus" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Room campus should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testRoomTypeIsNull()
    {
        Room r = new Room( "", Campus.PROXIMUS, null );

        Set<ConstraintViolation<Room>> constraintViolations = validator.validateProperty( r, "type" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Room type should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

}
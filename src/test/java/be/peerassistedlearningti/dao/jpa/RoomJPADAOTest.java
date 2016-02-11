package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Room;
import be.peerassistedlearningti.model.RoomType;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Class used to test RoomJPADAO
 */
public class RoomJPADAOTest extends JPADAOTest
{

    private RoomJPADAO roomJPADAO;

    /**
     * Constructor for RoomJPADAOTest
     */
    public RoomJPADAOTest()
    {
        super( "PAL" );
    }

    /**
     * Assign the factory to the dao
     */
    @Before
    public void before()
    {
        super.before();
        roomJPADAO = new RoomJPADAO();
        roomJPADAO.setEntityManagerFactory( factory );
    }

    @Test
    public void testAdd()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r = roomJPADAO.add( r );

        assertNotNull( r.getId() );
    }

    @Test
    public void testGetById()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomJPADAO.add( r1 );

        Room r2 = roomJPADAO.getById( r1.getId() );

        assertNotNull( r2 );
        assertEquals( r1, r2 );
    }

    @Test
    public void testUpdate()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomJPADAO.add( r1 );

        r1.setName( "2.26" );

        r1 = roomJPADAO.update( r1 );

        Room c2 = roomJPADAO.getById( r1.getId() );

        assertEquals( c2.getName(), "2.26" );
    }

    @Test
    public void testRemove()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r = roomJPADAO.add( r );

        roomJPADAO.remove( r );

        r = roomJPADAO.getById( r.getId() );

        assertNull( r );
    }

    @Test
    public void testGetAll()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        roomJPADAO.add( r1 );
        roomJPADAO.add( r2 );

        Collection<Room> list = roomJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    public void testGetLast()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        roomJPADAO.add( r1 );
        roomJPADAO.add( r2 );

        Room r3 = roomJPADAO.getLast();

        assertNotNull( r3 );
        assertEquals( r2, r3 );
    }

    @Test
    public void testGetFromCampus()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        roomJPADAO.add( r1 );
        roomJPADAO.add( r2 );

        Collection<Room> list = roomJPADAO.getFromCampus( Campus.PROXIMUS );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}
package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Room;
import be.peerassistedlearningti.model.RoomType;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class RoomJPADAOTest extends TestCase
{

    private static RoomJPADAO roomJPADAO;

    @BeforeClass
    public static void beforeClass()
    {
        // Get the entity manager factory from persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "PAL" );
        // Assign the factory to the dao
        roomJPADAO = new RoomJPADAO();
        roomJPADAO.setEntityManagerFactory( factory );
    }

    @Test
    public void test1Add()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r = roomJPADAO.add( r );

        assertNotNull( r.getId() );

        roomJPADAO.remove( r );
    }

    @Test
    public void test2GetById()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomJPADAO.add( r1 );

        Room r2 = roomJPADAO.getById( r1.getId() );

        assertNotNull( r2 );
        assertEquals( r1, r2 );

        roomJPADAO.remove( r1 );
    }

    @Test
    public void test3Update()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomJPADAO.add( r1 );

        r1.setName( "2.26" );

        r1 = roomJPADAO.update( r1 );

        Room c2 = roomJPADAO.getById( r1.getId() );

        assertEquals( c2.getName(), "2.26" );

        roomJPADAO.remove( r1 );
    }

    @Test
    public void test4Remove()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        r = roomJPADAO.add( r );

        roomJPADAO.remove( r );

        r = roomJPADAO.getById( r.getId() );

        assertNull( r );
    }

    @Test
    public void test5GetAll()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        roomJPADAO.add( r1 );
        roomJPADAO.add( r2 );

        Collection<Room> list = roomJPADAO.getAll();

        assertNotNull( list );
        assertEquals( 2, list.size() );

        roomJPADAO.remove( r1 );
        roomJPADAO.remove( r2 );
    }

    @Test
    public void test6GetLast()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        roomJPADAO.add( r1 );
        roomJPADAO.add( r2 );

        Room r3 = roomJPADAO.getLast();

        assertNotNull( r3 );
        assertEquals( r2, r3 );

        roomJPADAO.remove( r1 );
        roomJPADAO.remove( r2 );
    }

    @Test
    public void test7GetFromCampus()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomJPADAO.add( r1 );
        r2 = roomJPADAO.add( r2 );

        Collection<Room> list = roomJPADAO.getFromCampus( Campus.PROXIMUS );

        assertNotNull( list );
        assertEquals( 2, list.size() );

        roomJPADAO.remove( r1 );
        roomJPADAO.remove( r2 );
    }
}
package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Room;
import be.peerassistedlearningti.model.RoomType;
import be.peerassistedlearningti.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class RoomRepositoryTest implements RepositoryTest
{
    @Autowired
    RoomRepository repository;

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        repository.save( r );

        assertNotNull( r.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        repository.save( r1 );

        r1.setName( "2.26" );

        repository.save( r1 );

        Room c2 = repository.findOne( r1.getId() );

        assertEquals( c2.getName(), "2.26" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Room r = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        repository.save( r );
        repository.delete( r );

        r = repository.findOne( r.getId() );

        assertNull( r );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );

        repository.save( r1 );

        Room r2 = repository.findOne( r1.getId() );

        assertNotNull( r2 );
        assertEquals( r1, r2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.PLAIN );

        repository.save( r1 );
        repository.save( r2 );

        Collection<Room> list = Utils.makeCollection( repository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetFromCampus()
    {
        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        repository.save( r1 );
        repository.save( r2 );

        Collection<Room> list = repository.findByCampus( Campus.PROXIMUS );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

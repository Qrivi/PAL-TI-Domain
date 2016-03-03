/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearning.repository;

import be.peerassistedlearning.config.ApplicationConfig;
import be.peerassistedlearning.model.Campus;
import be.peerassistedlearning.model.Room;
import be.peerassistedlearning.model.RoomType;
import be.peerassistedlearning.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

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

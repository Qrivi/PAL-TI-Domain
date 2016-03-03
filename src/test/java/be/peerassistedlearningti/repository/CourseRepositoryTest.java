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

package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Curriculum;
import be.peerassistedlearningti.util.Utils;
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
public class CourseRepositoryTest implements RepositoryTest
{
    @Autowired
    CourseRepository repository;

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c );

        assertNotNull( c.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c1 );

        c1.setName( ".NET Programmeren in Visual Studio" );

        repository.save( c1 );

        Course c2 = repository.findOne( c1.getId() );

        assertEquals( c2.getName(), ".NET Programmeren in Visual Studio" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Course c = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c );

        repository.delete( c );

        c = repository.findOne( c.getId() );

        assertNull( c );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c1 );

        Course c2 = repository.findOne( c1.getId() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );
        Course c2 = new Course( "MBI62a", "3D Computer Graphics", "3D", Curriculum.TI, 3 );

        repository.save( c1 );
        repository.save( c2 );

        Collection<Course> list = Utils.makeCollection( repository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetByCode()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c1 );

        Course c2 = repository.findByCode( c1.getCode() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetByName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c1 );

        Course c2 = repository.findByName( c1.getName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetByShortName()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );

        repository.save( c1 );

        Course c2 = repository.findByShortName( c1.getShortName() );

        assertNotNull( c2 );
        assertEquals( c1, c2 );
    }
}

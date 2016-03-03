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
import be.peerassistedlearningti.model.Curriculum;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.UserType;
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
public class StudentRepositoryTest implements RepositoryTest
{
    @Autowired
    StudentRepository repository;

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );

        repository.save( s );

        assertNotNull( s.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );

        repository.save( s1 );

        s1.setName( "Cedric" );

        repository.save( s1 );

        Student s2 = repository.findOne( s1.getId() );

        assertEquals( s2.getName(), "Cedric" );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );

        repository.save( s );
        repository.delete( s );

        s = repository.findOne( s.getId() );

        assertNull( s );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );

        repository.save( s1 );

        Student s2 = repository.findOne( s1.getId() );

        assertNotNull( s2 );
        assertEquals( s1, s2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );
        Student s2 = new Student( "Cedric", "paswoord", "cedricopdebeeck@hotmail.com", Curriculum.TI, "cedric", UserType.ADMIN );

        repository.save( s1 );
        repository.save( s2 );

        Collection<Student> list = Utils.makeCollection( repository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetByEmail() throws Exception
    {
        Student s1 = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david", UserType.ADMIN );

        repository.save( s1 );

        Student s2 = repository.findByEmail( "davidopdebeeck@hotmail.com" );

        assertNotNull( s2 );
        assertEquals( s1, s2 );
    }
}
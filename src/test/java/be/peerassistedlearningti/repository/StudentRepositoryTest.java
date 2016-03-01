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
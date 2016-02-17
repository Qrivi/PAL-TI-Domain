package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class StudentRepositoryTest
{
    @Autowired
    StudentRepository repository;

    @Test
    public void sampleTestCase()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", UserType.ADMIN );

        repository.save( s );

        assertNotNull( s.getId() );
    }
}
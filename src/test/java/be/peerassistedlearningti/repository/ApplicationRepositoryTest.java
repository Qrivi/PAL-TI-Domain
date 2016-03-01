package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.util.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class ApplicationRepositoryTest implements RepositoryTest
{

    private Application a1, a2;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Before
    public void before()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );
        c1 = courseRepository.save( c1 );

        assertNotNull( c1.getId() );

        Student s1 = new Student( "Koen", "paswoord", "koen1992@hotmail.com", Curriculum.TI, "koen", UserType.ADMIN );
        s1 = studentRepository.save( s1 );

        assertNotNull( s1.getId() );

        a1 = new Application( s1, c1, new byte[ 2 ] );
        a2 = new Application( s1, c1, new byte[ 2 ], ApplicationState.PENDING, new Date(), new Date() );
    }


    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        applicationRepository.save( a1 );
        assertNotNull( a1.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        applicationRepository.save( a1 );

        assertNotNull( a1.getId() );

        a1.reject();

        applicationRepository.save( a1 );

        Application a3 = applicationRepository.findOne( a1.getId() );

        assertEquals( a1, a3 );

    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        applicationRepository.save( a1 );
        applicationRepository.delete( a1 );
        assertNull( applicationRepository.findOne( a1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        applicationRepository.save( a1 );
        assertNotNull( a1.getId() );
        assertNotNull( applicationRepository.findOne( a1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        applicationRepository.save( a1 );
        applicationRepository.save( a2 );

        assertNotNull( a1.getId() );
        assertNotNull( a2.getId() );

        Collection<Application> list = Utils.makeCollection( applicationRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

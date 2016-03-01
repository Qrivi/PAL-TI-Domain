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

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class RequestRepositoryTest implements RepositoryTest
{

    private Student s1;
    private Course c1;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void before()
    {
        //make student object
        s1 = new Student( "Koen", "password", "koen1992@hotmail.com", Curriculum.TI, "koen", UserType.NORMAL );
        studentRepository.save( s1 );
        assertNotNull( s1.getId() );

        //make course object
        c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", Curriculum.TI, 3 );
        courseRepository.save( c1 );
        assertNotNull( c1.getId() );
    }

    @Test
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        Request request = new Request("some title", "some description", c1, s1 );
        requestRepository.save( request );
        assertNotNull( request.getId() );
    }

    @Test
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        Request request = new Request( "some title", "some description", c1, s1 );
        requestRepository.save( request );
        assertNotNull( request.getId() );

        request.setTitle( "some other title" );
        requestRepository.save( request );
        assertEquals( "some other title", request.getUpvotes() );
    }

    @Test
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        Request request = new Request( "some title", "some description", c1, s1 );
        requestRepository.save( request );
        assertNotNull( request.getId() );
        requestRepository.delete( request );
        assertNull( requestRepository.findOne( request.getId() ) );

    }

    @Test
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        Request request = new Request( "some title", "some description", c1, s1 );
        requestRepository.save( request );
        assertEquals( request, requestRepository.findOne( request.getId() ) );

    }

    @Test
    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        Request request = new Request( "some title", "some description", c1, s1 );
        Request otherRequest = new Request( "some other title", "some different description", c1, s1 );
        requestRepository.save( request );
        requestRepository.save( otherRequest );
        assertNotNull( request.getId() );
        assertNotNull( otherRequest.getId() );

        Collection<Request> list = Utils.makeCollection( requestRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

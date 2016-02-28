package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Request;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.UserType;
import be.peerassistedlearningti.util.Utils;
import org.junit.Before;
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
public class RequestRepositoryTest implements RepositoryTest{

    private Student s1;
    private Course c1;
    private Request r1, r2;

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
        s1 = new Student( "Koen", "password", "koen1992@hotmail.com", "koen", UserType.NORMAL );
        studentRepository.save( s1 );
        assertNotNull( s1.getId() );

        //make course object
        c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
        courseRepository.save( c1 );
        assertNotNull( c1.getId() );

    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd() {
        Request request = new Request(10,"some description", c1, s1);
        requestRepository.save(request);
        assertNotNull( request.getId() );
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate() {
        Request request = new Request(10,"some description", c1, s1);
        requestRepository.save(request);
        assertNotNull( request.getId() );

        request.setUpvotes(20);
        requestRepository.save(request);
        assertEquals( 20,request.getUpvotes() );
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove() {
        Request request = new Request(10,"some description", c1, s1);
        requestRepository.save(request);
        assertNotNull( request.getId() );
        requestRepository.delete(request);
        assertNull( requestRepository.findOne( request.getId() ));

    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById() {
        Request request = new Request(10,"some description", c1, s1);
        requestRepository.save(request);
        assertEquals(request, requestRepository.findOne( request.getId() ));

    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll() {
        Request request = new Request(10,"some description", c1, s1);
        Request otherRequest = new Request(20,"some different description", c1, s1);
        requestRepository.save(request);
        requestRepository.save(otherRequest);
        assertNotNull( request.getId() );
        assertNotNull( otherRequest.getId() );

        Collection<Request> list = Utils.makeCollection(requestRepository.findAll());

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}

package be.peerassistedlearningti.dao.jpa;


import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.internal.SessionImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class TutorJPADAOTest {
    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;
    protected static IDatabaseConnection connection;
    protected static TutorJPADAO tutorJPADAO;
    protected static StudentJPADAO studentJPADAO;
    protected static CourseJPADAO courseJPADAO;

    @Before
    public void setup() throws Exception{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "PAL" );
        entityManager = entityManagerFactory.createEntityManager();
        connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());

        IDataSet dataset = new FlatXmlDataSetBuilder().build(new File("database_images/DatabaseImage.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection,dataset);

        tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory(entityManagerFactory);
        studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory(entityManagerFactory);
        courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory(entityManagerFactory);
    }

    @Test
    public void testAddTutor()throws Exception{
        Student student = studentJPADAO.getById(2);
        Assert.assertNotNull(student);

        Course course = courseJPADAO.getById(1);
        Assert.assertNotNull(course);

        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        Tutor newTutor = new Tutor(student,courses);
        tutorJPADAO.add(newTutor);

        //get expected result
        IDataSet expectedDataset = new FlatXmlDataSetBuilder().build(new File("database_images/expected_results/tutor/addTutorResultImage.xml"));
        ITable expectedTutorTable = expectedDataset.getTable("table");
        //get actual result
        IDataSet actualDataset = connection.createDataSet();
        ITable actualTutorTable = actualDataset.getTable("tutor");

        Assertion.assertEquals(expectedTutorTable,actualTutorTable);
    }

}

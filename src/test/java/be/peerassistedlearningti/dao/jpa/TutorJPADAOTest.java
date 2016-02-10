package be.peerassistedlearningti.dao.jpa;


import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Student;
import be.peerassistedlearningti.model.Tutor;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;


public class TutorJPADAOTest {
    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;
    protected static IDatabaseConnection connection;
    protected static IDataSet cleanDataset;
    protected static TutorJPADAO tutorJPADAO;
    protected static StudentJPADAO studentJPADAO;
    protected static CourseJPADAO courseJPADAO;

    @BeforeClass
    public static void init() throws MalformedURLException, DatabaseUnitException, FileNotFoundException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "PAL" );
        entityManager = entityManagerFactory.createEntityManager();
        connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        flatXmlDataSetBuilder.setColumnSensing(true);
        cleanDataset = flatXmlDataSetBuilder.build(new FileInputStream("src/test/resources/database_images/DatabaseImage.xml"));

        //FIXME kan maar 1 connectie met db tergelijk openen.
        tutorJPADAO = new TutorJPADAO();
        tutorJPADAO.setEntityManagerFactory(entityManagerFactory);
        studentJPADAO = new StudentJPADAO();
        studentJPADAO.setEntityManagerFactory(entityManagerFactory);
        courseJPADAO = new CourseJPADAO();
        courseJPADAO.setEntityManagerFactory(entityManagerFactory);
    }

    @Before
    public void setup() throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(connection,cleanDataset);
    }

    @AfterClass
    public static void breakdown() throws SQLException {
        connection.close();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testAddTutor()throws Exception{


        Student s = new Student( "Koen", "paswoord", "koen1992@hotmail.com", true );
        System.out.println("Student:"+s.getName());
        s = studentJPADAO.add( s );
        System.out.println("Student:"+s.getName());
        s = studentJPADAO.getLast();
        System.out.println("Student:"+s.getName());

        Student student = studentJPADAO.getById(2);
        assertNotNull(student);

        Course course = courseJPADAO.getById(1);
        assertNotNull(course);

        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        Tutor newTutor = new Tutor(student,courses);
        tutorJPADAO.add(newTutor);

        //get expected result
        IDataSet expectedDataset = new FlatXmlDataSetBuilder().build(new File("src/test/resources/database_images/expected_results/tutor/addTutorResultImage.xml"));
        ITable expectedTutorTable = expectedDataset.getTable("table");
        //get actual result
        IDataSet actualDataset = connection.createDataSet();
        ITable actualTutorTable = actualDataset.getTable("tutor");

        Assertion.assertEquals(expectedTutorTable,actualTutorTable);
    }

}

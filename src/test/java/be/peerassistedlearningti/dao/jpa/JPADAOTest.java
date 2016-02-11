package be.peerassistedlearningti.dao.jpa;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPADAOTest
{

    protected String persistenceUnitName;
    protected EntityManagerFactory factory;

    public JPADAOTest( String persistenceUnitName )
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Before
    public void before()
    {
        // Get the entity manager factory from persistence.xml
        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
    }

    @After
    public void after()
    {
        // Close the factory so we drop our connection and the database gets reset
        factory.close();
    }

}

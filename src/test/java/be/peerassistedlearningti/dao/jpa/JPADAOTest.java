package be.peerassistedlearningti.dao.jpa;

import org.junit.After;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class used to test a JPADAO
 */
public abstract class JPADAOTest
{

    protected String persistenceUnitName;
    protected EntityManagerFactory factory;

    /**
     * Constructor for JPADAOTest
     *
     * @param persistenceUnitName The persistence unit name for the test database
     */
    public JPADAOTest( String persistenceUnitName )
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    /**
     * Get the entity manager factory from persistence.xml
     */
    public void before()
    {
        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
    }

    /**
     * Close the factory so we drop our connection and the database gets reset
     */
    @After
    public void after()
    {
        factory.close();
    }

    /**
     * Test the add functionality of the DAO
     */
    public abstract void testAdd();

    /**
     * Test the update functionality of the DAO
     */
    public abstract void testUpdate();

    /**
     * Test the remove functionality of the DAO
     */
    public abstract void testRemove();

    /**
     * Test the get by id functionality of the DAO
     */
    public abstract void testGetById();

    /**
     * Test the get all functionality of the DAO
     */
    public abstract void testGetAll();

    /**
     * Test the get last functionality of the DAO
     */
    public abstract void testGetLast();

}

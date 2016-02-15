package be.peerassistedlearningti.dao.jpa;

import org.junit.Before;

/**
 * Class used to test ReviewJPADAO
 */
public class ReviewJPADAOTest extends JPADAOTest {

    private static ReviewJPADAO reviewJPADAO;
    /**
     * Constructor for ReviewJPADAOTest
     */
    public ReviewJPADAOTest(){super("PAL");}

    /**
     * Assign the factory to the dao
     */
    @Before
    public void before(){
        super.before();
        reviewJPADAO = new ReviewJPADAO();
        reviewJPADAO.setEntityManagerFactory(factory);
    }

    @Override
    public void testAdd() {

    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testRemove() {

    }

    @Override
    public void testGetById() {

    }

    @Override
    public void testGetAll() {

    }

    @Override
    public void testGetLast() {

    }
}

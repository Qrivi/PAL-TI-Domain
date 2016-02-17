package be.peerassistedlearningti.repository;

public interface RepositoryTest
{
    /**
     * Test the add functionality of the DAO
     */
    void testAdd();

    /**
     * Test the update functionality of the DAO
     */
    void testUpdate();

    /**
     * Test the remove functionality of the DAO
     */
    void testRemove();

    /**
     * Test the get by id functionality of the DAO
     */
    void testGetById();

    /**
     * Test the get all functionality of the DAO
     */
    void testGetAll();
}

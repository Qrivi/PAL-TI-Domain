package be.peerassistedlearningti.common.model.identifiable;

/**
 * Class used to add identity functionalities to another class
 *
 * @param <T> the type of ID
 */
public interface Identifiable <T extends Object>
{

    /**
     * Gets the id of the object
     *
     * @return The id
     */
    T getId();

    /**
     * Equals functionality (used in databases)
     *
     * @param obj The object to compare
     * @return False if the ids are not equal or are null
     */
    @Override
    boolean equals( Object obj );

    /**
     * Hashcode functionality
     *
     * @return The hashcode of the object
     */
    @Override
    int hashCode();

}

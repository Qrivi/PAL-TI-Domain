package be.peerassistedlearningti.common.dao;

/**
 * Class used to encapsulate all exceptions thrown in a dao class
 */
public class DAOException extends RuntimeException
{

    /**
     * Constructor for DAOException
     *
     * @param message The message of the exception
     */
    public DAOException( String message )
    {
        super(message);
    }

    /**
     * Constructor for DAOException
     *
     * @param e The exception that will be encapsulated
     */
    public DAOException( Throwable e )
    {
        super(e);
    }

    /**
     * Constructor for DAOException
     *
     * @param message The message of the exception
     * @param e The exception that will be encapsulated
     */
    public DAOException( String message, Throwable e ) { super(message, e); }
    
}

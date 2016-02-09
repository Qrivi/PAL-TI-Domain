package be.peerassistedlearningti.common.service;

/**
 * Class used to encapsulate all exceptions thrown in a service class
 */
public class ServiceException extends RuntimeException
{

    /**
     * Constructor for ServiceException
     *
     * @param message The message of the exception
     */
    public ServiceException( String message )
    {
        super(message);
    }

    /**
     * Constructor for ServiceException
     *
     * @param e The exception that will be encapsulated
     */
    public ServiceException( Throwable e )
    {
        super(e);
    }

    /**
     * Constructor for ServiceException
     *
     * @param message The message of the exception
     * @param e The exception that will be encapsulated
     */
    public ServiceException( String message, Throwable e ) { super(message, e); }

}

package be.peerassistedlearningti.common.model;

/**
 * Class used to encapsulate all exceptions thrown in a model class
 */
public class ModelException extends RuntimeException
{

    /**
     * Constructor for ModelException
     *
     * @param message The message of the exception
     */
    public ModelException( String message )
    {
        super( message );
    }

    /**
     * Constructor for ModelException
     *
     * @param e The exception that will be encapsulated
     */
    public ModelException( Throwable e )
    {
        super( e );
    }

    /**
     * Constructor for ModelException
     *
     * @param message The message of the exception
     * @param e       The exception that will be encapsulated
     */
    public ModelException( String message, Throwable e ) { super( message, e ); }

}

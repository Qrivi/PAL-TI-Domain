package be.peerassistedlearningti.model;

/**
 * Class that specifies the state of an application
 */
public enum ApplicationState
{

    PENDING,
    APPROVED,
    REJECTED;

    public static ApplicationState getByValue( String state )
    {
        for ( ApplicationState t : values() )
        {
            String s = t.toString();
            if ( s.equalsIgnoreCase( state ) )
            {
                return t;
            }
        }
        return null;
    }
}

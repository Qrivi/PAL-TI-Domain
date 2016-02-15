package be.peerassistedlearningti.model;

/**
 * Class that specifies the type of a user
 */
public enum UserType
{

    NORMAL,
    ADMIN;

    public static UserType getByValue( String type )
    {
        for ( UserType u : values() )
        {
            if ( u.toString()
                    .equalsIgnoreCase( type ) )
            {
                return u;
            }
        }
        return null;
    }

}

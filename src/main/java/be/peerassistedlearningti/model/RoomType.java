package be.peerassistedlearningti.model;

/**
 * Class that specifies the type of a room
 */
public enum RoomType
{

    COMPUTER,
    CHEMISTRY,
    PLAIN;

    public static RoomType getByValue( String type )
    {
        for ( RoomType t : values() )
        {
            String s = t.toString();
            if ( s.equalsIgnoreCase( type ) )
                return t;
        }
        return null;
    }

}

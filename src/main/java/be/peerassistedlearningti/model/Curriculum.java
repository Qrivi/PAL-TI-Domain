package be.peerassistedlearningti.model;

/**
 * Class that specifies the curriculums
 */
public enum Curriculum
{

    TI( "Toegepaste Informatica" ),
    MANAGEMENT( "Management" );

    private final String name;

    Curriculum( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static Curriculum getByName( String name )
    {
        for ( Curriculum c : values() )
        {
            String s = c.toString();
            if ( s.equalsIgnoreCase( name ) )
                return c;
        }
        return null;
    }
}

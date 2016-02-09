package be.peerassistedlearningti.common.model.jpa;

import be.peerassistedlearningti.common.model.identifiable.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class used to add JPA functionalities to an object
 */
@MappedSuperclass
public class JPAEntity <T extends Object> implements Identifiable<T>, Serializable
{

    /**
     * Adds JPA functionalities to the id field
     */
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "generatorName" )
    @TableGenerator( name = "generatorName", allocationSize = 1 )
    @Column( name = "id" )
    protected T id;

    /**
     * Sets the id of the JPAEntity
     *
     * @param id The id of the JPAEntity
     */
    public void setId( T id )
    {
        this.id = id;
    }

    /**
     * Gets the id of the JPAEntity
     *
     * @return The id
     */
    public T getId()
    {
        return id;
    }

    /**
     * Equals functionality (used in databases)
     *
     * @param obj The object to compare
     * @return False if the ids are not equal or are null
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null || id == null )
            return false;
        if ( obj instanceof JPAEntity )
        {
            if ( ( (JPAEntity) obj ).getId() == null )
                return false;
            return ( (JPAEntity) obj ).getId()
                    .equals( this.id );
        }
        return false;
    }

    /**
     * Hashcode functionality
     *
     * @return The hashcode of the object
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode( this.id );
        return hash;
    }

    /**
     * ToString functionality
     *
     * @return The string representation of the entity
     */
    @Override
    public String toString()
    {
        return "JPAEntity{id=" + id + "}";
    }

}

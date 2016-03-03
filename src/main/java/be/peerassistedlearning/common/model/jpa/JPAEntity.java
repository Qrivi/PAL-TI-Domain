/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearning.common.model.jpa;

import be.peerassistedlearning.common.model.identifiable.Identifiable;

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

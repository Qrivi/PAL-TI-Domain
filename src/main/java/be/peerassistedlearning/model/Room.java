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

package be.peerassistedlearning.model;

import be.peerassistedlearning.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "room" )
public class Room extends JPAEntity<Integer>
{

    @NotEmpty( message = "{NotEmpty.Room.name}" )
    @Column( name = "name", nullable = false )
    private String name;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Room.campus}" )
    @Column( name = "campus", nullable = false )
    private Campus campus;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Room.type}" )
    @Column( name = "type", nullable = false )
    private RoomType type;

    /**
     * Default constructor for Room
     */
    public Room() {}

    /**
     * Constructor for Room
     *
     * @param name   The name of the room
     * @param campus The campus of a room
     * @param type   The type of the room
     */
    public Room( String name, Campus campus, RoomType type )
    {
        this.name = name;
        this.campus = campus;
        this.type = type;
    }

    /**
     * Sets the name of the room
     *
     * @param name The new name of the room
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the campus of the room
     *
     * @param campus The new campus of the room
     */
    public void setCampus( Campus campus )
    {
        this.campus = campus;
    }

    /**
     * Sets the type of the room
     *
     * @param type The new type of the room
     */
    public void setType( RoomType type )
    {
        this.type = type;
    }

    /**
     * @return The name of the room
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The campus of the room
     */
    public Campus getCampus()
    {
        return campus;
    }

    /**
     * @return The type of the room
     */
    public RoomType getType()
    {
        return type;
    }
}

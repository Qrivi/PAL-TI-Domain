package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "room" )
public class Room extends JPAEntity<Integer>
{

    @NotEmpty( message = "NotEmpty.Room.room" )
    @Column( name = "room", nullable = false )
    private String name;

    @NotEmpty( message = "NotEmpty.Room.campus" )
    @Column( name = "room", nullable = false )
    private String campus;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "NotNull.Room.type" )
    @Column( name = "type" )
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
    public Room( String name, String campus, RoomType type )
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
    public void setCampus( String campus )
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
    public String getCampus()
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

package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Class used to specify an Image
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "image" )
public class Image extends JPAEntity<Integer>
{
    @Lob
    @Basic( fetch = FetchType.LAZY )
    @Column( name = "image", columnDefinition = "LONGBLOB" )
    private byte[] bytes;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "last_modified" )
    private Date lastModified;

    /**
     * Default empty constructor for JPA Entities
     */
    public Image() {}

    /**
     * Constructor for a image entity
     *
     * @param bytes        The bytes of the image
     * @param lastModified The date the image is last modified
     */
    public Image( byte[] bytes, Date lastModified )
    {
        this.bytes = bytes;
        this.lastModified = lastModified;
    }

    /**
     * Sets the bytes of the image
     *
     * @param bytes The bytes of the image
     */
    public void setBytes( byte[] bytes )
    {
        this.bytes = bytes;
    }

    /**
     * Sets the last modified date of the image
     *
     * @param lastModified The last modified date of the image
     */
    public void setLastModified( Date lastModified )
    {
        this.lastModified = lastModified;
    }

    /**
     * @return The bytes of the image
     */
    public byte[] getBytes()
    {
        return bytes;
    }

    /**
     * @return The date the image is last modified
     */
    public Date getLastModified()
    {
        return lastModified;
    }
}

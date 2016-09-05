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

import javax.persistence.*;
import java.util.Date;

/**
 * Class used to specify an Image
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "image" )
public class Image extends JPAEntity<Integer>{
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
    public Image(){
    }

    /**
     * Constructor for a image entity
     *
     * @param bytes        The bytes of the image
     * @param lastModified The date the image is last modified
     */
    public Image( byte[] bytes, Date lastModified ){
        this.bytes = bytes;
        this.lastModified = lastModified;
    }

    /**
     * @return The bytes of the image
     */
    public byte[] getBytes(){
        return bytes;
    }

    /**
     * Sets the bytes of the image
     *
     * @param bytes The bytes of the image
     */
    public void setBytes( byte[] bytes ){
        this.bytes = bytes;
    }

    /**
     * @return The date the image is last modified
     */
    public Date getLastModified(){
        return lastModified;
    }

    /**
     * Sets the last modified date of the image
     *
     * @param lastModified The last modified date of the image
     */
    public void setLastModified( Date lastModified ){
        this.lastModified = lastModified;
    }
}

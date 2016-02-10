package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Class used to specify a Student
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "student" )
public class Student extends JPAEntity<Integer>
{

    @NotEmpty( message = "NotEmpty.Student.name" )
    @Column( name = "name", nullable = false )
    private String name;

    @NotEmpty( message = "NotEmpty.Student.password" )
    @Column( name = "password", nullable = false )
    private String password;

    @NotEmpty( message = "NotEmpty.Student.salt" )
    @Column( name = "salt", nullable = false )
    private String salt;

    @NotEmpty( message = "NotEmpty.Student.email" )
    @Email( message = "Email.Student.email" )
    @Column( name = "email", unique = true, nullable = false )
    private String email;

    @Column( name = "admin" )
    private boolean admin;

    /**
     * Default empty constructor for JPA Entities
     */
    public Student() {}

    /**
     * Constructor for a student entity
     *
     * @param name     The name of the student
     * @param password The password of the student
     * @param email    The email of the student
     * @param admin    The admin value of the student
     */
    public Student( String name, String password, String email, boolean admin )
    {
        this.email = email;
        this.name = name;
        this.admin = admin;
        this.salt = new BigInteger( 130, new SecureRandom() ).toString( 20 );
        this.password = hashPassword( password );
    }

    /**
     * Hashes the plaintext password
     *
     * @param plainTextPassword The password that will be hashed
     * @return The password in hashed form.
     */
    private String hashPassword( String plainTextPassword )
    {
        if ( plainTextPassword == null )
            return null;

        MessageDigest digest = null;

        try
        {
            digest = MessageDigest.getInstance( "SHA-512" );
            digest.reset();
        } catch ( NoSuchAlgorithmException e ) { }

        digest.update( plainTextPassword.getBytes() );

        if ( salt != null )
            digest.update( salt.getBytes() );

        return ( new BigInteger( 1, digest.digest() ).toString( 40 ) );
    }

    /**
     * Check if the given password is valid
     *
     * @param plainTextPassword The plaintext password
     * @return True if the plaintext password matches the saved password
     */
    public boolean isPasswordValid( String plainTextPassword )
    {
        return hashPassword( plainTextPassword ).equals( password );
    }

    /**
     * Sets the name of the Student
     *
     * @param name The name of the Student
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the password of the Student
     *
     * @param password The password of the student
     */
    public void setPassword( String password )
    {
        if ( salt == null )
            this.salt = new BigInteger( 130, new SecureRandom() ).toString( 20 );
        this.password = hashPassword( password );
    }

    /**
     * Sets the email of the student
     *
     * @param email The email of the student
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * Sets the admin value of the student
     *
     * @param admin The admin value of the student
     */
    public void setAdmin( boolean admin )
    {
        this.admin = admin;
    }

    /**
     * Gets the name of the Student
     *
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the password of the Student
     *
     * @return The password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Gets the email of the student
     *
     * @return The email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Gets the admin value of the student
     *
     * @return If the student is an admin
     */
    public boolean isAdmin()
    {
        return admin;
    }

}

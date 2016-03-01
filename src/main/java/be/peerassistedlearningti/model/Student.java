package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Class used to specify a Student
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "student" )
public class Student extends JPAEntity<Integer>
{

    @NotEmpty( message = "{NotEmpty.Student.name}" )
    @Column( name = "name", nullable = false )
    private String name;

    @NotEmpty( message = "{NotEmpty.Student.password}" )
    @Column( name = "password", nullable = false )
    private String password;

    @Lob
    @Column( name = "avatar", nullable = true )
    private byte[] avatar;

    @NotEmpty( message = "{NotEmpty.Student.salt}" )
    @Column( name = "salt", nullable = false )
    private String salt;

    @NotEmpty( message = "{NotEmpty.Student.email}" )
    @Email( message = "{Email.Student.email}" )
    @Column( name = "email", unique = true, nullable = false )
    private String email;

    @Enumerated( EnumType.STRING )
    @NotNull( message = "{NotNull.Student.type}" )
    @Column( name = "type", nullable = false )
    private UserType type;

    @Valid
    @OneToOne( mappedBy = "student", cascade = CascadeType.REMOVE )
    private Tutor tutor;

    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "student_subscriptions",
            joinColumns = @JoinColumn( name = "student_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn( name = "subscription_id", referencedColumnName = "id" ) )
    private Set<Course> subscriptions;

    @Valid
    @OneToMany( mappedBy = "student", orphanRemoval = true )
    private Set<Review> reviews;

    @Valid
    @OneToMany( mappedBy = "student", orphanRemoval = true )
    private Set<Request> requests;

    @Valid
    @OneToMany( mappedBy = "student", orphanRemoval = true )
    private Set<Application> applications;

    @Valid
    @ManyToMany( mappedBy = "bookings" )
    private Set<Lesson> bookings;

    @Column( name = "reset_token", unique = true )
    private String resetToken;

    @Column( name = "reset_salt" )
    private String resetSalt;

    @Column( name = "reset_expiration" )
    private Date resetTokenExpiration;

    @NotEmpty( message = "{NotEmpty.Student.securityToken}" )
    @Column( name = "security_token", unique = true, nullable = false )
    private String securityToken;

    @NotEmpty( message = "{NotEmpty.Student.profileIdentifier}" )
    @Column( name = "profile_identifier", unique = true, nullable = false )
    private String profileIdentifier;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "last_updated" )
    private Date lastUpdated;

    /**
     * Default empty constructor for JPA Entities
     */
    public Student() {}

    /**
     * Constructor for a student entity
     *
     * @param name              The name of the student
     * @param password          The password of the student
     * @param email             The email of the student
     * @param profileIdentifier The profile identifier of the student
     * @param type              The user type of the student
     */
    public Student( String name, String password, String email, String profileIdentifier, UserType type )
    {
        this.email = email;
        this.name = name;
        this.profileIdentifier = profileIdentifier;
        this.type = type;
        this.salt = new BigInteger( 130, new SecureRandom() ).toString( 20 );
        this.password = createHash( password, salt );
        this.securityToken = new BigInteger( 130, new SecureRandom() ).toString( 20 );
    }

    /**
     * Hashes the plaintext password
     *
     * @param plainTextPassword The password that will be hashed
     * @return The password in hashed form.
     */
    private String createHash( String plainTextPassword, String salt )
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
        return createHash( plainTextPassword, salt ).equals( password );
    }

    /**
     * Sets the resetTokenExpiration to 1 hour in the future and creates a reset token.
     *
     * @return The plaintext token
     */
    public String issuePasswordReset()
    {
        resetTokenExpiration = new Date( new Date().getTime() + TimeUnit.HOURS.toMillis( 1 ) );
        resetSalt = new BigInteger( 130, new SecureRandom() ).toString( 20 );

        String plainTextToken = email + new BigInteger( 130, new SecureRandom() ).toString( 20 );

        try
        {
            plainTextToken = Base64.getUrlEncoder().encodeToString( plainTextToken.getBytes( "utf-8" ) );
        } catch ( UnsupportedEncodingException e ) {}

        resetToken = createHash( plainTextToken, resetSalt );

        return plainTextToken;
    }

    /**
     * Check if the given reset token is valid and did not pass expiration
     *
     * @param plainTextToken The  plaintext reset token to verify
     * @return True if the plaintext token was correct and did not pass expiration
     */
    public boolean validatePasswordReset( String plainTextToken )
    {
        if ( resetToken != null && plainTextToken != null &&
                resetTokenExpiration.getTime() - new Date().getTime() > 0 )
        {
            boolean valid = createHash( plainTextToken, resetSalt ).equals( resetToken );
            resetToken = valid ? null : resetToken;
            return valid;
        }
        return false;
    }

    /**
     * Changes the security token to a new random string
     */
    public void changeSecurityToken()
    {
        this.securityToken = new BigInteger( 130, new SecureRandom() ).toString( 20 );
    }

    /**
     * Removed the student object from the lesson
     */
    @PreRemove
    private void removeBookings()
    {
        if ( bookings != null )
        {
            for ( Lesson l : bookings )
            {
                l.getBookings().remove( this );
            }
        }
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
     * Sets the profile identifier of the Student
     *
     * @param profileIdentifier the profile identifier of the Student
     */
    public void setProfileIdentifier( String profileIdentifier )
    {
        this.profileIdentifier = profileIdentifier;
    }

    /**
     * Sets the avatar of the Student
     *
     * @param avatar The avatar of the Student
     */
    public void setAvatar( byte[] avatar )
    {
        this.avatar = avatar;
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
        this.password = createHash( password, salt );
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
     * Sets the user type of the student
     *
     * @param type The user type of the student
     */
    public void setType( UserType type )
    {
        this.type = type;
    }

    /**
     * Sets the subscription set of the student
     *
     * @param subscriptions The Set containing the student's subscriptions
     */
    public void setSubscriptions( Set<Course> subscriptions )
    {
        this.subscriptions = subscriptions;
    }

    /**
     * Sets the date the student is last updated
     *
     * @param lastUpdated The date the student is last updated
     */
    public void setLastUpdated( Date lastUpdated )
    {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return The name of the student
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the avatar of the Student
     */
    public byte[] getAvatar()
    {
        return avatar;
    }

    /**
     * @return The password of the student
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @return The email of the student
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return The user type of the student
     */
    public UserType getType()
    {
        return type;
    }

    /**
     * @return The reset token of the student used to reset the password
     */
    public String getResetToken()
    {
        return resetToken;
    }

    /**
     * @return The expiration date of the reset token
     */
    public Date getResetTokenExpiration()
    {
        return resetTokenExpiration;
    }

    /**
     * @return The subscriptions from the student
     */
    public Set<Course> getSubscriptions()
    {
        return subscriptions;
    }

    /**
     * @return The security token of the student
     */
    public String getSecurityToken()
    {
        return securityToken;
    }

    /**
     * @return The profile identifier of the student
     */
    public String getProfileIdentifier()
    {
        return profileIdentifier;
    }

    /**
     * @return The date the student is last updated
     */
    public Date getLastUpdated()
    {
        return lastUpdated;
    }

    /**
     * @return The tutor object of the student
     */
    public Tutor getTutor()
    {
        return tutor;
    }

    /**
     * @return The reviews of the student
     */
    public Set<Review> getReviews()
    {
        return reviews;
    }

    /**
     * @return The requests of the student
     */
    public Set<Request> getRequests()
    {
        return requests;
    }

    /**
     * @return The applications of the student
     */
    public Set<Application> getApplications()
    {
        return applications;
    }

    /**
     * @return The bookings of the student
     */
    public Set<Lesson> getBookings()
    {
        return bookings;
    }
}

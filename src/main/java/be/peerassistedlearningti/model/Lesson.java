package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Class used to specify a Lesson
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "lesson" )
public class Lesson extends JPAEntity<Integer>
{

    @NotNull( message = "{NotNull.Lesson.date}" )
    @Column( name = "date", nullable = false )
    private Date date;

    @NotNull( message = "{NotNull.Lesson.name}" )
    @Column( name = "name", nullable = false )
    private String name;

    @Min( value = 1, message = "{Min.Lesson.duration}" )
    @Column( name = "duration" )
    private long duration;

    @Min( value = 1, message = "{Min.Lesson.maxParticipants}" )
    @Column( name = "max_participants", nullable = false )
    private int maxParticipants;

    @Valid
    @NotNull( message = "{NotNull.Lesson.course}" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Valid
    @NotNull( message = "{NotNull.Lesson.tutor}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "tutor_id" )
    private Tutor tutor;

    @Valid
    @OneToMany( mappedBy = "lesson", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Set<Booking> bookings;

    @Valid
    @NotNull( message = "{NotNull.Lesson.room}" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "room_id" )
    private Room room;

    @Valid
    @NotNull( message = "NotNull.Lesson.backupRoom" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "backup_room_id" )
    private Room backupRoom;

    /**
     * Default constructor for Lesson
     */
    public Lesson() {}

    /**
     * Constructor for Lesson
     *
     * @param date            The date of the lesson
     * @param name            The name of the lesson
     * @param duration        The duration of the lesson
     * @param course          The course of the lesson
     * @param maxParticipants The maximum number of participants of the course
     * @param tutor           The tutor of the course
     * @param room            The room for the lesson
     * @param backupRoom      The backup room for the lesson
     */
    public Lesson( Date date, String name, long duration, Course course, int maxParticipants, Tutor tutor, Room room, Room backupRoom )
    {
        this.date = date;
        this.name = name;
        this.duration = duration;
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.tutor = tutor;
        this.room = room;
        this.backupRoom = backupRoom;
    }

    /**
     * Adds a given booking to a lesson
     *
     * @param booking The given booking to be added
     * @return true if this set did not already contain the specified booking
     */
    public boolean addBooking( Booking booking )
    {
        return bookings.add( booking );
    }

    /**
     * Removes a given booking of a lesson
     *
     * @param booking The given booking to be removed
     * @return true if this set contained the specified booking
     */
    public boolean removeBooking( Booking booking )
    {
        return bookings.remove( booking );
    }

    /**
     * Sets the date and time of the lesson
     *
     * @param date The date of the lesson
     */
    public void setDate( Date date )
    {
        this.date = date;
    }

    /**
     * Sets the name of the lesson
     *
     * @param name The name of the lesson
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the duration of the lesson
     *
     * @param duration The duration of the lesson
     */
    public void setDuration( long duration )
    {
        this.duration = duration;
    }

    /**
     * Sets the course of the lesson
     *
     * @param course The course of the lesson
     */
    public void setCourse( Course course )
    {
        this.course = course;
    }

    /**
     * Sets the maximum number of participants of the lesson
     *
     * @param maxParticipants The maximum number of participants of the lesson
     */
    public void setMaxParticipants( int maxParticipants )
    {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Sets the tutor of the lesson
     *
     * @param tutor The tutor of the lesson
     */
    public void setTutor( Tutor tutor )
    {
        this.tutor = tutor;
    }

    /**
     * Sets the bookings of the lesson
     *
     * @param bookings The bookings of the lesson
     */
    public void setBookings( Set<Booking> bookings )
    {
        this.bookings = bookings;
    }

    /**
     * Sets the room of the lesson
     *
     * @param room
     */
    public void setRoom( Room room )
    {
        this.room = room;
    }

    /**
     * Sets the backup room (when the room is occupied) of the lesson
     *
     * @param backupRoom The backup room of the lesson
     */
    public void setBackupRoom( Room backupRoom )
    {
        this.backupRoom = backupRoom;
    }

    /**
     * @return The date and time of the lesson
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @return The duration of the lesson
     */
    public long getDuration()
    {
        return duration;
    }

    /**
     * @return The course of the lesson
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * @return The maximum number of participants of the lesson
     */
    public int getMaxParticipants()
    {
        return maxParticipants;
    }

    /**
     * @return The tutor and time of the lesson
     */
    public Tutor getTutor()
    {
        return tutor;
    }

    /**
     * @return The set of bookings
     */
    public Set<Booking> getBookings()
    {
        return bookings;
    }

    /**
     * @return The room of the lesson
     */
    public Room getRoom()
    {
        return room;
    }

    /**
     * @return The backup room of the lesson
     */
    public Room getBackupRoom()
    {
        return backupRoom;
    }

}

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

    @NotNull( message = "NotNull.Lesson.date" )
    @Column( name = "date", nullable = false )
    private Date date;

    @Min( value = 1, message = "Min.Lesson.duration" )
    @Column( name = "duration" )
    private long duration;

    @Column( name = "max_participants", nullable = false )
    @Min( value = 1, message = "Min.Lesson.maxParticipants" )
    private int maxParticipants;

    @Valid
    @NotNull( message = "NotNull.Lesson.course" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Valid
    @NotNull( message = "NotNull.Lesson.tutor" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "tutor_id" )
    private Tutor tutor;

    @Valid
    @OneToMany( mappedBy = "lesson", fetch = FetchType.LAZY )
    private Set<Booking> bookings;

    @Valid
    @NotNull( message = "NotNull.Lesson.room" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "room_id" )
    private Room room;

    @Valid
    @NotNull( message = "NotNull.Lesson.backupRoom" )
    @OneToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "room_id" )
    private Room backupRoom;

    /**
     * Default constructor for Lesson
     */
    public Lesson() {}

    /**
     * Constructor for Lesson
     *
     * @param date            The date of the lesson
     * @param duration        The duration of the lesson
     * @param course          The course of the lesson
     * @param maxParticipants The max participants of the course
     * @param tutor           The tutor of the course
     * @param bookings        The bookings for the lesson
     * @param room            The room for the lesson
     * @param backupRoom      The backup room for the lesson
     */
    public Lesson( Date date, long duration, Course course, int maxParticipants, Tutor tutor, Set<Booking> bookings, Room room, Room backupRoom )
    {
        this.date = date;
        this.duration = duration;
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.tutor = tutor;
        this.bookings = bookings;
        this.room = room;
        this.backupRoom = backupRoom;
    }

    /**
     * Sets the date and time of the lesson
     *
     * @param date
     */
    public void setDate( Date date )
    {
        this.date = date;
    }

    /**
     * Sets the duration of the lesson
     *
     * @param duration
     */
    public void setDuration( long duration )
    {
        this.duration = duration;
    }

    /**
     * Sets the course of the lesson
     *
     * @param course
     */
    public void setCourse( Course course )
    {
        this.course = course;
    }

    /**
     * Sets the maximum number of participants of the lesson
     *
     * @param maxParticipants
     */
    public void setMaxParticipants( int maxParticipants )
    {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Sets the tutor of the lesson
     *
     * @param tutor
     */
    public void setTutor( Tutor tutor )
    {
        this.tutor = tutor;
    }

    /**
     * Sets the bookings of the lesson
     *
     * @param bookings
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
    public void setRoom( String room )
    {
        this.room = room;
    }

    /**
     * Sets the backup room (when the room is occupied) of the lesson
     *
     * @param backupRoom
     */
    public void setBackupRoom( String backupRoom )
    {
        this.backupRoom = backupRoom;
    }

    /**
     * Gets the date and time of the lesson
     *
     * @return Date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Gets the duration of the lesson
     *
     * @return duration
     */
    public long getDuration()
    {
        return duration;
    }

    /**
     * Gets the course of the lesson
     *
     * @return Course
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * Gets the maximum number of participants of the lesson
     *
     * @return maximum number of participants
     */
    public int getMaxParticipants()
    {
        return maxParticipants;
    }

    /**
     * Gets the tutor and time of the lesson
     *
     * @return Tutor
     */
    public Tutor getTutor()
    {
        return tutor;
    }

    public Set<Booking> getBookings()
    {
        return bookings;
    }

    /**
     * Gets the room of the lesson
     *
     * @return room
     */
    public Room getRoom()
    {
        return room;
    }

    /**
     * Gets the backup room (when the room is occupied) of the lesson
     *
     * @return backupRoom
     */
    public Room getBackupRoom()
    {
        return backupRoom;
    }

}

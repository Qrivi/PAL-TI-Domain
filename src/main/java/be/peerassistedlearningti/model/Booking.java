package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "booking" )
public class Booking extends JPAEntity<Integer>
{
    @Valid
    @NotNull( message = "{NotNull.Booking.lesson}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "lesson_id", nullable = false )
    private Lesson lesson;

    @Valid
    @NotNull( message = "{NotNull.Booking.student}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id", nullable = false )
    private Student student;

    /**
     * Default constructor for Booking
     */
    public Booking() {}

    /**
     * Constructor for a Booking entity without review
     *
     * @param lesson  The lesson of the booking
     * @param student The student of the booking
     */
    public Booking( Lesson lesson, Student student )
    {
        this.lesson = lesson;
        this.student = student;
    }

    /**
     * Sets the lesson of the booking
     *
     * @param lesson The lesson of the booking
     */
    public void setLesson( Lesson lesson )
    {
        this.lesson = lesson;
    }

    /**
     * Sets the student of the booking
     *
     * @param student The student of the booking
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

    /**
     * @return The lesson of the booking
     */
    public Lesson getLesson()
    {
        return lesson;
    }

    /**
     * @return The student of the booking
     */
    public Student getStudent()
    {
        return student;
    }

}

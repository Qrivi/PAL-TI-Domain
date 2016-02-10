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
    @NotNull( message = "NotNull.Booking.lesson" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "lesson_id", nullable = false )
    private Lesson lesson;

    @Valid
    @NotNull( message = "NotNull.Booking.student" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "student_id", nullable = false )
    private Student student;

    /**
     * Default constructor for Booking
     */
    public Booking() {}

    /**
     * Constructor for a Booking entity without review
     *
     * @param lesson
     * @param student
     */
    public Booking( Lesson lesson, Student student )
    {
        this.lesson = lesson;
        this.student = student;
    }

    /**
     * Sets the lesson of the booking
     *
     * @return Lesson
     */
    public Lesson getLesson()
    {
        return lesson;
    }

    /**
     * Sets the student of the booking
     *
     * @return student
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * Gets the lesson of the booking
     *
     * @param lesson
     */
    public void setLesson( Lesson lesson )
    {
        this.lesson = lesson;
    }

    /**
     * Gets the student of the booking
     *
     * @param student
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

}

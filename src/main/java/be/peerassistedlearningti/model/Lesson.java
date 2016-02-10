package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Class used to specify a Lesson
 *
 * @see JPAEntity
 */
@Entity
@Table(name = "lesson")
public class Lesson {
    @NotNull(message = "NotNull.Lesson.date")
    @Column(name="date", nullable = false)
    private Date date;

    @Valid
    @NotNull(message = "NotNull.Lesson.course")
    @OneToOne
    @JoinColumn(name="course_id")
    private Course course;

    @Column(name="maxParticipants", nullable = true)
    private int maxParticipants;

    @Valid
    @NotNull(message = "NotNull.Lesson.tutor")
    private Tutor tutor;

    @Valid
    @NotNull(message = "NotNull.Lesson.bookings")
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Set<Booking> bookings;

    public Lesson(){}

    /**
     * Constructor for a Lesson entity
     *
     * @param date
     * @param course
     * @param maxParticipants
     * @param tutor
     * @param bookings
     */
    public Lesson(Date date, Course course, int maxParticipants, Tutor tutor, Set<Booking> bookings) {
        this.date = date;
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.tutor = tutor;
        this.bookings = bookings;
    }

    public Date getDate() {
        return date;
    }

    public Course getCourse() {
        return course;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}

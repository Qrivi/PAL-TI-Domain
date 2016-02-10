package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "booking")
public class Booking extends JPAEntity{
    @Valid
    @NotNull(message = "NotNull.Booking.lesson")
    @OneToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Valid
    @NotNull(message = "NotNull.Booking.student")
    @OneToMany
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Valid
    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    /**
     * Default constructor for Booking
     */
    public Booking(){}

    /**
     * Constructor for a Booking entity without review
     *
     * @param lesson
     * @param student
     */
    public Booking(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
    }

    /**
     * Constructor for a Booking entity
     *
     * @param lesson
     * @param student
     * @param review
     */
    public Booking(Lesson lesson, Student student, Review review) {
        this(lesson, student);
        this.review = review;
    }

    /**
     * Sets the lesson of the booking
     *
     * @return Lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Sets the student of the booking
     *
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the review of the booking
     *
     * @return review
     */
    public Review getReview() {
        return review;
    }


    /**
     * Gets the lesson of the booking
     *
     * @param lesson
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Gets the student of the booking
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the review of the booking
     *
     * @param review
     */
    public void setReview(Review review) {
        this.review = review;
    }
}

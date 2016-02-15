package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Class used to specify a Review
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "review" )
public class Review extends JPAEntity<Integer>
{

    @NotEmpty( message = "{NotEmpty.Review.text}" )
    @Column( name = "text", nullable = false )
    @Size( min = 10, max = 140, message = "{Size.Review.text}" )
    private String text;

    @Valid
    @NotNull( message = "{NotNull.Review.student}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Review.lesson" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "lesson_id" )
    private Lesson lesson;

    @NotNull( message = "{NotNull.Review.date}" )
    @Column( name = "date" )
    private Date date;

    @NotNull( message = "{NotNull.Review.contentScore}" )
    @Min( value = 1, message = "{Min.Review.contentScore}" )
    @Max( value = 10, message = "{Max.Review.contentScore}" )
    @Column( name = "content_score" )
    private int contentScore;

    @NotNull( message = "{NotNull.Review.tutorScore}" )
    @Min( value = 1, message = "{Min.Review.tutorScore}" )
    @Max( value = 10, message = "{Max.Review.tutorScore}" )
    @Column( name = "tutor_score" )
    private int tutorScore;

    @NotNull( message = "{NotNull.Review.engagementScore}" )
    @Min( value = 1, message = "{Min.Review.engagementScore}" )
    @Max( value = 10, message = "{Max.Review.engagementScore}" )
    @Column( name = "engagement_score" )
    private int engagementScore;

    @NotNull( message = "{NotNull.Review.atmosphereScore}" )
    @Min( value = 1, message = "{Min.Review.atmosphereScore}" )
    @Max( value = 10, message = "{Max.Review.atmosphereScore}" )
    @Column( name = "atmosphere_score" )
    private int atmosphereScore;

    /**
     * Default constructor for Review
     */
    public Review() {}

    /**
     * Constructor for Review
     *
     * @param text            The text of the review
     * @param student         The student of the review
     * @param lesson          The lesson of the review
     * @param contentScore    The score regarding the lesson's content
     * @param tutorScore      The score regarding the lesson's tutor
     * @param engagementScore The score regarding the lesson's engagement
     * @param atmosphereScore The score regarding the lesson's atmosphere
     */
    public Review( String text, Student student, Lesson lesson, int contentScore, int tutorScore, int engagementScore, int atmosphereScore )
    {
        this( text, student, lesson, contentScore, tutorScore, engagementScore, atmosphereScore, new Date() );
    }

    /**
     * Constructor for Review
     *
     * @param text            The text of the review
     * @param student         The student of the review
     * @param lesson          The lesson of the review
     * @param contentScore    The score regarding the lesson's content
     * @param tutorScore      The score regarding the lesson's tutor
     * @param engagementScore The score regarding the lesson's engagement
     * @param atmosphereScore The score regarding the lesson's atmosphere
     * @param date            The date of the review
     */
    public Review( String text, Student student, Lesson lesson, int contentScore, int tutorScore, int engagementScore, int atmosphereScore, Date date )
    {
        this.text = text;
        this.student = student;
        this.lesson = lesson;
        this.contentScore = contentScore;
        this.tutorScore = tutorScore;
        this.engagementScore = engagementScore;
        this.atmosphereScore = atmosphereScore;
        this.date = date;
    }

    /**
     * Sets the text of the review
     *
     * @param text The text of the review
     */
    public void setText( String text )
    {
        this.text = text;
    }

    /**
     * Sets the student of the review
     *
     * @param student The student of the review
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

    /**
     * Sets the lesson of review
     *
     * @param lesson The lesson of the review
     */

    public void setLesson( Lesson lesson )
    {
        this.lesson = lesson;
    }

    /**
     * Sets the date of the review
     *
     * @param date The date of the review
     */
    public void setDate( Date date )
    {
        this.date = date;
    }

    /**
     * Sets the  content score of the review
     *
     * @param contentScore The content score @min 1, @max 10
     */
    public void setContentScore( int contentScore )
    {
        this.contentScore = contentScore;
    }

    /**
     * Sets the tutor score of the review
     *
     * @param tutorScore The tutor score @min 1, @max 10
     */
    public void setTutorScore( int tutorScore )
    {
        this.tutorScore = tutorScore;
    }

    /**
     * Sets the engagement score of the review
     *
     * @param engagementScore The engagement score @min 1, @max 10
     */
    public void setEngagementScore( int engagementScore )
    {
        this.engagementScore = engagementScore;
    }

    /**
     * Sets the atmosphere score of the review
     *
     * @param atmosphereScore The atmosphere score @min 1, @max 10
     */
    public void setAtmosphereScore( int atmosphereScore )
    {
        this.atmosphereScore = atmosphereScore;
    }

    /**
     * Gets the text of the review
     *
     * @return The text of the review
     */
    public String getText()
    {
        return text;
    }

    /**
     * Gets the student of the review
     *
     * @return the student of the review
     * @see Student
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * Gets the lesson of the review
     *
     * @return The lesson of the review
     * @see Lesson
     */
    public Lesson getLesson()
    {
        return lesson;
    }

    /**
     * Gets the date of the review
     *
     * @return The date of the review
     * @see Date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Gets the content score of the review
     *
     * @return The content score of the review
     */
    public int getContentScore()
    {
        return contentScore;
    }

    /**
     * Gets the tutor score of the review
     *
     * @return The tutor score of the review
     */
    public int getTutorScore()
    {
        return tutorScore;
    }

    /**
     * Gets the engagement score of the review
     *
     * @return The engagement score of the review
     */
    public int getEngagementScore()
    {
        return engagementScore;
    }

    /**
     * Gets the atmosphere score of the review
     *
     * @return The atmosphere score of the review
     */
    public int getAtmosphereScore()
    {
        return atmosphereScore;
    }
}

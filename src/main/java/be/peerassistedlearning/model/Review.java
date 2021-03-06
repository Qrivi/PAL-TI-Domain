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

import be.peerassistedlearning.common.model.archivable.Archivable;
import be.peerassistedlearning.common.model.jpa.JPAEntity;

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
public class Review extends JPAEntity<Integer> implements Archivable{

    @NotNull( message = "{NotNull.Review.text}" )
    @Column( name = "text", nullable = false )
    @Size( min = 10, max = 140, message = "{Size.Review.text}" )
    private String text;

    @Valid
    @NotNull( message = "{NotNull.Review.student}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "student_id" )
    private Student student;

    @Valid
    @NotNull( message = "{NotNull.Review.lesson}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "lesson_id" )
    private Lesson lesson;

    @NotNull( message = "{NotNull.Review.date}" )
    @Column( name = "date" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date date;

    @Min( value = 1, message = "{Min.Review.contentScore}" )
    @Max( value = 10, message = "{Max.Review.contentScore}" )
    @Column( name = "content_score" )
    private int contentScore;

    @Min( value = 1, message = "{Min.Review.tutorScore}" )
    @Max( value = 10, message = "{Max.Review.tutorScore}" )
    @Column( name = "tutor_score" )
    private int tutorScore;

    @Min( value = 1, message = "{Min.Review.engagementScore}" )
    @Max( value = 10, message = "{Max.Review.engagementScore}" )
    @Column( name = "engagement_score" )
    private int engagementScore;

    @Min( value = 1, message = "{Min.Review.atmosphereScore}" )
    @Max( value = 10, message = "{Max.Review.atmosphereScore}" )
    @Column( name = "atmosphere_score" )
    private int atmosphereScore;

    @Column( name = "anonymous" )
    private boolean anonymous;

    /**
     * Default constructor for Review
     */
    public Review(){
    }

    /**
     * Constructor for Review without date
     *
     * @param text            The text of the review
     * @param student         The student of the review
     * @param lesson          The lesson of the review
     * @param contentScore    The score regarding the lesson's content
     * @param tutorScore      The score regarding the lesson's tutor
     * @param engagementScore The score regarding the lesson's engagement
     * @param atmosphereScore The score regarding the lesson's atmosphere
     * @param anonymous       Indicates if the name may be shown
     */
    public Review( String text, Student student, Lesson lesson, int contentScore, int tutorScore, int engagementScore, int atmosphereScore, boolean anonymous ){
        this( text, student, lesson, contentScore, tutorScore, engagementScore, atmosphereScore, anonymous, new Date() );
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
     * @param anonymous       Indicates if the name may be shown
     * @param date            The date of the review
     */
    public Review( String text, Student student, Lesson lesson, int contentScore, int tutorScore, int engagementScore, int atmosphereScore, boolean anonymous, Date date ){
        this.text = text;
        this.student = student;
        this.lesson = lesson;
        this.contentScore = contentScore;
        this.tutorScore = tutorScore;
        this.engagementScore = engagementScore;
        this.atmosphereScore = atmosphereScore;
        this.anonymous = anonymous;
        this.date = date;
    }

    /**
     * Gets the text of the review
     *
     * @return The text of the review
     */
    public String getText(){
        return text;
    }

    /**
     * Sets the text of the review
     *
     * @param text The text of the review
     */
    public void setText( String text ){
        this.text = text;
    }

    /**
     * Gets the student of the review
     *
     * @return the student of the review
     * @see Student
     */
    public Student getStudent(){
        return student;
    }

    /**
     * Sets the student of the review
     *
     * @param student The student of the review
     */
    public void setStudent( Student student ){
        this.student = student;
    }

    /**
     * Gets the lesson of the review
     *
     * @return The lesson of the review
     * @see Lesson
     */
    public Lesson getLesson(){
        return lesson;
    }

    /**
     * Sets the lesson of review
     *
     * @param lesson The lesson of the review
     */

    public void setLesson( Lesson lesson ){
        this.lesson = lesson;
    }

    /**
     * Gets the date of the review
     *
     * @return The date of the review
     * @see Date
     */
    public Date getDate(){
        return date;
    }

    /**
     * Sets the date of the review
     *
     * @param date The date of the review
     */
    public void setDate( Date date ){
        this.date = date;
    }

    /**
     * Gets the content score of the review
     *
     * @return The content score of the review
     */
    public int getContentScore(){
        return contentScore;
    }

    /**
     * Sets the  content score of the review
     *
     * @param contentScore The content score @min 1, @max 10
     */
    public void setContentScore( int contentScore ){
        this.contentScore = contentScore;
    }

    /**
     * Gets the tutor score of the review
     *
     * @return The tutor score of the review
     */
    public int getTutorScore(){
        return tutorScore;
    }

    /**
     * Sets the tutor score of the review
     *
     * @param tutorScore The tutor score @min 1, @max 10
     */
    public void setTutorScore( int tutorScore ){
        this.tutorScore = tutorScore;
    }

    /**
     * Gets the engagement score of the review
     *
     * @return The engagement score of the review
     */
    public int getEngagementScore(){
        return engagementScore;
    }

    /**
     * Sets the engagement score of the review
     *
     * @param engagementScore The engagement score @min 1, @max 10
     */
    public void setEngagementScore( int engagementScore ){
        this.engagementScore = engagementScore;
    }

    /**
     * Gets the atmosphere score of the review
     *
     * @return The atmosphere score of the review
     */
    public int getAtmosphereScore(){
        return atmosphereScore;
    }

    /**
     * Sets the atmosphere score of the review
     *
     * @param atmosphereScore The atmosphere score @min 1, @max 10
     */
    public void setAtmosphereScore( int atmosphereScore ){
        this.atmosphereScore = atmosphereScore;
    }

    /**
     * Gets if the review is anonymous
     *
     * @return The anonymous boolean of review
     */
    public boolean isAnonymous(){
        return anonymous;
    }

    /**
     * Sets if the review is anonymous
     *
     * @param anonymous If the review is anonymous
     */
    public void setAnonymous( boolean anonymous ){
        this.anonymous = anonymous;
    }

    /**
     * @return The date to be used by the timeline
     */
    public Date getArchiveDate(){
        return date;
    }
}

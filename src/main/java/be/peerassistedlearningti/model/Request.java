package be.peerassistedlearningti.model;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Class used to specify a Request
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "request" )
public class Request extends JPAEntity<Integer>
{
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "request_upvotes",
            joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> upvotes;

    @NotNull(message = "{NotNull.Request.title}")
    @Size( min = 3, max = 50, message = "{Size.Request.title}" )
    @Column( name = "title" )
    private String title;

    @NotNull( message = "{NotNull.Request.description}" )
    @Size( min = 10, max = 300, message = "{Size.Request.text}" )
    @Column( name = "description" )
    private String description;

    @Valid
    @NotNull( message = "{NotNull.Request.course}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Valid
    @NotNull( message = "{NotNull.Request.student}" )
    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH , CascadeType.MERGE } )
    @JoinColumn( name = "student_id" )
    private Student student;

    @NotNull( message = "{NotNull.Request.creationDate}" )
    @Column( name = "creation_date" )
    private Date creationDate;

    @Valid
    @OneToOne(mappedBy = "request")
    private Lesson lesson;

    /**
     * Default constructor for Request
     */
    public Request() {}

    /**
     * Constructor for Request
     *
     * @param title       The title of the request
     * @param description The description of the request
     * @param course      The course of the request
     * @param student     The student of the request
     */
    public Request(String title, String description, Course course, Student student )
    {
        this.title = title;
        this.description = description;
        this.course = course;
        this.student = student;
        this.creationDate = new Date();
    }

    /**
     * Constructor for Request
     *
     * @param title       The title of the request
     * @param description  The description of the request
     * @param course       The course of the request
     * @param student      The student of the request
     * @param creationDate The creation date of the request
     */
    public Request(String title, String description, Course course, Student student, Date creationDate )
    {
        this( title, description, course, student );
        this.creationDate = creationDate;
    }

    /**
     * Adds the given student to this request's set of up-votes
     *
     * @param student The new student who up-voted this request
     * @return true if this set did not already contain the specified student
     * @see Set
     */
    public boolean upvote(Student student) {
        return upvotes.add(student);
    }

    /**
     * Removes the given student from this request's set of up-votes
     *
     * @param student The student to be remove from this request's up-votes set
     * @return true if this set contained the specified student
     */
    public boolean removeUpvote(Student student) {
        return upvotes.remove(student);
    }

    /**
     * @return the upvotes of the request
     */
    public Set<Student> getUpvotes()
    {
        return upvotes;
    }

    public void setUpvotes(Set<Student> upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * @return the title of the request
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the request
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of the request
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description of the request
     *
     * @param description the new description of the request
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @return the course of the request
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * Sets the course of the request
     *
     * @param course the new course of the request
     */
    public void setCourse( Course course )
    {
        this.course = course;
    }

    /**
     * @return the student of the request
     */
    public Student getStudent()
    {
        return student;
    }

    /**
     * Sets the student of the request
     *
     * @param student the new student of the request
     */
    public void setStudent( Student student )
    {
        this.student = student;
    }

    /**
     * @return the creation date of the request
     */
    public Date getCreationDate()
    {
        return creationDate;
    }

    /**
     * Sets the creation date of the request
     *
     * @param creationDate the new creation date of the request
     */
    public void setCreationDate( Date creationDate )
    {
        this.creationDate = creationDate;
    }

    /**
     * @return The lesson bound to this request
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Gets the similarity between this and another request, based on their titles
     *
     * @param other The other request to be compared with
     * @return a double indicating the similarity. 0 = 0 % similarity, 1 =  100% similarity
     * @see NormalizedLevenshtein
     */
    public double getSimilarity(Request other) {
        NormalizedLevenshtein l = new NormalizedLevenshtein();
        return l.similarity(title.toLowerCase(), other.title.toLowerCase());
    }
}

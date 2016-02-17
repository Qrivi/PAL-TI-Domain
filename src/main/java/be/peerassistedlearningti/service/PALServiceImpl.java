package be.peerassistedlearningti.service;

import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.repository.*;
import be.peerassistedlearningti.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
public class PALServiceImpl implements PALService
{

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    //================================================================================
    // region Course
    //================================================================================

    /**
     * Adds a course to the database
     *
     * @param course The course to be added to the database
     */
    public void addCourse( Course course )
    {
        courseRepository.save( course );
    }

    /**
     * Updates a course from the database
     *
     * @param course The course to be updated to the database
     */
    public void updateCourse( Course course )
    {
        courseRepository.save( course );
    }

    /**
     * Removes the specified course from the database
     *
     * @param course The course to be removed from the database
     */
    public void removeCourse( Course course )
    {
        courseRepository.delete( course );
    }

    /**
     * Gets the course with the specified id
     *
     * @param id The id of the course
     * @return The course with the specified id
     */
    public Course getCourseById( int id )
    {
        return courseRepository.findOne( id );
    }

    /**
     * Gets all the courses
     *
     * @return A collection containing all the courses
     */
    public Collection<Course> getAllCourses()
    {
        return Utils.makeCollection( courseRepository.findAll() );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Student
    //================================================================================

    /**
     * Adds a student to the database
     *
     * @param student The student to be added to the database
     */
    public void addStudent( Student student )
    {
        studentRepository.save( student );
    }

    /**
     * Updates a student in the database
     *
     * @param student The student to be updated in the database
     */
    public void updateStudent( Student student )
    {
        studentRepository.save( student );
    }

    /**
     * Removes the specified student from the database
     *
     * @param student The student to be removed from the database
     */
    public void removeStudent( Student student )
    {
        studentRepository.delete( student );
    }

    /**
     * Gets the student with the specified id
     *
     * @param id The id of the student
     * @return The student with the specified id
     */
    public Student getStudentById( int id )
    {
        return studentRepository.findOne( id );
    }

    /**
     * Gets the student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    public Student getStudentByEmail( String email )
    {
        return studentRepository.findByEmail( email );
    }

    /**
     * Gets all the students
     *
     * @return A collection containing all the students
     */
    public Collection<Student> getAllStudents()
    {
        return Utils.makeCollection( studentRepository.findAll() );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Tutor
    //================================================================================

    /**
     * Adds a tutor to the database
     *
     * @param tutor The tutor to be added to the database
     */
    public void addTutor( Tutor tutor )
    {
        tutorRepository.save( tutor );
    }

    /**
     * Removes the specified tutor from the database
     *
     * @param tutor The tutor to be removed from the database
     */
    public void removeTutor( Tutor tutor )
    {
        tutorRepository.delete( tutor );
    }

    /**
     * Gets the tutor with the specified id
     *
     * @param id The id of the tutor
     * @return The tutor with the specified id
     */
    public Tutor getTutorById( int id )
    {
        return tutorRepository.findOne( id );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Lesson
    //================================================================================

    /**
     * Adds a lesson to the database
     *
     * @param lesson The lesson to be added to the database
     */
    public void addLesson( Lesson lesson )
    {
        lessonRepository.save( lesson );
    }

    /**
     * Updates a lesson in the database
     *
     * @param lesson The lesson to be updated in the database
     */
    public void updateLesson( Lesson lesson )
    {
        lessonRepository.save( lesson );
    }

    /**
     * Removes the specified lesson from the database
     *
     * @param lesson The lesson to be removed from the database
     */
    public void removeLesson( Lesson lesson )
    {
        lessonRepository.delete( lesson );
    }

    /**
     * Gets the lesson with the specified id
     *
     * @param id The id of the lesson
     * @return The lesson with the specified id
     */
    public Lesson getLessonById( int id )
    {
        return lessonRepository.findOne( id );
    }

    /**
     * Gets all the lessons
     *
     * @return A collection containing all the lessons
     */
    public Collection<Lesson> getAllLessons()
    {
        return Utils.makeCollection( lessonRepository.findAll() );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Booking
    //================================================================================

    /**
     * Gets the booking with the specified id
     *
     * @param id The id of the booking
     * @return The booking with the specified id
     */
    public Booking getBookingById( int id )
    {
        return bookingRepository.findOne( id );
    }

    /**
     * Adds a booking to the database
     *
     * @param booking The booking to be added to the database
     */
    public void addBooking( Booking booking )
    {
        bookingRepository.save( booking );
    }

    /**
     * Removes the specified booking from the database
     *
     * @param booking The booking to be removed from the database
     */
    public void removeBooking( Booking booking )
    {
        bookingRepository.delete( booking );
    }

    /**
     * Gets all the bookings
     *
     * @return A collection containing all the bookings
     */
    public Collection<Booking> getAllBookings()
    {
        return Utils.makeCollection( bookingRepository.findAll() );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Room
    //================================================================================

    /**
     * Adds a room to the database
     *
     * @param room The room to be added to the database
     */
    public void addRoom( Room room )
    {
        roomRepository.save( room );
    }

    /**
     * Removes the specified room from the database
     *
     * @param room The room to be removed from the database
     */
    public void removeRoom( Room room )
    {
        roomRepository.delete( room );
    }

    /**
     * Gets the room with the specified id
     *
     * @param id The id of the room
     * @return The room with the specified id
     */
    public Room getRoomById( int id )
    {
        return roomRepository.findOne( id );
    }

    /**
     * Gets the rooms with the specified campus
     *
     * @param campus The campus of the room
     * @return The rooms with the specified campus
     */
    public Collection<Room> getRoomsFromCampus( Campus campus )
    {
        return roomRepository.findByCampus( campus );
    }

    /**
     * @return A collection containing all the room types
     */
    public Collection<RoomType> getRoomTypes()
    {
        return Arrays.asList( RoomType.values() );
    }

    /**
     * Gets the room type with the specified type
     *
     * @param type The string type of the room type
     * @return The room type object
     */
    public RoomType getRoomTypeByType( String type )
    {
        return RoomType.getByValue( type );
    }

    /**
     * Gets all the rooms
     *
     * @return A collection containing all the rooms
     */
    public Collection<Room> getAllRooms()
    {
        return Utils.makeCollection( roomRepository.findAll() );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Campus
    //================================================================================

    /**
     * @return A collection containing all the campuses
     */
    public Collection<Campus> getCampuses()
    {
        return Arrays.asList( Campus.values() );
    }

    /**
     * Gets the campus with the specified name
     *
     * @param name The string name of the campus
     * @return The Campus object
     */
    public Campus getCampusByName( String name )
    {
        return Campus.getByValue( name );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Application
    //================================================================================

    /**
     * Gets the application with the specified id
     *
     * @param id The id of the application
     * @return The application with the specified id
     */
    public Application getApplicationById( int id )
    {
        return applicationRepository.findOne( id );
    }

    /**
     * Adds a application to the database
     *
     * @param application The application to be added to the database
     */
    public void addApplication( Application application )
    {
        applicationRepository.save( application );
    }

    /**
     * Updates a application to the database
     *
     * @param application The application to be updated in the database
     */
    public void updateApplication( Application application )
    {
        applicationRepository.save( application );
    }

    /**
     * Removes the specified application from the database
     *
     * @param application The application to be removed from the database
     */
    public void removeApplication( Application application )
    {
        applicationRepository.delete( application );
    }

    /**
     * Gets all the applications
     *
     * @return A collection containing all the applications
     */
    public Collection<Application> getAllApplications()
    {
        return Utils.makeCollection( applicationRepository.findAll() );
    }

    /**
     * Gets all the pending applications
     *
     * @return A collection containing all the pending applications
     */
    public Collection<Application> getAllPendingApplications()
    {
        return applicationRepository.findAll( ApplicationState.PENDING );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Review
    //================================================================================

    /**
     * Gets the review with the specified id
     *
     * @param id The id of the review
     * @return The review with the specified id
     */
    public Review getReviewById( int id )
    {
        return reviewRepository.findOne( id );
    }

    /**
     * Adds a review to the database
     *
     * @param review The review to be added to the database
     */
    public void addReview( Review review )
    {
        reviewRepository.save( review );
    }

    /**
     * Removes the specified review from the database
     *
     * @param review The review to be removed from the database
     */
    public void removeReview( Review review )
    {
        reviewRepository.delete( review );
    }

    /**
     * Gets all the reviews
     *
     * @return A collection containing all the reviews
     */
    public Collection<Review> getAllReviews()
    {
        return Utils.makeCollection( reviewRepository.findAll() );
    }

    /**
     * Gets reviews filtered by tutor
     *
     * @return A collection containing the reviews for that tutor
     */
    public Collection<Review> getReviews( Tutor tutor )
    {
        return reviewRepository.findByTutor( tutor );
    }

    /**
     * Gets reviews filtered by lesson
     *
     * @return A collection containing the reviews of that lesson
     */
    public Collection<Review> getReviews( Lesson lesson )
    {
        return reviewRepository.findByLesson( lesson );
    }

    //================================================================================
    // endregion
    //================================================================================
}

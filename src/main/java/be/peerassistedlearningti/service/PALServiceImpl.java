package be.peerassistedlearningti.service;

import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.repository.*;
import be.peerassistedlearningti.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

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
    ApplicationRepository applicationRepository;

    @Autowired
    RequestRepository requestRepository;

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
     * Gets the course with the specified code
     *
     * @param code The code of the course
     * @return The course with the specified code
     */
    public Course getCourseByCode( String code )
    {
        return courseRepository.findByCode( code );
    }

    /**
     * Gets all the courses available for the specified student
     *
     * @param student The student to get the available courses from
     * @return A collection containing all the courses available for the specified student
     */
    public Collection<Course> getAllCoursesByStudent( Student student )
    {
        return courseRepository.findByCurriculum( student.getCurriculum() );
    }

    /**
     * Gets the top subscribed courses with the specified limit
     *
     * @param top The amount of top courses returned
     * @return A collection containing the top subscribed courses
     */
    public Collection<Course> getTopSubscribedCourses( int top )
    {
        Pageable page = new PageRequest( 0, top );
        return courseRepository.findTopSubscribed( page );
    }

    /**
     * Adds a student to the database
     *
     * @param student The student to be added to the database
     */
    public void addStudent( Student student )
    {
        studentRepository.save( student );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Student
    //================================================================================

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
     * Gets the student with the specified profile identifier
     *
     * @param profileIdentifier The profile identifier of the student
     * @return The student with the specified profile identifier
     */
    public Student getStudentByProfileIdentifier( String profileIdentifier )
    {
        return studentRepository.findByProfileIdentifier( profileIdentifier );
    }

    /**
     * Gets the students avatar
     *
     * @param student The student of the avatar
     * @return The students avatar
     */
    public Image getAvatarByStudent( Student student )
    {
        return studentRepository.findAvatarByStudent( student );
    }

    /**
     * Gets the student type with the specified type
     *
     * @param type The type of the user type
     * @return The user type with the specified type
     */
    public UserType getStudentTypeByType( String type )
    {
        return UserType.getByValue( type );
    }

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
     * Updates a tutor from the database
     *
     * @param tutor The tutor to be updated to the database
     */
    public void updateTutor( Tutor tutor )
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

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Tutor
    //================================================================================

    /**
     * Gets the tutor with the specified student
     *
     * @param student The student of the tutor
     * @return The tutor with the specified student
     */
    public Tutor getTutorByStudent( Student student )
    {
        return tutorRepository.findByStudent( student );
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

    /**
     * Gets all the tutors from the specified course
     *
     * @param course The course to get the tutors from
     * @return A collection containing all the tutors from the specified course
     */
    public Collection<Tutor> getAllTutors( Course course )
    {
        return tutorRepository.findAll( course );
    }

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

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Lesson
    //================================================================================

    /**
     * Gets lessons filtered by course
     *
     * @param course to be filtered on
     * @return the lessons of that course
     */
    public Collection<Lesson> getLessons( Course course )
    {
        return lessonRepository.findByCourse( course );
    }

    /**
     * Gets lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons of that tutor
     */
    public Collection<Lesson> getLessons( Tutor tutor )
    {
        return lessonRepository.findByTutor( tutor );
    }

    /**
     * Gets upcoming lessons filtered by course
     *
     * @param course to be filtered on
     * @return the lessons in the future of that course
     */
    public Collection<Lesson> getUpcomingLessons( Course course )
    {
        return lessonRepository.findUpcomingByCourse( course );
    }

    /**
     * Gets upcoming lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons in the future of that tutor
     */
    public Collection<Lesson> getUpcomingLessons( Tutor tutor )
    {
        return lessonRepository.findUpcomingByTutor( tutor );
    }

    /**
     * Gets upcoming lessons available for the student
     *
     * @param student To get the available lessons from
     * @return The lessons available for the student
     */
    public Collection<Lesson> getUpcomingLessons( Student student )
    {
        return lessonRepository.findUpcomingByCurriculum( student.getCurriculum() );
    }

    /**
     * Gets past lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons in the past of that tutor
     */
    public Collection<Lesson> getPastLessons( Tutor tutor )
    {
        return lessonRepository.findPastByTutor( tutor );
    }

    /**
     * Gets past lessons from the specified tutor from the offset with the limit as size
     *
     * @param tutor  The tutor to get the lessons from
     * @param offset The offset to start from
     * @param limit  The maximum size of the list
     * @return the lessons in the past of that tutor from the offset with the limit as size
     */
    public Collection<Lesson> getPastLessons( Tutor tutor, int offset, int limit )
    {
        Pageable page = new PageRequest( offset, limit );
        return lessonRepository.findPastByTutor( tutor, page );
    }

    /**
     * Gets the past lessons for the student
     *
     * @param student The student to get the lessons from
     * @return The past lessons of the student
     */
    public Collection<Lesson> getPastBookings( Student student )
    {
        return lessonRepository.findPastByStudent( student );
    }

    /**
     * Gets the past bookings for the student from the offset with the limit as size
     *
     * @param student The student to get the bookings from
     * @param offset  The offset to start from
     * @param limit   The maximum size of the list
     * @return The past bookings of the student from the offset with size limit
     */
    public Collection<Lesson> getPastBookings( Student student, int offset, int limit )
    {
        Pageable page = new PageRequest( offset, limit );
        return lessonRepository.findPastByStudent( student, page );
    }

    /**
     * Gets upcoming lessons filtered by student
     *
     * @param student The student to get the lessons from
     * @return the lessons in the future of that student
     */
    public Collection<Lesson> getUpcomingBookings( Student student )
    {
        return lessonRepository.findUpcomingByStudent( student );
    }

    /**
     * Checks if the specified student has the specified booking
     *
     * @param student The student to check if it has the booking
     * @param lesson  The booking to check if it has the student
     * @return If the student has the booking
     */
    public boolean hasBooking( Student student, Lesson lesson )
    {
        return lessonRepository.hasBooking( student, lesson ) != null;
    }

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
     * Updates a room from the database
     *
     * @param room The room to be updated from the database
     */
    public void updateRoom( Room room )

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
     * Gets the room type with the specified type
     *
     * @param type The string type of the room type
     * @return The room type object
     */
    public RoomType getRoomTypeByType( String type )
    {
        return RoomType.getByValue( type );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Room
    //================================================================================

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
     * Gets all the pending applications from a student
     *
     * @param student The student to get the pending applications from
     * @return A collection containing all the pending applications from a student
     */
    public Collection<Application> getAllPendingApplications( Student student )
    {
        return applicationRepository.findAll( student, ApplicationState.PENDING );
    }

    /**
     * Gets all the approved applications from a student
     *
     * @param student The student to get the approved applications from
     * @return A collection containing all the approved applications from a student
     */
    public Collection<Application> getAllApprovedApplications( Student student ) {return applicationRepository.findAll( student, ApplicationState.APPROVED );}

    /**
     * Gets the last applications from a student with the specified limit
     *
     * @param student The student to get the applications from
     * @param last    The amount of last applications returned
     * @return A collection containing the last applications from the student
     */
    public Collection<Application> getLastApplications( Student student, int last )
    {
        Pageable page = new PageRequest( 0, last );
        return applicationRepository.findLastByStudent( student, page );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Campus
    //================================================================================

    /**
     * Gets the application screenshot
     *
     * @param application The application of the screenshot
     * @return The application screenshot
     */
    public Image getScreenshotByApplication( Application application )
    {
        return applicationRepository.findScreenshotByApplication( application );
    }

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

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Application
    //================================================================================

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
     * Gets reviews filtered by tutor
     *
     * @param tutor to be filtered on
     * @return A collection containing the reviews for that tutor
     */
    public Collection<Review> getReviews( Tutor tutor )
    {
        return reviewRepository.findByTutor( tutor );
    }

    /**
     * Gets reviews filtered by tutor from the offset with the limit as size
     *
     * @param tutor  to be filtered on
     * @param offset The offset to start from
     * @param limit  The maximum size of the list
     * @return A collection containing the reviews for that tutor from the offset with the limit as size
     */
    public Collection<Review> getReviews( Tutor tutor, int offset, int limit )
    {
        Pageable page = new PageRequest( offset, limit );
        return reviewRepository.findByTutor( tutor, page );
    }

    /**
     * Gets reviews filtered by lesson
     *
     * @param lesson to be filterd on
     * @return A collection containing the reviews of that lesson
     */
    public Collection<Review> getReviews( Lesson lesson )
    {
        return reviewRepository.findByLesson( lesson );
    }

    /**
     * Gets the reviews made by the given student
     *
     * @param student The given student
     * @return A collection containing the reviews of that student
     */
    public Collection<Review> getReviewsByStudent( Student student )
    {
        return reviewRepository.findByStudent( student );
    }

    /**
     * Gets the reviews made by the given student from the offset with the limit as size
     *
     * @param student The given student
     * @param offset  The offset to start from
     * @param limit   The maximum size of the list
     * @return A collection containing the reviews of that student from the offset with the limit as size
     */
    public Collection<Review> getReviewsByStudent( Student student, int offset, int limit )
    {
        Pageable page = new PageRequest( offset, limit );
        return reviewRepository.findByStudent( student, page );
    }

    /**
     * Gets reviews filtered by student and lesson
     *
     * @return A collection containing the reviews of that student
     */
    public Review getReviewsByStudentAndLesson( Student student, Lesson lesson )
    {
        return reviewRepository.findByStudentAndLesson( student, lesson );
    }

    /**
     * Gets the request with the specified id
     *
     * @param id The id of the request
     * @return The request with the specified id
     */
    public Request getRequestById( int id )
    {
        return requestRepository.findOne( id );
    }

    /**
     * Adds a request to the database
     *
     * @param request The request to be added to the database
     */
    public void addRequest( Request request )
    {
        requestRepository.save( request );
    }

    /**
     * Updates a request to the database
     *
     * @param request The request to be updated in the database
     */
    public void updateRequest( Request request )
    {
        requestRepository.save( request );
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Review
    //================================================================================

    /**
     * Removes the specified request from the database
     *
     * @param request The request to be removed from the database
     */
    public void removeRequest( Request request )
    {
        requestRepository.delete( request );
    }

    /**
     * Gets all the requests from the specified course
     *
     * @param course The course to get the requests from
     * @return A collection containing all the requests from the specified course
     */
    public Collection<Request> getRequests( Course course )
    {
        return requestRepository.findAll( course );
    }

    /**
     * Gets all the requests from the specified student
     *
     * @param student The student to get the requests from
     * @return A collection containing all the requests from the specified student
     */
    public Collection<Request> getRequests( Student student )
    {
        return requestRepository.findAll( student );
    }

    /**
     * Gets the curriculum with the specified name
     *
     * @param name The string name of the curriculum
     * @return The Curriculum object with the specified name
     */
    public Curriculum getCurriculumByName( String name )
    {
        return Curriculum.getByName( name );
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

    /**
     * Gets all the students
     *
     * @return A collection containing all the students
     */
    public Collection<Student> getAllStudents()
    {
        return Utils.makeCollection( studentRepository.findAll() );
    }

    /**
     * Gets all the student types
     *
     * @return A collection containing all the student types
     */
    public Collection<UserType> getStudentTypes()
    {
        return Arrays.asList( UserType.values() );
    }

    /**
     * Gets all the tutors
     *
     * @return A collection containing all the tutors
     */
    public Collection<Tutor> getAllTutors()
    {
        return Utils.makeCollection( tutorRepository.findAll() );
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

    /**
     * Gets all the upcoming lessons
     *
     * @return A collection containing all the upcoming lessons
     */
    public Collection<Lesson> getUpcomingLessons()
    {
        return lessonRepository.findUpcoming();
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Request
    //================================================================================

    /**
     * @return A collection containing all the room types
     */
    public Collection<RoomType> getRoomTypes()
    {
        return Arrays.asList( RoomType.values() );
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

    /**
     * @return A collection containing all the campuses
     */
    public Collection<Campus> getCampuses()
    {
        return Arrays.asList( Campus.values() );
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

    /**
     * Gets all the done applications
     *
     * @return A collection containing all the done applications
     */
    public Collection<Application> getAllDoneApplications()
    {
        return new ArrayList<Application>()
        {{
                addAll( applicationRepository.findAll( ApplicationState.APPROVED ) );
                addAll( applicationRepository.findAll( ApplicationState.REJECTED ) );
            }};
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
     * Gets all the requests filtered by given set of courses
     *
     * @return A collection containing all the requests
     */
    public Collection<Request> getAllRequests(Set<Course> courses) {
        return requestRepository.findAllRequests(courses);
    }

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Curriculum
    //================================================================================

    /**
     * Gets all the request
     *
     * @return A collection containing all the request
     */
    public Collection<Request> getAllRequests()
    {
        return Utils.makeCollection( requestRepository.findAll() );
    }

    /**
     * @return A collection containing all the curriculum's
     */
    public Collection<Curriculum> getCurriculums()
    {
        return Arrays.asList( Curriculum.values() );
    }


    //================================================================================
    // endregion
    //================================================================================
}

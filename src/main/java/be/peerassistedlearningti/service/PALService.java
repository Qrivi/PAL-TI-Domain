package be.peerassistedlearningti.service;

import be.peerassistedlearningti.model.*;

import java.util.Collection;

/**
 * Interface used to determine the backend functionalities
 *
 * @see PALService
 */
public interface PALService
{

    //================================================================================
    // region Course
    //================================================================================

    /**
     * Adds a course to the database
     *
     * @param course The course to be added to the database
     */
    void addCourse( Course course );

    /**
     * Updates a course from the database
     *
     * @param course The course to be updated to the database
     */
    void updateCourse( Course course );

    /**
     * Removes the specified course from the database
     *
     * @param course The course to be removed from the database
     */
    void removeCourse( Course course );

    /**
     * Gets the course with the specified id
     *
     * @param id The id of the course
     * @return The course with the specified id
     */
    Course getCourseById( int id );

    /**
     * Gets the course with the specified code
     *
     * @param code The code of the course
     * @return The course with the specified code
     */
    Course getCourseByCode( String code );

    /**
     * Gets all the courses
     *
     * @return A collection containing all the courses
     */
    Collection<Course> getAllCourses();

    /**
     * Gets the top subscribed courses with the specified limit
     *
     * @return A collection containing the top subscribed courses
     */
    Collection<Course> getTopSubscribedCourses( int limit );

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
    void addStudent( Student student );

    /**
     * Updates a student in the database
     *
     * @param student The student to be updated in the database
     */
    void updateStudent( Student student );

    /**
     * Removes the specified student from the database
     *
     * @param student The student to be removed from the database
     */
    void removeStudent( Student student );

    /**
     * Gets the student with the specified id
     *
     * @param id The id of the student
     * @return The student with the specified id
     */
    Student getStudentById( int id );

    /**
     * Gets the student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    Student getStudentByEmail( String email );

    /**
     * Gets the student with the specified profile identifier
     *
     * @param profileIdentifier The profile identifier of the student
     * @return The student with the specified profile identifier
     */
    Student getStudentByProfileIdentifier( String profileIdentifier );

    /**
     * Gets all the students
     *
     * @return A collection containing all the students
     */
    Collection<Student> getAllStudents();

    /**
     * Gets lessons filtered by course
     *
     * @param course to be filtered on
     * @return the lessons of that course
     */
    Collection<Lesson> getLessons( Course course );

    /**
     * Gets the past lessons for the student
     *
     * @param student The student to get the lessons from
     * @return The past lessons of the student
     */
    Collection<Lesson> getPastLessons( Student student );

    /**
     * Gets upcoming lessons filtered by student
     *
     * @param student The student to get the lessons from
     * @return the lessons in the future of that student
     */
    Collection<Lesson> getUpcomingLessons( Student student );

    /**
     * Gets upcoming lessons filtered by course
     *
     * @param course to be filterd on
     * @return the lessons in the future of that course
     */
    Collection<Lesson> getUpcomingLessons( Course course );

    /**
     * Gets all the upcoming lessons
     *
     * @return A collection containing the upcoming lessons
     */
    Collection<Lesson> getUpcomingLessons();

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
    void addTutor( Tutor tutor );

    /**
     * Updates a tutor from the database
     *
     * @param tutor The tutor to be updated to the database
     */
    void updateTutor( Tutor tutor );

    /**
     * Removes the specified tutor from the database
     *
     * @param tutor The tutor to be removed from the database
     */
    void removeTutor( Tutor tutor );

    /**
     * Gets the tutor with the specified id
     *
     * @param id The id of the tutor
     * @return The tutor with the specified id
     */
    Tutor getTutorById( int id );

    /**
     * Gets all the tutors
     *
     * @return A collection containing all the tutors
     */
    Collection<Tutor> getAllTutors();

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
    void addLesson( Lesson lesson );

    /**
     * Updates a lesson in the database
     *
     * @param lesson The lesson to be updated in the database
     */
    void updateLesson( Lesson lesson );

    /**
     * Removes the specified lesson from the database
     *
     * @param lesson The lesson to be removed from the database
     */
    void removeLesson( Lesson lesson );

    /**
     * Gets the lesson with the specified id
     *
     * @param id The id of the lesson
     * @return The lesson with the specified id
     */
    Lesson getLessonById( int id );

    /**
     * Gets all the lessons
     *
     * @return A collection containing all the lessons
     */
    Collection<Lesson> getAllLessons();

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
    void addRoom( Room room );

    /**
     * Updates a room from the database
     *
     * @param room The room to be updated from the database
     */
    void updateRoom( Room room );

    /**
     * Removes the specified room from the database
     *
     * @param room The room to be removed from the database
     */
    void removeRoom( Room room );

    /**
     * Gets the room with the specified id
     *
     * @param id The id of the room
     * @return The room with the specified id
     */
    Room getRoomById( int id );

    /**
     * Gets the rooms with the specified campus
     *
     * @param campus The campus of the room
     * @return The rooms with the specified campus
     */
    Collection<Room> getRoomsFromCampus( Campus campus );

    /**
     * @return A collection containing all the room types
     */
    Collection<RoomType> getRoomTypes();

    /**
     * Gets the room type with the specified type
     *
     * @param type The string type of the room type
     * @return The room type object
     */
    RoomType getRoomTypeByType( String type );

    /**
     * Gets all the rooms
     *
     * @return A collection containing all the rooms
     */
    Collection<Room> getAllRooms();

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Campus
    //================================================================================

    /**
     * @return A collection containing all the campuses
     */
    Collection<Campus> getCampuses();

    /**
     * Gets the campus with the specified name
     *
     * @param name The string name of the campus
     * @return The Campus object
     */
    Campus getCampusByName( String name );


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
    Application getApplicationById( int id );

    /**
     * Adds a application to the database
     *
     * @param application The application to be added to the database
     */
    void addApplication( Application application );

    /**
     * Updates a application to the database
     *
     * @param application The application to be updated in the database
     */
    void updateApplication( Application application );

    /**
     * Removes the specified application from the database
     *
     * @param application The application to be removed from the database
     */
    void removeApplication( Application application );

    /**
     * Gets all the applications
     *
     * @return A collection containing all the applications
     */
    Collection<Application> getAllApplications();

    /**
     * Gets all the pending applications
     *
     * @return A collection containing all the pending applications
     */
    Collection<Application> getAllPendingApplications();

    /**
     * Gets all the done applications
     *
     * @return A collection containing all the done applications
     */
    Collection<Application> getAllDoneApplications();

    /**
     * Gets all the pending applications from a student
     *
     * @param student The student to get the pending applications from
     * @return A collection containing all the pending applications from a student
     */
    Collection<Application> getAllPendingApplications( Student student );

    /**
     * Gets all the approved applications from a student
     *
     * @param student The student to get the approved applications from
     * @return A collection containing all the approved applications from a student
     */
    Collection<Application> getAllApprovedApplications( Student student );

    /**
     * Gets the last applications from a student with the specified limit
     *
     * @param student The student to get the applications from
     * @return A collection containing the last applications from the student
     */
    Collection<Application> getLastApplications( Student student, int limit );

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
    Review getReviewById( int id );

    /**
     * Adds a review to the database
     *
     * @param review The review to be added to the database
     */
    void addReview( Review review );

    /**
     * Removes the specified review from the database
     *
     * @param review The review to be removed from the database
     */
    void removeReview( Review review );

    /**
     * Gets all the reviews
     *
     * @return A collection containing all the reviews
     */
    Collection<Review> getAllReviews();

    /**
     * Gets reviews filtered by tutor
     *
     * @param tutor to be filtered on
     * @return A collection containing the reviews for that tutor
     */
    Collection<Review> getReviews( Tutor tutor );

    /**
     * Gets reviews filtered by lesson
     *
     * @param lesson to be filterd on
     * @return A collection containing the reviews of that lesson
     */
    Collection<Review> getReviews( Lesson lesson );

    /**
     * Gets the reviews made by the given student
     *
     * @param student The given student
     * @return A collection containing the reviews of that student
     */
    Collection<Review> getReviewsForStudent( Student student );

    /**
     * Gets reviews filtered by student and lesson
     *
     * @return A collection containing the reviews of that student
     */
    Review getReviewsForStudentAndLesson( Student student, Lesson lesson );

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Request
    //================================================================================

    /**
     * Gets the request with the specified id
     *
     * @param id The id of the request
     * @return The request with the specified id
     */
    Request getRequestById( int id );

    /**
     * Adds a request to the database
     *
     * @param request The request to be added to the database
     */
    void addRequest( Request request );

    /**
     * Removes the specified request from the database
     *
     * @param request The request to be removed from the database
     */
    void removeRequest( Request request );

    /**
     * Gets all the request
     *
     * @return A collection containing all the request
     */
    Collection<Request> getAllRequest();


    //================================================================================
    // endregion
    //================================================================================
}

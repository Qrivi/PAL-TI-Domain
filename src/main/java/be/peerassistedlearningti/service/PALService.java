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
     * Gets all the courses
     *
     * @return A collection containing all the courses
     */
    Collection<Course> getAllCourses();

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
     * Gets all the students
     *
     * @return A collection containing all the students
     */
    Collection<Student> getAllStudents();

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
    public Collection<Lesson> getAllLessons();

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
    Booking getBookingById( int id );

    /**
     * Adds a booking to the database
     *
     * @param booking The booking to be added to the database
     */
    public void addBooking( Booking booking );

    /**
     * Removes the specified booking from the database
     *
     * @param booking The booking to be removed from the database
     */
    public void removeBooking( Booking booking );

    /**
    * Gets all the bookings
    *
            * @return A collection containing all the bookings
    */
    public Collection<Booking> getAllBookings();

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
}

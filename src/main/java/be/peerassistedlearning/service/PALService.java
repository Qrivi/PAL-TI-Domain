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

package be.peerassistedlearning.service;

import be.peerassistedlearning.model.*;

import java.util.Collection;
import java.util.Set;

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
     * Gets all the courses available for the specified student
     *
     * @param student The student to get the available courses from
     * @return A collection containing all the courses available for the specified student
     */
    Collection<Course> getCourses( Student student );
    /**
     * Gets the top subscribed courses with the specified limit
     *
     * @param top The amount of top courses returned
     * @return A collection containing the top subscribed courses
     */
    Collection<Course> getTopSubscribedCourses( int top );

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
     * Gets all the students
     *
     * @return A collection containing all the students
     */
    Collection<Student> getAllStudents();
    /**
     * Gets all the student types
     *
     * @return A collection containing all the student types
     */
    Collection<UserType> getStudentTypes();
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
     * Gets the students avatar
     *
     * @param student The student of the avatar
     * @return The students avatar
     */
    Image getAvatar( Student student );
    /**
     * Gets all the student types
     *
     * @param type The type of the user type
     * @return A collection containing all the student types
     */
    UserType getStudentTypeByString( String type );

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
    /**
     * Gets all the tutors from the specified course
     *
     * @param course The course to get the tutors from
     * @return A collection containing all the tutors from the specified course
     */
    Collection<Tutor> getTutors( Course course );
    /**
     * Gets the tutor with the specified student
     *
     * @param student The student of the tutor
     * @return The tutor with the specified student
     */
    Tutor getTutorByStudent( Student student );
    /**
     * Adds a lesson to the database
     *
     * @param lesson The lesson to be added to the database
     */

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Lesson
    //================================================================================
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
    /**
     * Gets all the upcoming lessons
     *
     * @return A collection containing the upcoming lessons
     */
    Collection<Lesson> getUpcomingLessons();
    /**
     * Gets lessons filtered by course
     *
     * @param course To be filtered on
     * @return The lessons of that course
     */
    Collection<Lesson> getLessons( Course course );
    /**
     * Gets lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return The lessons of that tutor
     */
    Collection<Lesson> getLessons( Tutor tutor );
    /**
     * Gets the past bookings for the student
     *
     * @param student The student to get the bookings from
     * @return The past bookings of the student
     */
    Collection<Lesson> getPastBookings( Student student );

    /**
     * Gets the past bookings for the student from the offset with the limit as size
     *
     * @param student The student to get the bookings from
     * @param offset  The offset to start from
     * @param limit   The maximum size of the list
     * @return The past bookings of the student from the offset with size limit
     */
    Collection<Lesson> getPastBookings( Student student, int offset, int limit );

    /**
     * Gets upcoming bookings filtered by student
     *
     * @param student The student to get the bookings from
     * @return the bookings in the future of that student
     */
    Collection<Lesson> getUpcomingBookings( Student student );
    /**
     * Checks if the specified student has the specified booking
     *
     * @param student The student to check if it has the booking
     * @param lesson  The booking to check if it has the student
     * @return If the student has the booking
     */
    boolean hasBooking( Student student, Lesson lesson );
    /**
     * Gets upcoming lessons filtered by course
     *
     * @param course to be filtered on
     * @return the lessons in the future of that course
     */
    Collection<Lesson> getUpcomingLessons( Course course );
    /**
     * Gets upcoming lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons in the future of that tutor
     */
    Collection<Lesson> getUpcomingLessons( Tutor tutor );
    /**
     * Gets upcoming lessons available for the student
     *
     * @param student To get the available lessons from
     * @return The lessons available for the student
     */
    Collection<Lesson> getUpcomingLessons( Student student );
    /**
     * Gets past lessons from the specified tutor
     *
     * @param tutor The tutor to get the lessons from
     * @return the lessons in the past of that tutor
     */
    Collection<Lesson> getPastLessons( Tutor tutor );
    /**
     * Gets past lessons from the specified tutor from the offset with the limit as size
     *
     * @param tutor  The tutor to get the lessons from
     * @param offset The offset to start from
     * @param limit  The maximum size of the list
     * @return the lessons in the past of that tutor from the offset with the limit as size
     */
    Collection<Lesson> getPastLessons( Tutor tutor, int offset, int limit );

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
     * @return A collection containing all the room types
     */
    Collection<RoomType> getRoomTypes();
    /**
     * Gets all the rooms
     *
     * @return A collection containing all the rooms
     */
    Collection<Room> getAllRooms();
    /**
     * Gets the rooms with the specified campus
     *
     * @param campus The campus of the room
     * @return The rooms with the specified campus
     */
    Collection<Room> getRooms( Campus campus );
    /**
     * Gets the room type with the specified type
     *
     * @param type The string type of the room type
     * @return The room type object
     */
    RoomType getRoomTypeByString( String type );

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
     * Gets all the pending applications from a student
     *
     * @param student The student to get the pending applications from
     * @return A collection containing all the pending applications from a student
     */
    Collection<Application> getPendingApplications( Student student );
    /**
     * Gets all the approved applications from a student
     *
     * @param student The student to get the approved applications from
     * @return A collection containing all the approved applications from a student
     */
    Collection<Application> getApprovedApplications( Student student );
    /**
     * Gets the last applications from a student with the specified limit
     *
     * @param student The student to get the applications from
     * @param last    The amount of last applications returned
     * @return A collection containing the last applications from the student
     */
    Collection<Application> getLastApplications( Student student, int last );
    /**
     * Gets the application screenshot
     *
     * @param application The application of the screenshot
     * @return The application screenshot
     */
    Image getScreenshot( Application application );
    /**
     * Gets all the done applications
     *
     * @return A collection containing all the done applications
     */
    Collection<Application> getAllDoneApplications();

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
     * Gets reviews filtered by tutor from the offset with the limit as size
     *
     * @param tutor  to be filtered on
     * @param offset The offset to start from
     * @param limit  The maximum size of the list
     * @return A collection containing the reviews for that tutor from the offset with the limit as size
     */
    Collection<Review> getReviews( Tutor tutor, int offset, int limit );
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
    Collection<Review> getReviews( Student student );
    /**
     * Gets the reviews made by the given student from the offset with the limit as size
     *
     * @param student The given student
     * @param offset  The offset to start from
     * @param limit   The maximum size of the list
     * @return A collection containing the reviews of that student from the offset with the limit as size
     */
    Collection<Review> getReviews( Student student, int offset, int limit );
    /**
     * Gets reviews filtered by student and lesson
     *
     * @param student The student that gave the review
     * @param lesson  The lesson of the review
     * @return A collection containing the reviews of that student
     */
    Review getReviews( Student student, Lesson lesson );

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
     * Updates a request to the database
     *
     * @param request The request to be updated in the database
     */
    void updateRequest( Request request );
    /**
     * Removes the specified request from the database
     *
     * @param request The request to be removed from the database
     */
    void removeRequest( Request request );
    /**
     * Gets all the request for given course.
     *
     * @param course The course to get the requests from
     * @return A collection containing all the request
     */
    Collection<Request> getRequests( Course course );

    /**
     * Gets all the request for given course who don't already have a lesson associated.
     *
     * @param course The course to get the requests from
     * @return A collection containing all the request
     */
    Collection<Request> getRequestsWithoutLesson(Course course);
    /**
     * Gets all the request for given student.
     *
     * @param student The student to filter the requests
     * @return A collection containing all the request
     */
    Collection<Request> getRequests( Student student );
    /**
     * Gets all the requests filtered by given set of courses
     *
     * @param courses The course to filter the requests from
     * @return A collection containing all the requests
     */
    Collection<Request> getRequests( Set<Course> courses );
    /**
     * Gets all the request
     *
     * @return A collection containing all the request
     */
    Collection<Request> getAllRequests();

    //================================================================================
    // endregion
    //================================================================================

    //================================================================================
    // region Curriculum
    //================================================================================
    /**
     * @return A collection containing all the curriculum's
     */
    Collection<Curriculum> getCurriculums();
    /**
     * Gets the curriculum with the specified name
     *
     * @param name The string name of the curriculum
     * @return The Curriculum object with the specified name
     */
    Curriculum getCurriculumByName( String name );

    //================================================================================
    // endregion
    //================================================================================
}

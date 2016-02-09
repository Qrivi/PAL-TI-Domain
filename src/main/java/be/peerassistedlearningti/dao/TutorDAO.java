package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Tutor;

import java.util.Set;

/**
 * Interface for Tutor specific DAO operations
 *
 * @see Tutor
 * @see CRUDDAO
 */
public interface TutorDAO extends CRUDDAO<Integer, Tutor> {

    /**
     * Gets a set of tutors for the specified course
     *
     * @param course The course of the tutors
     * @return The set of tutors with the specified course
     */
    Set<Tutor> getTutorsForCourse(Course course);
}

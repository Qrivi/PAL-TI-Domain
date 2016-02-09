package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.TutorDAO;
import be.peerassistedlearningti.model.Course;
import be.peerassistedlearningti.model.Tutor;

import java.util.Set;

/**
 * Class for Tutor specific DAO operations via JPA
 *
 * @see Tutor
 * @see CRUDJPADAO
 */
public class TutorJPADAO extends CRUDJPADAO<Integer, Tutor> implements TutorDAO {

    /**
     * Default constructor for StudentJPADAO
     */
    public TutorJPADAO(){super(Tutor.class);}

    /**
     * Gets a set of tutors for the specified course
     *
     * @param course The course of the tutors
     * @return The set of tutors with the specified course
     */
    public Set<Tutor> getTutorsForCourse(Course course) {
        //TODO:: implement
        return null;
    }
}

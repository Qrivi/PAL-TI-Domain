package be.peerassistedlearningti.dao;

import be.peerassistedlearningti.common.dao.CRUDDAO;
import be.peerassistedlearningti.model.Lesson;
import be.peerassistedlearningti.model.Review;
import be.peerassistedlearningti.model.Tutor;

import java.util.Collection;

/**
 * Interface for Review specific DAO operations
 *
 * @see Review
 * @see CRUDDAO
 */
public interface ReviewDAO extends CRUDDAO<Integer, Review> {
    Collection<Review> getReviews(Tutor tutor);
    Collection<Review> getReviews(Lesson lesson);
}

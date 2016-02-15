package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.ReviewDAO;
import be.peerassistedlearningti.model.Review;

/**
 * Class for Review specific DAO operations via JPA
 *
 * @see be.peerassistedlearningti.model.Review
 * @see CRUDJPADAO
 */
public class ReviewJPADAO extends CRUDJPADAO<Integer, Review> implements ReviewDAO{
    /**
     * Default constructor for CRUDJPADAO
     */
    public ReviewJPADAO() { super(Review.class); }

}

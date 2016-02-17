package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.ReviewDAO;
import be.peerassistedlearningti.model.Lesson;
import be.peerassistedlearningti.model.Review;
import be.peerassistedlearningti.model.Tutor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    public Collection<Review> getReviews(Tutor tutor) {
        Collection<Review> reviews = new HashSet<Review>();
        for (Review r: super.getAll()){
            if (r.getLesson().getTutor().equals(tutor)){
                reviews.add(r);
            }
        }
        return reviews;
    }

    public Collection<Review> getReviews(Lesson lesson){
        Collection<Review> reviews = new HashSet<Review>();
        for (Review r: super.getAll()){
            if (r.getLesson().equals(lesson)){
                reviews.add(r);
            }
        }
        return reviews;
    }
}

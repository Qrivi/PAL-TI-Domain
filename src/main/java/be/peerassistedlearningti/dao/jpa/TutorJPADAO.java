package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.TutorDAO;
import be.peerassistedlearningti.model.Tutor;

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

}

package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.LessonDAO;
import be.peerassistedlearningti.model.Lesson;

/**
 * Class for Lesson specific DAO operations via JPA
 *
 * @see Lesson
 * @see CRUDJPADAO
 */
public class LessonJPADAO extends CRUDJPADAO<Integer, Lesson> implements LessonDAO
{

    /**
     * Default constructor for CRUDJPADAO
     */
    public LessonJPADAO()
    {
        super( Lesson.class );
    }

}

package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.CourseDAO;
import be.peerassistedlearningti.model.Course;

public class CourseJPADAO extends CRUDJPADAO<Integer, Course> implements CourseDAO
{

    /**
     * Default constructor for CourseJPADAO
     */
    public CourseJPADAO()
    {
        super( Course.class );
    }

}

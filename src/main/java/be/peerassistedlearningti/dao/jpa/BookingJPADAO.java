package be.peerassistedlearningti.dao.jpa;

import be.peerassistedlearningti.common.dao.CRUDJPADAO;
import be.peerassistedlearningti.dao.BookingDAO;
import be.peerassistedlearningti.model.Booking;

public class BookingJPADAO extends CRUDJPADAO<Integer, Booking> implements BookingDAO
{
    /**
     * Default constructor for CRUDJPADAO
     */
    public BookingJPADAO()
    {
        super( Booking.class );
    }
}

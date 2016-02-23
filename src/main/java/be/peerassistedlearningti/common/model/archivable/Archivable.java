package be.peerassistedlearningti.common.model;

import java.util.Date;


/**
 * Interface used to list all timeline Objects
 */
public interface Archivable
{

    /**
     * @return The date to be used by the timeline
     */
    Date getArchiveDate();
}

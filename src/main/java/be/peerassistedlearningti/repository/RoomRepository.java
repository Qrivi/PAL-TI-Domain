/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.model.Campus;
import be.peerassistedlearningti.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Interface for Room specific database operations
 *
 * @see Room
 * @see CrudRepository
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>
{
    /**
     * Gets all the rooms from the specified campus
     *
     * @param campus The campus to get the rooms from
     * @return The rooms from the specified campus
     */
    @Query( "SELECT r FROM Room r WHERE r.campus = :campus" )
    Collection<Room> findByCampus( @Param( "campus" ) Campus campus );
}

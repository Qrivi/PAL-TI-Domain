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

package be.peerassistedlearning.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.Set;

public class LessonTest extends ValidationTest{
    @Test
    public void testDateIsNull(){
        Lesson l = new Lesson( null, "Test lesson", "Test description", 120L, new Course(), 25, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "date" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson date should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testNameIsNull(){
        Lesson l = new Lesson( new Date(), null, "Test description", 120L, new Course(), 25, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "name" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testDescriptionIsNull(){
        Lesson l = new Lesson( new Date(), "Test lesson", null, 120L, new Course(), 25, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "description" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson description should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testDurationTooLow(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 0, new Course(), 25, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "duration" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson duration should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testMaxParticipantsTooLow(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 120L, new Course(), 0, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "maxParticipants" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson max participants should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCourseIsNull(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 120L, null, 25, new Tutor(), new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "course" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson course should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testTutorIsNull(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 120L, new Course(), 25, null, new Room(), new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "tutor" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson tutor should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testRoomIsNull(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 120L, new Course(), 25, new Tutor(), null, new Room() );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "room" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson room should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testBackupRoomIsNull(){
        Lesson l = new Lesson( new Date(), "Test lesson", "Test description", 120L, new Course(), 25, new Tutor(), new Room(), null );

        Set<ConstraintViolation<Lesson>> constraintViolations = validator.validateProperty( l, "backupRoom" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Lesson backup room should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
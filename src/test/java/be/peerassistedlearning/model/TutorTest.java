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
import java.util.HashSet;
import java.util.Set;

public class TutorTest extends ValidationTest{

    @Test
    public void testStudentIsNull(){
        Tutor t = new Tutor( null, new HashSet<Course>() );

        Set<ConstraintViolation<Tutor>> constraintViolations = validator.validateProperty( t, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Tutor student should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCoursesIsNull(){
        Tutor t = new Tutor( new Student(), null );

        Set<ConstraintViolation<Tutor>> constraintViolations = validator.validateProperty( t, "courses" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Tutor courses should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
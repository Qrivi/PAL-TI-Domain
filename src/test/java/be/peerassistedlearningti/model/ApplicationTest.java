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

package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.Set;

public class ApplicationTest extends ValidationTest
{
    @Test
    public void testStudentIsNull()
    {
        Application a = new Application( null, new Course(), new byte[ 2 ], ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application student should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCourseIsNull()
    {
        Application a = new Application( new Student(), null, new byte[ 2 ], ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "course" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application course should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testScreenshotIsNull()
    {
        Application a = new Application( new Student(), new Course(), null, ApplicationState.PENDING, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "screenshot" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application screenshot should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testStateIsNull()
    {
        Application a = new Application( new Student(), new Course(), new byte[ 2 ], null, new Date(), new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "state" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application state should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testBeginDateIsNull()
    {
        Application a = new Application( new Student(), new Course(), new byte[ 2 ], ApplicationState.PENDING, null, new Date() );

        Set<ConstraintViolation<Application>> constraintViolations = validator.validateProperty( a, "beginDate" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Application begin date should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }
}
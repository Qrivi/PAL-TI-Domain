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
import java.util.Set;

public class CourseTest extends ValidationTest
{

    @Test
    public void testCodeIsNull()
    {
        Course c = new Course( null, ".NET Programmeren", ".NET", Curriculum.TI, 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "code" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course code should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testNameIsNull()
    {
        Course c = new Course( "MX2506", null, ".NET", Curriculum.TI, 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "name" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testShortNameIsNull()
    {
        Course c = new Course( "MX2506", ".NET Programmeren", null, Curriculum.TI, 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "shortName" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course short name should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testCurriculumIsNull()
    {
        Course c = new Course( "MX2506", ".NET Programmeren", ".NET", null, 1 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty( c, "curriculum" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "The course curriculum should not be empty!", constraintViolations.iterator()
                .next()
                .getMessage() );
    }

    @Test
    public void testYearIsTooLow() {
        Course c = new Course( "MX2506", ".NET Programmeren", ".NET", Curriculum.TI, 0 );

        Set<ConstraintViolation<Course>> constraintViolations = validator.validateProperty(c, "year");

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("The course year should be higher than zero!", constraintViolations.iterator()
                .next()
                .getMessage());
    }
}
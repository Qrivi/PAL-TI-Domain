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

public class RequestTest extends ValidationTest
{

    @Test
    public void testTitleIsNull()
    {
        Request request = new Request(null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "title" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request title should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testTitleSizeIsTooSmall()
    {
        Request request = new Request("", null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "title" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request title should be between 3 and 50 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testTitleSizeIsTooBig()
    {
        String title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu maximus metus, vel imperdiet nunc. Cras commodo vestibulum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin ultrices magna ut nisl venenatis, quis pretium urna fermentum. In aliquam velit id enim porta convallis. Vestibulum vel efficitur dui. Curabitur vitae neque nullam.";

        Request request = new Request( title, null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "title" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request title should be between 3 and 50 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionIsNull()
    {
        Request request = new Request( null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionSizeIsTooSmall()
    {
        Request request = new Request( null, "abc", null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should be between 10 and 300 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDescriptionSizeIsTooBig()
    {
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu maximus metus, vel imperdiet nunc. Cras commodo vestibulum laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin ultrices magna ut nisl venenatis, quis pretium urna fermentum. In aliquam velit id enim porta convallis. Vestibulum vel efficitur dui. Curabitur vitae neque nullam.";

        Request request = new Request( null, desc, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "description" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request description should be between 10 and 300 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testCourseIsNull()
    {
        Request request = new Request( null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "course" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request course should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testStudentIsNull()
    {
        Request request = new Request( null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "student" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request student should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testCreationDateIsNull()
    {
        Request request = new Request( null, null, null, null, null );
        Set<ConstraintViolation<Request>> constraintViolations = validator.validateProperty( request, "creationDate" );
        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Request creation date should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

}

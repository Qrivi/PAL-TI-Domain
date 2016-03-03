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
import java.util.Set;

public class ReviewTest extends ValidationTest
{

    private final String shortText = "Good";
    private final String normalText = "The lesson was great, I learned a lot!";
    private final String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet tortor maximus, scelerisque ex eu, bibendum purus. Aenean lectus sed.";

    @Test
    public void testTextSizeIsTooSmall()
    {
        Review r = new Review( shortText, null, new Lesson(), 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testTextSizeIsTooBig()
    {
        Review r = new Review( longText, null, new Lesson(), 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "text" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review text should be between 10 to 140 characters!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testStudentIsNull()
    {
        Review r = new Review( normalText, null, new Lesson(), 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "student" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review student should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testLessonIsNull()
    {
        Review r = new Review( normalText, new Student(), null, 5, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "lesson" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review lesson should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testDateIsNull()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 5, 5, 5, false, null );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "date" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review date should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testContentScoreTooLow()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 0, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "contentScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review content score should not be less than 1!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testContentScoreTooHigh()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 11, 5, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "contentScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review content score should not be greater than 10!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testAtmosphereScoreTooLow()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 5, 5, 0, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "atmosphereScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review atmosphere score should not be less than 1!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testAtmosphereScoreTooHigh()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 5, 5, 11, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "atmosphereScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review atmosphere score should not be greater than 10!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testTutorScoreTooLow()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 0, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "tutorScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review tutor score should not be less than 1!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testTutorScoreTooHigh()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 11, 5, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "tutorScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review tutor score should not be greater than 10!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testEngagementScoreTooLow()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 5, 0, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "engagementScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review engagement score should not be less than 1!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testEngagementScoreTooHigh()
    {
        Review r = new Review( normalText, new Student(), new Lesson(), 5, 5, 11, 5, false );

        Set<ConstraintViolation<Review>> constraintViolations = validator.validateProperty( r, "engagementScore" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Review engagement score should not be greater than 10!", constraintViolations.iterator().next().getMessage() );
    }

}
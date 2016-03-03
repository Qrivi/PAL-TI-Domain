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

package be.peerassistedlearningti.common.model.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for the FieldMatch annotation
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{

    /**
     * The first name
     */
    private String firstFieldName;

    /**
     * The second name
     */
    private String secondFieldName;

    /**
     * Initialises the validator by setting the first and second name
     *
     * @param constraintAnnotation The FieldMatch annotation
     */
    public void initialize( final FieldMatch constraintAnnotation )
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    /**
     * Checks if the values of the  first and second names are equal
     *
     * @param value The object holding the values
     * @param context The constraint context
     * @return True if the values are equal
     * @return False if the values are not equal
     */
    public boolean isValid( final Object value , final ConstraintValidatorContext context )
    {
        try
        {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch ( final Exception ignore ) { }
        return true;
    }

}
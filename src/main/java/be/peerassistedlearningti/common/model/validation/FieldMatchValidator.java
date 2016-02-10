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
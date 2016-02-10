package be.peerassistedlearningti.common.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation used to validate if two strings are equal
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch
{

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Sets the error message
     *
     * @return If the message is not set it returns the default message
     * @return If the message is set it returns the message
     */
    String message() default "{constraints.fieldmatch}";

    /**
     * Sets the first name
     *
     * @return The first name
     */
    String first();

    /**
     * Sets the second name
     *
     * @return The second name
     */
    String second();

    /**
     * Multiple FieldMatches are allowed by using the List interface
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }

}
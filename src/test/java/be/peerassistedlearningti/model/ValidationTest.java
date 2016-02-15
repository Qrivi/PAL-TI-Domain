package be.peerassistedlearningti.model;

import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Class used to test the constraints on an object
 */
public class ValidationTest
{

    protected static Validator validator;

    @BeforeClass
    public static void setUpValidator()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

}

package cl.barbatos.basemvc.validations;

import cl.barbatos.basemvc.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPassword;

    private static final int MIN_PASSWORD_LENGTH = 6;

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public void initialize(PasswordValidator passwordValidator) {
        weakPassword = Arrays.asList("123456", "password", "qwerty");
    }

    @Override
    public boolean isValid(String passwordField,
                           ConstraintValidatorContext constraintValidatorContext) {

        if (passwordField == null || passwordField.length() < MIN_PASSWORD_LENGTH || weakPassword.contains(passwordField.toLowerCase())) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordField);

        return matcher.matches();
    }
}

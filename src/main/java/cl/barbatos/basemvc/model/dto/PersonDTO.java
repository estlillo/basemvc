package cl.barbatos.basemvc.model.dto;

import cl.barbatos.basemvc.annotation.FieldsValueMatch;
import cl.barbatos.basemvc.annotation.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@FieldsValueMatch.List({
        @FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "Passwords do not match!"),
        @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Emails do not match!")

})
public class PersonDTO implements Serializable {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    @PasswordValidator
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Confirm email is required")
    @Email(message = "Confirm email is not valid")
    private String confirmEmail;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^(\\+56|56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$", message = "Mobile number is not valid")
    private String mobileNumber;
}

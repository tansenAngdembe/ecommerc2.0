package com.ecommerce.ecommerce_20.dto.Request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message="Last name is required.")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Mobile number is required.")
    private String mobileNumber;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 14, message = "Password must be between 8 and 14 characters")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$",
//            message = "Password must be at least 8 characters and include uppercase, lowercase, number, and special character"
//    )
    private String password;
}

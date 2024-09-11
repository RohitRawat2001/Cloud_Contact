package com.Cloud.Cloud.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    //@Pattern ka use kr sakta hai net ka search kr laana (Bada level ka liya)

    @NotBlank(message = "Username is Required")
    @Size(min = 3,message = "Min 3 Character is Required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6,message = "Min 6 Character is Required")
    private String password;

    @NotBlank(message = "About is Required")
    private String about;

    @NotBlank(message = "PhoneNumber is Required")
    @Size(min = 8, max = 12 ,message = "Invalid Phone Number")
    private String PhoneNumber;
}

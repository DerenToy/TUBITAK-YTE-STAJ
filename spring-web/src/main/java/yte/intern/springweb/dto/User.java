package yte.intern.springweb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class User {

    @NotBlank(message = "name can not be empty!")
    private String name;
    // @Getter private String name;


    @NotBlank(message = "Surname can not be empty!")
    private String surname;

    @Min(value=12, message = "Age can not be lower than 12!")
    @Max(value=100)
    private Long age;

    @NotBlank
    @Email
    private String email;

    @PastOrPresent
    private LocalDate birthDate; // time olmaksızın ifade etmek için

    @Size(max=250)
    private String address;

    @NotBlank
    private String userName;




}

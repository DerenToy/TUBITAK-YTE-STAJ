package yte.intern.springapplication.student.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yte.intern.springapplication.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
@ToString
public class AddStudentRequest {

    @Size(max=255, message = "Firstname can not exceed 255 characters")
    @NotEmpty(message = "Firstname cannot be empty")
    private final String firstName;

    @Size(max=255, message = "Lastname can not exceed 255 characters")
    @NotEmpty(message = "Lastname cannot be empty")
    private final String lastName;

    @Email(message = "Please enter a valid email address")
    @NotEmpty(message = "Email can't be empty!")
    private final String email;

    //farklı durumlar vamrış
    @Size(min=11, max = 11, message = "Must be 11 characters")
    private final String tcKimlikNumber;

    @Size(min=7, max=7, message = "must be 7 characters")
    private final String studentNumber;

    public Student toStudent(){
        return new Student(firstName, lastName, email, tcKimlikNumber, studentNumber);

    }
}

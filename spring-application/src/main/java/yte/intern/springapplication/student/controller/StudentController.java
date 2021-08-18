package yte.intern.springapplication.student.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.springapplication.common.dto.MessageResponse;
import yte.intern.springapplication.student.controller.request.AddBookToStudentRequest;
import yte.intern.springapplication.student.controller.request.AddStudentRequest;
import yte.intern.springapplication.student.controller.request.UpdateStudentRequest;
import yte.intern.springapplication.student.controller.response.StudentQueryResponse;
import yte.intern.springapplication.student.entity.Student;
import yte.intern.springapplication.student.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/students")
//@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//mapping işini controller yapmalı
    @PostMapping
    public MessageResponse addStudent(@Valid @RequestBody final AddStudentRequest request){
        // Student student = new Student(request.getFirstName(), ) -> veri ile veri üzerinde işlem yapan fonksiyonlar
        //bir arada bulunmalı.. onu direkt request içine yazarız

        return studentService.addStudent(request.toStudent());
        //System.out.println(request); //controllerdan direkt entity almıyoruz requestten alıyoruz



    }

    @GetMapping
    public List<StudentQueryResponse> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(StudentQueryResponse::new)
                .toList();

    }

    //@Size(min = 7, max = 7, message = "Student number must be 7 characters long")
    @GetMapping("/{studentNumber}")
    public StudentQueryResponse getStudentByStudentNumber(
            @PathVariable
            @Size(min = 7, max = 7, message = "Student number must be 7 characters long") final String studentNumber) {
        Student student = studentService.getStudentByStudentNumber(studentNumber);
        return new StudentQueryResponse(student);
    }

    @PutMapping("/{id}")
    public MessageResponse updateStudent(@PathVariable Long id, @RequestBody @Valid UpdateStudentRequest request) {
        return studentService.updateStudent(id, request.toStudent());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/{id}/books")
    public MessageResponse addBookToStudent(@RequestBody @Valid AddBookToStudentRequest addBookToStudentRequest,
                                            @PathVariable Long id) {
        return studentService.addBookToStudent(id, addBookToStudentRequest.toBook());
    }



}

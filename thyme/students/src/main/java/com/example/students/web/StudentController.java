package com.example.students.web;

import com.example.students.Domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class StudentController {

    @RequestMapping("/hello")
    public String students(Model model) {

        ArrayList<Student> studentArr = new ArrayList<Student>();

        studentArr.add(new Student("Bob", "Barker"));
        studentArr.add(new Student("Jack", "Sparrow"));
        studentArr.add(new Student("Willy", "Wonka"));
        studentArr.add(new Student("Bob", "Billson"));

        model.addAttribute( "students", studentArr);

        return "studentlist";

    }
}

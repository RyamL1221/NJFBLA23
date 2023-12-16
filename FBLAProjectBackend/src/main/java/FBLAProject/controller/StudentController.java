package FBLAProject.controller;

import FBLAProject.model.Student;
import FBLAProject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable("id") Long id) {
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("grade/{gradeLevel}")
    public ResponseEntity<List<Student>> getStudentByGrade(@PathVariable("gradeLevel") int gradeLevel) {
        return new ResponseEntity<>(studentService.findStudentsByGrade(gradeLevel), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<> (newStudent, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @PutMapping("points/{id}/{points}")
    public ResponseEntity<Student> addPoints(@PathVariable("id") Long id, @PathVariable("points") int points) {
        Student addPointStudent = studentService.findStudentById(id);
        addPointStudent.setPoints(addPointStudent.getPoints() + points);
        addPointStudent = studentService.updateStudent(addPointStudent);
        return new ResponseEntity<>(addPointStudent, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // returns student with the highest points
    @GetMapping("/highest")
    public ResponseEntity<List<Student>> getHighestStudentPoints() {
        List<Student> nineStudentList = studentService.findStudentsByGrade(9);
        List<Student> tenStudentList = studentService.findStudentsByGrade(10);
        List<Student> elevenStudentList = studentService.findStudentsByGrade(11);
        List<Student> twelveStudentList = studentService.findStudentsByGrade(12);
        List<Student> highestPointStudents = new ArrayList<Student>();
        int highest9 = Integer.MIN_VALUE;
        int highest10 = Integer.MIN_VALUE;
        int highest11 = Integer.MIN_VALUE;
        int highest12 = Integer.MIN_VALUE;
        int index9 =  -1;
        int index10 =  -1;
        int index11 =  -1;
        int index12 =  -1;
        for(int i = 0; i < nineStudentList.size(); i++)
            if(nineStudentList.get(i).getPoints() > highest9) {
                index9 = i;
                highest9 = nineStudentList.get(i).getPoints();
            }
        for(int i = 0; i < tenStudentList.size(); i++)
            if(tenStudentList.get(i).getPoints() > highest10) {
                index10 = i;
                highest10 = tenStudentList.get(i).getPoints();
            }
        for(int i = 0; i < elevenStudentList.size(); i++)
            if(elevenStudentList.get(i).getPoints() > highest11) {
                index11 = i;
                highest11 = elevenStudentList.get(i).getPoints();
            }
        for(int i = 0; i < twelveStudentList.size(); i++)
            if(twelveStudentList.get(i).getPoints() > highest12) {
                index12 = i;
                highest12 = twelveStudentList.get(i).getPoints();
            }
        highestPointStudents.add(nineStudentList.get(index9));
        highestPointStudents.add(tenStudentList.get(index10));
        highestPointStudents.add(elevenStudentList.get(index11));
        highestPointStudents.add(twelveStudentList.get(index12));
        return new ResponseEntity<>(highestPointStudents, HttpStatus.OK);
    }

    // returns random winner from a grade
    @GetMapping("/random")
    public ResponseEntity<List<Student>> getRandomStudentWinner() {
        List<Student> nineStudentList = studentService.findStudentsByGrade(9);
        List<Student> tenStudentList = studentService.findStudentsByGrade(10);
        List<Student> elevenStudentList = studentService.findStudentsByGrade(11);
        List<Student> twelveStudentList = studentService.findStudentsByGrade(12);
        int winnerNum9 = (int) (Math.random() * nineStudentList.size());
        int winnerNum10 = (int) (Math.random() * tenStudentList.size());
        int winnerNum11 = (int) (Math.random() * elevenStudentList.size());
        int winnerNum12 = (int) (Math.random() * twelveStudentList.size());
        List<Student> winners = new ArrayList<Student>();
        winners.add(nineStudentList.get(winnerNum9));
        winners.add(tenStudentList.get(winnerNum10));
        winners.add(elevenStudentList.get(winnerNum11));
        winners.add(twelveStudentList.get(winnerNum12));
        return new ResponseEntity<>(winners, HttpStatus.OK);
    }
}

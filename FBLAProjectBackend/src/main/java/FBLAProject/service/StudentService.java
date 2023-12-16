package FBLAProject.service;

import FBLAProject.model.Student;
import FBLAProject.exception.StudentNotFoundException;
import FBLAProject.repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student) {
        student.setCode(UUID.randomUUID().toString());
        return studentRepo.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepo.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " was not found."));
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteStudentById(id);
    }

    public List<Student> findStudentsByGrade(int grade) {
       return studentRepo.findStudentsByGradeLevel(grade).
               orElseThrow(() -> new StudentNotFoundException("No students found in grade level" + grade));
    }
}

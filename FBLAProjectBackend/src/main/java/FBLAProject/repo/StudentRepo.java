package FBLAProject.repo;

import FBLAProject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    void deleteStudentById(Long id);
    Optional<Student> findStudentById(Long id);

    Optional<List<Student>> findStudentsByGradeLevel(int gradeLevel);
}

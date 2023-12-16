package FBLAProject.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException() {
        super("Student provided does not exist.");
    }

    public StudentNotFoundException(String error) {
        super(error);
    }
}

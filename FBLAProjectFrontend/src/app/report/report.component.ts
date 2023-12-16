import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Student } from '../student/student';
import { StudentService } from '../student/student.service';

@Component({
    selector: 'report',
    templateUrl: 'report.component.html'
})

export class ReportComponent implements OnInit{

    public students: Student[] = [];
    constructor(private studentService: StudentService) {}

    ngOnInit() {
       this.getStudents();
    }

    public getStudents(): void {
        this.studentService.getStudents().subscribe(
          (response: Student[]) => {
            this.students = response;
          },
          (error:HttpErrorResponse) => {
            alert(error.message);
          }
        )
      }
}

import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Student } from './student';
import { StudentService } from './student.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'student',
  templateUrl: 'student.component.html',
  styleUrls: ['student.component.css']
})
export class StudentComponent implements OnInit{
  public students: Student[] = [];
  public editStudent: Student = {id:-1, name:"-1", gradeLevel:-1, points:-1, eventsAttended: "-1", code:"-1"}; 
  public deleteStudent: Student = {id:-1, name:"-1", gradeLevel:-1, points:-1, eventsAttended: "-1", code:"-1"}; 
  public eventStudent: Student = {id:-1, name:"-1", gradeLevel:-1, points:-1, eventsAttended: "-1", code:"-1"};  
  public eventSelect: string = ''; 
  public allEvents: string[] = ['Basketball Game', 'Volleyball Game', 'Soccer Game', 'Baseball Game', 'Golf Game', 'Volunteering', 'Fundraising', 'New Student Orientation', 'Band Concert', 'Career Day'];
  public allEventsPointValue: number[] = [5, 5, 5, 5, 5, 10, 10, 10, 10, 10];
  public eventsAttended: Array<string> = [];
  public randomWinnerStudents: Student[] =  [];
  public topWinnerStudents: Student[] = [];
 

  constructor(private studentService: StudentService) {}

  ngOnInit() { // whenever the component is intialized, this function will be called 
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

  public onAddStudent(addForm: NgForm): void {
    document.getElementById('add-student-form')?.click();
    this.studentService.addStudent(addForm.value).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudents();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateStudent(student: Student): void {
    document.getElementById('edit-student-form')?.click();
    this.studentService.updateStudent(student).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteStudent(studentId: number): void {
    document.getElementById('delete-student-form')?.click();
    this.studentService.deleteStudent(studentId).subscribe(
      (response: void) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchStudents(key: string): void {
    const results: Student[] = [];
    for(const student of this.students) {
      if(student.name.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(student);
      }
    }
    this.students = results;
    if(results.length === 0 || !key) {
      this.getStudents();
    }
  }

  public onOpenAddModal(): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addStudentModal');
    container?.appendChild(button);
    button.click();
  }

  public onOpenEditModal(student: Student): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    this.editStudent = student;
    button.setAttribute('data-target', '#editStudentModal');
    container?.appendChild(button);
    button.click();
  }

  public onOpenDeleteModal(student: Student): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    this.deleteStudent = student;
    button.setAttribute('data-target', '#deleteStudentModal');
    container?.appendChild(button);
    button.click();
  }

  public onSelectDropdown(student: Student, event: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    this.eventStudent = student;
    this.eventSelect = event;
    button.setAttribute('data-target', '#confirmEventModal');
    container?.appendChild(button);
    button.click();
  }

  public onConfirmEventAdd(): void{
    document.getElementById('event-student-form')?.click();
    if(this.eventSelect.indexOf('Game') !== -1) {
      this.eventStudent.points = this.eventStudent.points + 5;
      if(this.eventStudent.eventsAttended.length > 0)
        this.eventStudent.eventsAttended += ', ' + this.eventSelect;
      else 
        this.eventStudent.eventsAttended += this.eventSelect;
      this.studentService.updateStudent(this.eventStudent).subscribe(
        (response: Student) => {
          console.log(response);
          this.getStudents();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
    if(this.eventSelect.indexOf('Game') === -1) {
      this.eventStudent.points = this.eventStudent.points + 10;
      if(this.eventStudent.eventsAttended.length > 0)
        this.eventStudent.eventsAttended += ', ' + this.eventSelect;
      else 
        this.eventStudent.eventsAttended += this.eventSelect;
      this.studentService.updateStudent(this.eventStudent).subscribe(
        (response: Student) => {
          console.log(response);
          this.getStudents();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

  public pickRandomWinner(): void {
    this.studentService.selectRandomStudentWinners().subscribe(
      (response: Student[]) => {
        this.randomWinnerStudents = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#randomWinnerModal');
    container?.appendChild(button);
    button.click();
  }

  public pickTopWinner(): void {
    this.studentService.selectHighestPointStudent().subscribe(
      (response: Student[]) => {
        this.topWinnerStudents = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none'; // button is not displayed 
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#topWinnerModal');
    container?.appendChild(button);
    button.click();
  }

  public selectPrize(points: number): string {
    if(points < 20)
      return 'T-shirt';
    else if(points < 40)
      return 'pencil case';
    else if(points < 60)
      return 'School keychain';
    else
      return 'computer';
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home';
import { AppComponent } from './app.component';
import { StudentComponent } from './student/student.component';
import { ReportComponent } from './report';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'student', component: StudentComponent},
  {path: 'report', component: ReportComponent},

  //directs to home in all other cases
  {path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { 
}

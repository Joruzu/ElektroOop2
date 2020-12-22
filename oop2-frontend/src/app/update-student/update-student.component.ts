import { Component, OnInit } from '@angular/core';
import {Student} from "../student";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../student.service";

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {
  studid: number;
  student: Student = new Student();
  geslachten = ['man', 'vrouw'];
  orientatie = ['telecommunicatie', 'informatica', 'Energietechniek'];
  studiestatus = ['Actief', 'Inactief', 'Voldaan'];

  constructor(private route: ActivatedRoute,
              private studentService: StudentService,
              private router: Router) { }

  ngOnInit(): void {
    this.studid = this.route.snapshot.params['studid'];
    this.studentService.getStudentById(this.studid).subscribe(data=> {
      this.student = data;
    }, error => console.log(error))
  }

  onSubmit() {
    this.studentService.updateStudent(this.studid, this.student).subscribe(data=>{
      this.goToStudentInfo()
    }, error => console.log(error))
  }

  goToStudentInfo() {
    this.router.navigate(['studenten', this.studid ,'info']);
  }

}

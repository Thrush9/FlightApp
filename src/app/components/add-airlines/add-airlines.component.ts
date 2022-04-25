import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-add-airlines',
  templateUrl: './add-airlines.component.html',
  styleUrls: ['./add-airlines.component.css']
})
export class AddAirlinesComponent implements OnInit {

  airlinesForm: FormGroup;
  name = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]);
  poc = new FormControl('', [Validators.required]);
  contact = new FormControl('');
  address = new FormControl('');
  enabled = new FormControl(true);
  error: string = '';

  constructor(private flightService: FlightService, private snackbar: MatSnackBar) {
    this.airlinesForm = new FormGroup({
      name: this.name,
      email: this.email,
      poc: this.poc,
      contact: this.contact,
      address: this.address,
      enabled: this.enabled
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.airlinesForm.value);
    let data = this.airlinesForm.value;
    this.flightService.registerAirlines(data).subscribe(
      (res) => {
        let msg1 = `${res.name} Airlines Registration Succesful!`;
        this.snackbar.open(msg1, 'OK', { duration: 5000 });
        this.clearForm();
      },
      (err) => {
        if (err.status === 400) {
          this.error = err.error;
        } else {
          this.error = err.error.message;
        }
      }
    );
  }

  clearForm() {
    this.airlinesForm.reset();
  }

}

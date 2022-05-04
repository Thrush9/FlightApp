import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { FlightService } from 'src/app/services/flight.service';
import { Flight } from 'src/app/models/Flight';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Airlines } from 'src/app/models/Airlines';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';


interface slot {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { showError: true },
    },
  ],
})

export class AddFlightComponent implements OnInit {

  airlinesData: Airlines[];
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isEditable = true;

  slots: slot[] = [
    { value: 'daily', viewValue: 'DAILY' },
    { value: 'weekly', viewValue: 'WEEKLY' },
    { value: 'weekends', viewValue: 'WEEKENDS' },
  ];


  error: string = '';

  constructor(private flightService: FlightService, private snackbar: MatSnackBar, private _formBuilder: FormBuilder) {
    this.airlinesData = [];
    this.firstFormGroup = this._formBuilder.group({
      airlinesId: ['', Validators.required],
      flightNo: ['', Validators.required],
      fromPlace: ['', Validators.required],
      toPlace: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
    this.secondFormGroup = this._formBuilder.group({
      cost: ['', Validators.required],
      availability: ['', Validators.required],
      businessSeats: ['', Validators.required],
      nonBusinessSeats: ['', Validators.required],
      totalRows: ['', Validators.required],
      instrument: ['', Validators.required],
      meal: [true],
      veg: [true]
    });
  }

  ngOnInit(): void {
    this.fetchAllAirlines();
  }

  fetchAllAirlines() {
    this.flightService.getAllAirlinesDetails().subscribe(res => {
      this.airlinesData = res;
    });
  }

  onSubmit() {
    let flight: Flight = {
      ...this.firstFormGroup.value
      , ...this.secondFormGroup.value,
    };
    console.log(flight);
    let start = this.convert(flight.startDate);
    let end = this.convert(flight.endDate);
    this.flightService.scheduleFlight(flight).subscribe(
      (res) => {
        let msg1 = `${res.flightNo} Flight Scheduled Succesfully!`;
        this.snackbar.open(msg1, 'OK', { duration: 5000 });
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

  convert(str: any) {
    var date = new Date(str),
      mnth = ("0" + (date.getMonth() + 1)).slice(-2),
      day = ("0" + date.getDate()).slice(-2);
    return [date.getFullYear(), mnth, day].join("-");
  }
}

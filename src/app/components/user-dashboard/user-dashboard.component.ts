import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { FlightService } from 'src/app/services/flight.service';
import { BookingService } from 'src/app/services/booking.service';
import { Flight } from 'src/app/models/Flight';
import { Airlines } from 'src/app/models/Airlines';
import { MatSnackBar } from '@angular/material/snack-bar';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { showError: true },
    },
  ],
})
export class UserDashboardComponent implements OnInit {

  searchForm: FormGroup;
  airlines = new FormControl('', [Validators.required]);
  searchDate = new FormControl('', [Validators.required]);
  source = new FormControl('', [Validators.required]);
  destination = new FormControl('');

  p: number = 1;
  flightsData: Flight[];
  airlinesData: Airlines[];
  selectedFlightId: number = 0;
  showBookingCard: boolean = false;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isEditable = true;

  constructor(private flightService: FlightService, private snackbar: MatSnackBar, private _formBuilder: FormBuilder, private bookingService: BookingService) {
    this.flightsData = [];
    this.airlinesData = [];
    this.searchForm = new FormGroup({
      airlines: this.airlines,
      searchDate: this.searchDate,
      source: this.source,
      destination: this.destination
    });

    this.firstFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      totalSeats: ['', Validators.required],
      meal: [true],
    });
    this.secondFormGroup = this._formBuilder.group({
      passengers: ['', Validators.required],
      seats: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.fetchAllFlights();
    this.fetchAllAirlines();
  }

  fetchAllFlights() {
    this.flightService.getAllFlightDetailsForUser().subscribe(res => {
      this.flightsData = res;
    });
  }

  fetchAllAirlines() {
    this.flightService.getAllAirlinesDetails().subscribe(res => {
      this.airlinesData = res;
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });
  }

  convert(str: any) {
    let date = new Date(str),
      mnth = ("0" + (date.getMonth() + 1)).slice(-2),
      day = ("0" + date.getDate()).slice(-2);
    return [date.getFullYear(), mnth, day].join("-");
  }

  onSubmit() {
    console.log(this.searchForm.value);
    let data = { ...this.searchForm.value, searchDate: this.convert(this.searchForm.value.searchDate) };
    console.log(data);
    this.flightService.searchFlights(data).subscribe(res => {
      this.flightsData = res;
      let msg;
      if (this.flightsData.length === 0) {
        msg = 'No Results found! Try other options';
      } else {
        msg = 'Results Fetched Sucessfully!';
      }
      this.snackbar.open(msg, 'OK', { duration: 5000 });
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });
  }

  bookFlight(data: Flight) {
    console.log(data);
    this.selectedFlightId = data.id
    this.showBookingCard = true;
    this.firstFormGroup.reset();
    this.secondFormGroup.reset();
  }

  onSave() {
    let group1 = this.firstFormGroup.value
    let group2 = this.secondFormGroup.value
    console.log(group1);
    console.log(group2);
    let passengersArray = group2.passengers.split(',');
    let seatsArray = group2.seats.split(',');
    let booking = {
      ...group1, passengers: passengersArray
      , seats: seatsArray, flightId: this.selectedFlightId
    };
    this.bookingService.newFlightBooking(booking).subscribe(res => {
      let msg = `Flight Booked Sucessfully with PNR number -${res.pnr}`;
      this.snackbar.open(msg, 'OK', { duration: 5000 });
      this.firstFormGroup.reset();
      this.secondFormGroup.reset();
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });

  }

}

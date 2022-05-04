import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { BookingService } from 'src/app/services/booking.service';
import { FlightService } from 'src/app/services/flight.service';
import { Flight } from 'src/app/models/Flight';
import { Booking } from 'src/app/models/Booking';
import { Airlines } from 'src/app/models/Airlines';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-booking-status',
  templateUrl: './booking-status.component.html',
  styleUrls: ['./booking-status.component.css']
})
export class BookingStatusComponent implements OnInit {

  searchForm: FormGroup;
  pnr = new FormControl('', [Validators.required]);

  bookingDetails!: Booking;
  flightDetails!: Flight;
  airlinesDetails!: Airlines;
  showDetails: boolean = false;

  constructor(private snackbar: MatSnackBar, private bookingService: BookingService, private flightService: FlightService) {
    this.searchForm = new FormGroup({
      pnr: this.pnr,
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.searchForm.value);
    let data = this.searchForm.value;
    this.bookingService.getBookingDetailsByPNR(data).subscribe(res => {
      this.loadBookingDetails(res);
      let msg = 'Booking Details Fetched Successfully';
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

  loadBookingDetails(data: Booking) {
    console.log(123);
    this.bookingDetails = data;
    this.flightService.getFlightDetailsById(data.flightId).subscribe(res => {
      this.flightDetails = res;
      console.log(this.flightDetails);
      console.log(this.flightDetails.airlinesId);
      this.flightService.getAirlinesDetailsById(this.flightDetails.airlinesId).subscribe(resp => {
        this.airlinesDetails = resp;
        console.log(this.airlinesDetails);
      },
        (err) => {
          if (err.status === 400) {
            console.log(err.error);
          } else {
            console.log(err.error.message);
          }
        });
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });

    this.showDetails = true;
    console.log(data);
  }

  cancelBooking(data: Booking) {
    console.log(data);
    this.bookingService.cancelBooking(data.pnr).subscribe(res => {
      console.log(res);
      let msg = 'Booking Canceled Successfully';
      this.snackbar.open(msg, 'OK', { duration: 5000 });
      this.onSubmit();
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

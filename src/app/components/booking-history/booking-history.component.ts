import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/services/booking.service';
import { FlightService } from 'src/app/services/flight.service';
import { Flight } from 'src/app/models/Flight';
import { Booking } from 'src/app/models/Booking';
import { Airlines } from 'src/app/models/Airlines';

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.css']
})
export class BookingHistoryComponent implements OnInit {

  p: number = 1;
  bookingsData: Booking[];
  bookingDetails!: Booking;
  flightDetails!: Flight;
  airlinesDetails!: Airlines;
  showDetails: boolean = false;

  constructor(private bookingService: BookingService, private flightService: FlightService) {
    this.bookingsData = [];
  }

  ngOnInit(): void {
    this.fetchAllFlights()
  }

  fetchAllFlights() {
    this.bookingService.getAllBookingsByEmailId().subscribe(res => {
      this.bookingsData = res;
      console.log(res);
    });
  }

  loadBookingDetails(data: Booking) {
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

}

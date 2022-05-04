import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { Airlines } from 'src/app/models/Airlines';
import { Flight } from 'src/app/models/Flight';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  p: number = 1;
  p1: number = 1;
  airlinesData: Airlines[];
  flightsData: Flight[];
  totalAirlines: number = 0;
  totalFlights: number = 0;
  totalUsers: number = 5;

  ngAfterViewInit() {
  }

  constructor(private flightService: FlightService, private snackbar: MatSnackBar) {
    this.airlinesData = [];
    this.flightsData = [];
  }

  ngOnInit(): void {
    this.fetchAllAirlines();
    this.fetchAllFlights();
    this.fetchAllUsers();
  }

  fetchAllAirlines() {
    this.flightService.getAllAirlinesDetails().subscribe(res => {
      this.airlinesData = res;
      this.totalAirlines = this.airlinesData.length
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });
  }

  fetchAllUsers() {
    this.flightService.getAllUserDetails().subscribe(res => {
      this.totalUsers = res.length
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });
  }

  fetchAllFlights() {
    this.flightService.getAllFlightDetails().subscribe(res => {
      this.flightsData = res;
      this.totalFlights = this.flightsData.length
    },
      (err) => {
        if (err.status === 400) {
          console.log(err.error);
        } else {
          console.log(err.error.message);
        }
      });
  }

  toggleAirlines(data: Airlines) {
    this.flightService.toggleAirlines(data.name).subscribe(res => {
      let msg = `${res.name} Airlines ${res.enabled === true ? 'Enabled' : 'Disabled'} Succesfully!`;
      this.snackbar.open(msg, 'OK', { duration: 5000 });
      this.fetchAllAirlines();
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

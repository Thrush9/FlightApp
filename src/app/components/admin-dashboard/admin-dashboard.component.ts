import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { Airlines } from 'src/app/models/Airlines';
import { Flight } from 'src/app/models/Flight';
import { MatSnackBar } from '@angular/material/snack-bar';


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
  }

  fetchAllAirlines() {
    this.flightService.getAllAirlinesDetails().subscribe(res => {
      this.airlinesData = res;
      this.totalAirlines = this.airlinesData.length
    });
  }

  fetchAllFlights() {
    this.flightService.getAllFlightDetails().subscribe(res => {
      this.flightsData = res;
      this.totalFlights = this.flightsData.length
    });
  }

  toggleAirlines(data: Airlines) {
    this.flightService.toggleAirlines(data.name).subscribe(res => {
      let msg = `${res.name} Airlines ${res.enabled === true ? 'Enabled' : 'Disabled'} Succesfully!`;
      this.snackbar.open(msg, 'OK', { duration: 5000 });
      this.fetchAllAirlines();
    });
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Airlines } from '../models/Airlines';
import { AuthenticationService } from './authentication.service';
import { Flight } from '../models/Flight';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  //flightService:string = 'http://localhost:8085';
  flightServiceAPI: string = 'http://localhost:8765/FlightService';

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getAllAirlinesDetails() {
    let token = this.authService.getBearertoken();
    return this.http.get<Airlines[]>(this.flightServiceAPI + '/api/v1.0/flight/airline/fetchAllAirlines',
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getAllFlightDetails() {
    let token = this.authService.getBearertoken();
    return this.http.get<Flight[]>(this.flightServiceAPI + '/api/v1.0/flight/fetchAllFlights',
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getAllFlightDetailsForUser() {
    let token = this.authService.getBearertoken();
    return this.http.get<Flight[]>(this.flightServiceAPI + '/api/v1.0/flight/fetchAllFlightsForUser',
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getAllUserDetails() {
    let token = this.authService.getBearertoken();
    return this.http.get<User[]>(this.flightServiceAPI + '/api/v1.0/flight/fetchAllUsers',
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  toggleAirlines(name: string) {
    let token = this.authService.getBearertoken();
    return this.http.post<any>(`${this.flightServiceAPI}/api/v1.0/flight/airline/toggleAvailability/${name}`, {},
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  registerAirlines(data: Airlines) {
    let token = this.authService.getBearertoken();
    return this.http.post<any>(this.flightServiceAPI + '/api/v1.0/flight/airline/register', data,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  scheduleFlight(flight: Flight) {
    let data = { ...flight, startDate: new Date(flight.startDate), endDate: new Date(flight.endDate) };
    console.log(data);
    let token = this.authService.getBearertoken();
    return this.http.post<any>(this.flightServiceAPI + '/api/v1.0/flight/airline/inventory/add', data,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getFlightDetailsById(id: number) {
    let token = this.authService.getBearertoken();
    return this.http.get<Flight>(`${this.flightServiceAPI}/api/v1.0/flight/fetchSpecificFlight/${id}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getAirlinesDetailsById(id: number) {
    let token = this.authService.getBearertoken();
    return this.http.get<Airlines>(`${this.flightServiceAPI}/api/v1.0/flight/airline/fetchSpecificAirlines/${id}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  searchFlights(data: any) {
    let token = this.authService.getBearertoken();
    return this.http.get<Flight[]>(`${this.flightServiceAPI}/api/v1.0/flight/search?airlines=${data.airlines}&source=${data.source}&destination=${data.destination}&searchDate=${data.searchDate}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

}


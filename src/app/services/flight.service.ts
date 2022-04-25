import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Airlines } from '../models/Airlines';
import { AuthenticationService } from './authentication.service';
import { Flight } from '../models/Flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getAllAirlinesDetails() {
    let token = this.authService.getBearertoken();
    return this.http.get<Airlines[]>(`http://localhost:8085/api/v1.0/flight/airline/fetchAllAirlines`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getAllFlightDetails() {
    let token = this.authService.getBearertoken();
    return this.http.get<Flight[]>(`http://localhost:8085/api/v1.0/flight/fetchAllFlights`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  toggleAirlines(name: string) {
    let token = this.authService.getBearertoken();
    return this.http.post<any>(`http://localhost:8085/api/v1.0/flight/airline/toggleAvailability/${name}`, {},
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  registerAirlines(data: Airlines) {
    let token = this.authService.getBearertoken();
    return this.http.post<any>(`http://localhost:8085/api/v1.0/flight/airline/register`, data,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  scheduleFlight(flight: Flight, start: string, end: string) {
    let data = { ...flight, startDate: start, endDate: end };
    console.log(data);
    let token = this.authService.getBearertoken();
    return this.http.post<any>(`http://localhost:8085/api/v1.0/flight/airline/inventory/add`, data,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

}

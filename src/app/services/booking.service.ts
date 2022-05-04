import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { AuthenticationService } from './authentication.service';
import { Booking } from '../models/Booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  //flightService:string = 'http://localhost:8086';
  bookingServiceAPI: string = 'http://localhost:8765/BookingService';

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getAllBookingsByEmailId() {
    let token = this.authService.getBearertoken();
    let email = this.authService.getUserEmail();
    return this.http.get<Booking[]>(`${this.bookingServiceAPI}/api/v1.0/flight/booking/history/${email
      }`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  getBookingDetailsByPNR(data: any) {
    let token = this.authService.getBearertoken();
    return this.http.get<Booking>(`${this.bookingServiceAPI}/api/v1.0/flight/ticket/${data.pnr}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  newFlightBooking(data: Booking) {
    let token = this.authService.getBearertoken();
    return this.http.post<any>(`${this.bookingServiceAPI}/api/v1.0/flight/booking/${data.flightId}`, data,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

  cancelBooking(pnr: string) {
    let token = this.authService.getBearertoken();
    return this.http.delete<any>(`${this.bookingServiceAPI}/api/v1.0/flight/booking/cancel/${pnr}`,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
  }

}

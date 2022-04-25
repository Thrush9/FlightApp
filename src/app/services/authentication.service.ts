import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  validateUser(data: User) {
    return this.http.post('http://localhost:8085/api/v1.0/flight/login', data);
  }

  registerUser(data: User) {
    return this.http.post('http://localhost:8085/api/v1.0/flight/signup', data, { reportProgress: true, responseType: 'text' });
  }

  setBearertoken(token: string) {
    sessionStorage.setItem('bearerToken', token);
  }

  getBearertoken(): any {
    return sessionStorage.getItem('bearerToken');
  }

  setUserDetails(username: string, email: string, role: string) {
    let userDetails = { username, email, role }
    sessionStorage.setItem('userDetails', JSON.stringify(userDetails));
  }

  setUserRole(role: string) {
    sessionStorage.setItem('userRole', role);
  }

  getUserRole() {
    return sessionStorage.getItem('userRole');
  }

  setUserEmail(email: string) {
    sessionStorage.setItem('userEmail', email);
  }

  getUserEmail() {
    return sessionStorage.getItem('userEmail');
  }

}

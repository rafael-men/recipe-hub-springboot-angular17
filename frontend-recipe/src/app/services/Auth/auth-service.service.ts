import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:5454';

  constructor(private http: HttpClient) { }

  authSubject = new BehaviorSubject<any>({
    user: null
  });

  login(UserData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/signin`, UserData).pipe(
      tap(response => {
        localStorage.setItem("jwt", response.jwt);
        this.getUserProfile().subscribe();
      })
    );
  }

  register(UserData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/signup`, UserData).pipe(
      tap(response => {
        localStorage.setItem("jwt", response.jwt);
        this.getUserProfile().subscribe();
      })
    );
  }

  getUserProfile(): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    });
    return this.http.get<any>(`${this.baseUrl}/api/users/profile`, { headers }).pipe(
      tap(user => {
        console.log("get user profile", user);
        const currentState = this.authSubject.value;
        this.authSubject.next({ ...currentState, user });
      })
    );
  }

  logout() {
    localStorage.clear();
    this.authSubject.next({ user: null });
  }
}

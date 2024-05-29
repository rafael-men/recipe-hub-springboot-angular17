import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl='http://localhost:5454'

  constructor(private http:HttpClient) { }

  authSubject = new BehaviorSubject<any>({
    user:null
  })

  login(UserData:any):Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/signin`,UserData)
  }

  register(UserData:any):Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/signup`,UserData)
  }

  getUserProfile():Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    })
    return this.http.post<any>(`${this.baseUrl}api/auth/signup`,{headers}).pipe(
      tap((user)=> {
        const currentState = this.authSubject.value
        this.authSubject.next({...currentState,user})
      })
    )
  }

  logout() {
    localStorage.clear()
    this.authSubject.next({})
  }
}
